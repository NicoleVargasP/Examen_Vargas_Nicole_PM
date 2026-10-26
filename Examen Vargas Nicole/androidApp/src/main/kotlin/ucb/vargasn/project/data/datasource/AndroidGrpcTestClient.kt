package ucb.vargasn.project.data.datasource

import com.develoop.logs.LogApi
import com.google.protobuf.ByteString
import ucb.vargasn.project.domain.repository.GrpcTestClient

class AndroidGrpcTestClient : GrpcTestClient {
    override suspend fun sendTestLog(): Boolean {
        val logData = LogApi.LogData.newBuilder()
            .setAndroidId(ByteString.copyFromUtf8("abc123"))
            .setAppInstanceId(ByteString.copyFromUtf8("instance_001"))
            .setLogLevel(LogApi.ELogLevel.LEVEL_ERROR)
            .setMessage("Diagnostic Test Log via ngrok")
            .setServerTimeStamp(System.currentTimeMillis())
            .setMobileTimeStamp(System.currentTimeMillis())
            .setVersionCode(1)
            .setUserId("user_test")
            .build()

        val request = LogApi.LogRequest.newBuilder()
            .addLogs(logData)
            .build()

        val dataSource = LogsRemoteDataSource(
            host = "0.tcp.sa.ngrok.io",
            port = 28082
        )
        return try {
            val response = dataSource.send(request)
            dataSource.shutdown()
            response.resultCode == LogApi.ELogResultCode.LOG_RESULT_OK
        } catch (e: Exception) {
            dataSource.shutdown()
            throw e
        }
    }
}
