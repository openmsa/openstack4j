package org.openstack4j.openstack.identity.v3.internal;

import static org.openstack4j.core.transport.ClientConstants.PATH_POLICIES;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.identity.v3.PolicyService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Policy;
import org.openstack4j.openstack.identity.v3.domain.KeystonePolicy;
import org.openstack4j.openstack.identity.v3.domain.KeystonePolicy.Policies;

public class PolicyServiceImpl extends BaseIdentityServices implements PolicyService {

    @Override
    public Policy create(Policy policy) {
        Objects.requireNonNull(policy);
        return post(KeystonePolicy.class, uri(PATH_POLICIES)).entity(policy).execute();
    }

    @Override
    public Policy create(String blob, String type, String projectId, String userId) {
        Objects.requireNonNull(blob);
        Objects.requireNonNull(type);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userId);
        return create(KeystonePolicy.builder().blob(blob).type(type).projectId(projectId).userId(userId).build());
    }

    @Override
    public Policy get(String policyId) {
        Objects.requireNonNull(policyId);
        return get(KeystonePolicy.class, PATH_POLICIES, "/", policyId).execute();
    }

    @Override
    public Policy update(Policy policy) {
        Objects.requireNonNull(policy);
        return patch(KeystonePolicy.class, PATH_POLICIES, "/", policy.getId()).entity(policy).execute();
    }

    @Override
    public ActionResponse delete(String policyId) {
        Objects.requireNonNull(policyId);
        return deleteWithResponse(PATH_POLICIES, "/", policyId).execute();
    }

    @Override
    public List<? extends Policy> list() {
        return get(Policies.class, uri(PATH_POLICIES)).execute().getList();
    }

}
