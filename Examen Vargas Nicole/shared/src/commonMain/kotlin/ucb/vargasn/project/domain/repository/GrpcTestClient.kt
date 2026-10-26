package ucb.vargasn.project.domain.repository

interface GrpcTestClient {
    suspend fun sendTestLog(): Boolean
}
