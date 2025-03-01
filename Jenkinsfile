pipeline {
    agent any

    tools {
        maven 'maven' // Ensure Jenkins uses the right Maven installation
    }

    options {
        skipDefaultCheckout() // Avoids Jenkins auto-checkout
    }

    environment {
        GIT_REPO = 'git@github.com:rydnr/java-commons.git'
        CREDENTIALS_ID = 'github' // Jenkins credentials ID for SSH key
        OWNER = 'rydnr'
        GROUP_ID = 'org.acmsl'
        PARENT_ARTIFACT_ID = 'acmsl-pom'
        ARTIFACT_ID = 'java-commons'
        MAVEN_REPO = 'http://maven.acm-sl.org/repository/maven-snapshots-local'
        LOCAL_VERSION_FILE = "${WORKSPACE}/.last_snapshot_versions"
        GIT_SSH_COMMAND = "ssh -i ~/.ssh/id_rsa-github-jenkins -o IdentitiesOnly=yes"
        ORIGIN_BRANCH = "master"
    }

    stages {
        stage('Ensure Parent POM is Available') {
            steps {
                script {
                    sshagent(credentials: [env.CREDENTIALS_ID]) {
                        sh 'git config --global core.sshCommand "ssh -o IdentitiesOnly=yes -i ~/.ssh/id_rsa-github-jenkins"'
                        sh '''
                            if ! /home/jenkins/.sdkman/candidates/maven/current/bin/mvn dependency:get -DgroupId=org.acmsl -DartifactId=acmsl-pom -Dversion=latest-SNAPSHOT -Dpackaging=pom; then
                                echo "Parent POM not found in remote repository, installing locally..."
                                git clone git@github.com:rydnr/acmsl-pom.git /tmp/$$
                                cd /tmp/$$
                                mvn clean install
                                rm -rf /tmp/$$
                            fi
                        '''
                    }
                }
            }
        }

        stage('Checkout') {
            steps {
                script {
                    sshagent(credentials: [env.CREDENTIALS_ID]) {
                        sh 'git config --global core.sshCommand "ssh -o IdentitiesOnly=yes -i ~/.ssh/id_rsa-github-jenkins"'
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: '*/master']],
                            doGenerateSubmoduleConfigurations: false,
                            extensions: [
                                [$class: 'WipeWorkspace'], // Wipes workspace before cloning
                                [$class: 'CloneOption', depth: 0, noTags: false, reference: '', shallow: false]
                            ],
                            submoduleCfg: [],
                            userRemoteConfigs: [[
                                url: env.GIT_REPO,
                                credentialsId: env.CREDENTIALS_ID
                            ]]
                        ])
                    }
                }
            }
        }

        stage('Build and Deploy') {
            steps {
                withMaven(maven: 'maven') {  // Enables Jenkins to track SNAPSHOT dependencies
                    sh '''
                        cd "${WORKSPACE}";
                        mvn clean deploy
                    '''
                }
            }
        }

        stage('Trigger _get-new-version') {
            steps {
                build job: '_get-new-version', wait: true, parameters: [
                    string(name: 'trigger', value: "${OWNER}.${ARTIFACT_ID}")
                ]
            }
        }

        stage('Update Version & Tag Release') {
            steps {
                script {
                    sh '''
                        cd "${WORKSPACE}";

                        export NEW_VERSION="$(cat "${WORKSPACE}"/target/.version)";
                        export NEW_GIT_TAG="${ARTIFACT_ID}-${NEW_VERSION}";

                        mvn versions:set -DnewVersion="${NEW_VERSION}"
                        xmlstarlet ed --inplace -N x="http://maven.apache.org/POM/4.0.0" -u "/x:project/x:parent/x:version" -v "${NEW_VERSION}" pom.xml

                        /usr/local/bin/prepare-release.sh "${GROUP_ID}" "${ARTIFACT_ID}" "${NEW_VERSION}" "${GIT_REPO}"

                        git add pom.xml
                        git commit -m "Releasing ${ARTIFACT_ID}: ${NEW_VERSION}"
                        git tag -a "${NEW_GIT_TAG}" -m "Releasing ${ARTIFACT_ID}: ${NEW_VERSION}"
                        git push origin --tags
                    '''
                }
            }
        }

        stage('Maven Release') {
            steps {
                withMaven(maven: 'maven') {
                    sh '''
                        cd "${WORKSPACE}";
                        mvn -Prelease release:perform
                    '''
                }
            }
        }

        stage('Reset to latest-SNAPSHOT') {
            steps {
                script {
                    sh '''
                        cd "${WORKSPACE}";

                        mvn versions:set -DnewVersion="latest-SNAPSHOT"
                        xmlstarlet ed --inplace -N x="http://maven.apache.org/POM/4.0.0" -u "/x:project/x:parent/x:version" -v "latest-SNAPSHOT" pom.xml

                        git add pom.xml
                        git commit -m "Pointing ${GROUP_ID}/${ARTIFACT_ID} to latest-SNAPSHOT again"

                        git push origin HEAD:${ORIGIN_BRANCH}
                    '''
                }
            }
        }
    }
}
