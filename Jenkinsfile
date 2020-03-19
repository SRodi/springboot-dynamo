node {
    git branch: 'push-image', credentialsId: 'github', url: 'https://github.com/SRodi/springboot-dynamo.git'
    withDockerRegistry(credentialsId: 'gcr:pulumi-259310', toolName: 'docker', url: 'https://gcr.io'){
        def newApp = docker.build "gcr.io/pulumi-259310/sr-springboot-dynamodb:v"+env.BUILD_NUMBER
        newApp.push()
    }
}