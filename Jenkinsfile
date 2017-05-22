pipeline {
  agent any
  stages {
    stage('Push Master') {
      steps {
        git(url: 'https://github.com/mozinrat/cleartax', branch: 'master', changelog: true, poll: true)
      }
    }
  }
}