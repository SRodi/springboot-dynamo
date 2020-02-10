node {
  git branch: 'multicloud', credentialsId: 'github', url: 'https://github.com/SRodi/springboot-dynamo.git'
  withDockerRegistry(credentialsId: 'gcr:pulumi-259310', toolName: 'docker', url: 'https://gcr.io'){
    def newApp = docker.build "gcr.io/pulumi-259310/sr-springboot-dynamodb:v1"
    newApp.push()
  }
}