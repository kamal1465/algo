package com.kamals.algo.idiom.otp;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.HazelcastClientNotActiveException;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientConnectionStrategyConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RILHazelcastClient
{
    private static final String INVENTORY_MAP = "storeInventory";
    private static final String PRE_ORDER_INVENTORY_MAP = "preOrderInventory";
    private static final String WHSHIFT_MAP = "WHShiftData";

    private static final long HAZELCAST_READ_TIMEOUT_NANOS = 2000000000;
    private static final int HAZELCAST_CONNECTION_ATTEMPT = 3;
    private static final int HAZELCAST_ATTEMPT_PERIOD = 5000;
    private static final int HAZELCAST_CONNECTION_TIMEOUT = 5000;

    private static HazelcastInstance client;

    private static final Map<String, IMap> allMaps = new HashMap<>();

    public static <K, V> void set(final String mapName, final K key, final V value)
    {
        final IMap<K, V> dataMap = getMap(mapName);
        dataMap.set(key, value);
    }

    public static <K, V> void set(final String mapName, final K key, final V value, final int time, TimeUnit timeUnit)
    {
        final IMap<K, V> dataMap = getMap(mapName);
        dataMap.set(key, value, time, timeUnit);
    }

    public static <K, V> void update(final String mapName, final K key, final V value, final int ttlSeconds)
    {
        final IMap<K, V> dataMap = getMap(mapName);
        EntryView<K, V> entryView = dataMap.getEntryView(key);
        if (entryView == null)
        {
            dataMap.set(key, value, ttlSeconds, TimeUnit.SECONDS);
        }
        else
        {
            long timeElapsed = (new Date().getTime() - entryView.getCreationTime()) / 1000;
            if (timeElapsed < ttlSeconds)
            {
                long ttl = ttlSeconds - timeElapsed;
                dataMap.set(key, value, ttl, TimeUnit.SECONDS);
            }
        }
    }

    public static <K, V> void putAll(final String mapName, final Map<K, V> map)
    {
        final IMap<K, V> dataMap = getMap(mapName);
        dataMap.putAll(map);
    }

    public static <K, V> V get(final String mapName, final K key) throws Exception
    {
        //final IMap<K, V> dataMap = getMap(mapName);
        return get(mapName, key, true);//dataMap.get(key);
    }

    public static <K, V> V get(final String mapName, final K key, final boolean async) throws Exception
    {
        final IMap<K, V> dataMap = getMap(mapName);
        ICompletableFuture<V> val = dataMap.getAsync(key);
        return val.get(HAZELCAST_READ_TIMEOUT_NANOS, TimeUnit.NANOSECONDS);
    }

    public static <K, V> Map<K, V> getAll(final String mapName, final Set<K> keys)
    {
        final IMap<K, V> dataMap = getMap(mapName);
        return dataMap.getAll(keys);
    }

    public static <K, V, M> void delete(final String mapName, final K key)
    {
        final IMap<K, V> dataMap = getMap(mapName);
        dataMap.delete(key);
    }

    public static <K, V> IMap<K, V> getMap(final String mapName)
    {
        IMap<K, V> dataMap;
        try
        {
            dataMap = allMaps.get(mapName);

            if (dataMap == null)
            {
                synchronized (RILHazelcastClient.class)
                {
                    dataMap = allMaps.get(mapName);

                    if (dataMap == null)
                    {
                        HazelcastInstance client = getClient();
                        dataMap = client.getMap(mapName);
                        allMaps.put(mapName, dataMap);
                    }
                }
            }
        }
        catch (final HazelcastInstanceNotActiveException e)
        {
            System.out.println("Got HazelcastInstanceNotActiveException:" + e.getMessage() + " Creating new client.");
            client = getNewClient();
            dataMap = client.getMap(mapName);
        }
        catch (final HazelcastClientNotActiveException e)
        {
            System.out.println("Got HazelcastClientNotActiveException:" + e.getMessage() + " Creating new client.");
            client = getNewClient();
            dataMap = client.getMap(mapName);
        }
        return dataMap;
    }

    public static <K> IQueue<K> getQueue(final String queueName)
    {
        IQueue<K> dataQueue;
        try
        {
            HazelcastInstance client = getClient();
            dataQueue = client.getQueue(queueName);
        }
        catch (final HazelcastInstanceNotActiveException e)
        {
            System.out.println("Got HazelcastInstanceNotActiveException:" + e.getMessage() + " Creating new client.");
            client = getNewClient();
            dataQueue = client.getQueue(queueName);
        }
        catch (final HazelcastClientNotActiveException e)
        {
            System.out.println("Got HazelcastClientNotActiveException:" + e.getMessage() + " Creating new client.");
            client = getNewClient();
            dataQueue = client.getQueue(queueName);
        }
        return dataQueue;
    }

    private static HazelcastInstance getClient()
    {
        if (null == client)
        {
            client = getNewClient();
        }
        return client;
    }

    private synchronized static HazelcastInstance getNewClient()
    {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.getConnectionStrategyConfig()
                .setAsyncStart(false)
                .setReconnectMode(ClientConnectionStrategyConfig.ReconnectMode.ON);

        clientConfig.getGroupConfig()
                .setName("dev")
                .setPassword("dev-pass");

        String[] addresses = "127.0.0.1".split(",");

        clientConfig.getNetworkConfig().addAddress(addresses)
                .setConnectionAttemptLimit(HAZELCAST_CONNECTION_ATTEMPT)
                .setConnectionAttemptPeriod(HAZELCAST_ATTEMPT_PERIOD)
                .setConnectionTimeout(HAZELCAST_CONNECTION_TIMEOUT);

        client = HazelcastClient.newHazelcastClient(clientConfig);

        MapConfig inventoryMapConfig = new MapConfig(INVENTORY_MAP);
        inventoryMapConfig.addMapIndexConfig(new MapIndexConfig("productId", false));
        client.getConfig().addMapConfig(inventoryMapConfig);

        MapConfig shiftMapConfig = new MapConfig(WHSHIFT_MAP);
        shiftMapConfig.addMapIndexConfig(new MapIndexConfig("nodeId", false));
        client.getConfig().addMapConfig(shiftMapConfig);

        MapConfig preOrderInventoryMapConfig = new MapConfig(PRE_ORDER_INVENTORY_MAP);
        client.getConfig().addMapConfig(preOrderInventoryMapConfig);

        return client;
    }

}
