pipeline {
  agent any
  stages {
    stage('Push Master') {
      steps {
        git(url: 'https://github.com/mozinrat/cleartax', branch: 'master', changelog: true, poll: true)
        cleanWs(cleanWhenAborted: true, cleanWhenFailure: true, cleanWhenSuccess: true, cleanWhenNotBuilt: true, cleanWhenUnstable: true, cleanupMatrixParent: true, deleteDirs: true, notFailBuild: true)
      }
    }
  }
}