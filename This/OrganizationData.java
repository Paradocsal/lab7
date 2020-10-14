package This;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class OrganizationData {


    private static ArrayDeque<Organization> organizationArrayDeque;

    /**
     * Building empty OrganizationData
     */
    public OrganizationData() {
        organizationArrayDeque = new ArrayDeque<>();

    }

    public static void setQ(ArrayDeque<Organization> q) {
        organizationArrayDeque = q;
    }

    public static ArrayDeque<Organization> getOrganizationArrayDeque() {
        return organizationArrayDeque;
    }

    public Organization getFirst() {
        return organizationArrayDeque.getFirst();
    }

    public String removeFirst(String user) {
        if (organizationArrayDeque.getFirst().getUser().equals(user)) {organizationArrayDeque.removeFirst(); return "Deleted first element";}
        else return "First element does not belong to you";
    }

    public void addOrganization(Organization org) {
        organizationArrayDeque.add(org);
    }

    public ArrayList<Long> getListOfIds() {
        ArrayList<Long> ids = new ArrayList<>();
        for (Organization organization :
                organizationArrayDeque) {
            ids.add(organization.getId());
        }
        return ids;
    }

    public Organization getElementById(long id) {
        for (Organization org : organizationArrayDeque) {
            if (org.getId() == id) {
                return org;
            }
        }
        return null;
    }

    public void updateOrganization(long id, Organization organization) {
        Organization updatingOrganization = new Organization();
        for (Organization org :
                organizationArrayDeque) {
            if (org.getId() == id) {
                updatingOrganization = org;
            }
        }
        organizationArrayDeque.remove(updatingOrganization);
        organization.setId(id);
        organizationArrayDeque.add(organization);
    }

    public void remove(Organization org) {
        organizationArrayDeque.remove(org);
    }

    public void clearCollection(String user) {
        ArrayDeque<Organization> arraytosave = new ArrayDeque<>();
        organizationArrayDeque.stream().filter(x -> !(x.getUser().equals(user))).forEach(arraytosave::add);
        setQ(arraytosave);
    }
}
