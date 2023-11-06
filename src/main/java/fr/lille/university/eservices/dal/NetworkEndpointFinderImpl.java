package fr.lille.university.eservices.dal;

import java.util.Map;

/**
 * @author amevigbe on 06/11/2023:14:22
 * @project network-endpoint-finder
 */
public class NetworkEndpointFinderImpl implements NetworkEndpointFinder {

    private Map<Integer, Integer> correspondance;

    public NetworkEndpointFinderImpl() {
        this.correspondance = new java.util.HashMap<>();
    }

    @Override
    public int findNetworkEndpoint(int startNodeId, int[] fromIds, int[] toIds) {
        this.getCorrespondance(fromIds, toIds);
        int index = this.getIndexId(startNodeId, fromIds, toIds);

        if (index == -1) {
            return -1;
        }
        int endNodeId = this.correspondance.get(startNodeId);
        while (endNodeId != startNodeId) {
            startNodeId = endNodeId;
            if (this.correspondance.get(startNodeId) == null) {
                return endNodeId;
            }
            endNodeId = this.correspondance.get(startNodeId);
        }
        return endNodeId;
    }

    private int getIndexId(int startNodeId, int[] fromIds, int[] toIds) {
        for (int i = 0; i < fromIds.length; i++) {
            if (fromIds[i] == startNodeId) {
                return i;
            }
        }
        return -1;
    }

    private void getCorrespondance(int[] fromIds, int[] toIds) {
        for (int i = 0; i < fromIds.length; i++) {
            this.correspondance.put(fromIds[i], toIds[i]);
        }
    }

    public static void main(String[] args) {
        NetworkEndpointFinderImpl networkEndpointFinder = new NetworkEndpointFinderImpl();
        int[] fromIds = {1,7,3,4,2,6,9};
        int[] toIds = {3,3,4,6,6,9,5};
        int startNodeId = 1;
        int networkEndpoint = networkEndpointFinder.findNetworkEndpoint(startNodeId, fromIds, toIds);
        System.out.println(networkEndpoint);
    }
}


