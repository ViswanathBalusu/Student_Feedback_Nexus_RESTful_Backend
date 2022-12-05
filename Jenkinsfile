void setBuildStatus(String message, String state) {
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/ViswanathBalusu/SWE645_Assignment_RESTAPI"],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "CI/Jenkins"],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
  ]);
}

pipeline {

  agent any

  environment {
    GITHUB_TOKEN_ID='github_pat'
    DOCKER_REGISTRY_URL = 'https://ghcr.io'
    DOCKER_REGISTRY = 'ghcr.io'
    DOCKER_USER = 'viswanathbalusu'
    DOCKER_IMAGE = 'swe645assignment3rest'
    DOCKER_IMAGE_TAG = 'latest'
    GCP_SA = 'swe645assignment3'
    GCP_PROJECT_ID = 'swe645assignment2'
    GCP_CLUSTER = 'swe645cluster'
    GCP_CLUSTER_LOCATION = 'us-east1-b'
    K8S_MANIFEST_FILE = 'k8smanifest.yml'
  }

  stages {

    stage("Build Docker image") {
          steps {
              setBuildStatus("Building Docker Image", "PENDING")
              script {
                  docker_image = docker.build("${env.DOCKER_REGISTRY}/${env.DOCKER_USER}/${env.DOCKER_IMAGE}:ci-build-${env.BUILD_ID}")
              }
          }
    }

    stage("Push the image to GHCR") {
          steps {
                setBuildStatus("Pushing Docker Image to GHCR", "PENDING")
                script {
                    docker.withRegistry(env.DOCKER_REGISTRY_URL, env.GITHUB_TOKEN_ID) {
                            docker_image.push(env.DOCKER_IMAGE_TAG)
                            docker_image.push("ci-build-${env.BUILD_ID}")
                    }
                }
          }
      }


    stage('Deploy App To K8s Cluster') {
      steps {
        setBuildStatus("Pushing Changes to K8s Cluster", "PENDING")
        sh "sed -i \"s/${env.DOCKER_IMAGE}:${env.DOCKER_IMAGE_TAG}/${env.DOCKER_IMAGE}:ci-build-${env.BUILD_ID}/g\" ${env.K8S_MANIFEST_FILE}"
        step(
          [
            $class: 'KubernetesEngineBuilder',
            projectId: env.GCP_PROJECT_ID,
            clusterName: env.GCP_CLUSTER,
            zone: env.GCP_CLUSTER_LOCATION,
            manifestPattern: env.K8S_MANIFEST_FILE,
            credentialsId: env.GCP_SA,
            verifyDeployments: true
            ]
        )
        setBuildStatus("Changes Pushed to K8s Cluster", "SUCCESS")
        build job:'SWE645_Assignment3_Angular'
        setBuildStatus("Handing Off the Job to Frontend", "SUCCESS")
      }
    }

  }

}