#!/bin/bash

#echo $GPG_KEY_CONTENTS | base64 -d > ./secret.gpg
#./gradlew eoandroidconcurrency:assemble
#./gradlew eoandroidconcurrency:publishReleasePublicationToSonatypeRepository
./gradlew eorest:jar
./gradlew eorest:publishReleasePublicationToSonatypeRepository
#./gradlew closeAndReleaseRepository
