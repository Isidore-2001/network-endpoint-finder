package fr.lille.university.eservices.dal;

public interface NetworkEndpointFinder {

    int findNetworkEndpoint(int startNodeId, int[] fromIds, int[] toIds);
}
