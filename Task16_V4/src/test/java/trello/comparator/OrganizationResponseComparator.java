package trello.comparator;

import trello.dto.OrganizationResponse;

public class OrganizationResponseComparator {
    public static boolean compare(OrganizationResponse expected, OrganizationResponse actual) {
        return expected.getDisplayName().equals(actual.getDisplayName());
    }
}