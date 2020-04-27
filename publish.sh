#!/bin/bash

#echo $GPG_KEY_CONTENTS | base64 -d > /secret.gpg
./gradlew -PnewVersion=0.0.2 eoandroidconcurrency:clean
./gradlew -PnewVersion=0.0.2 eoandroidconcurrency:assemble
./gradlew -PnewVersion=0.0.2 eoandroidconcurrency:publishReleasePublicationToSonatypeRepository
./gradlew closeAndReleaseRepository
#./gradlew eoandroidworkmanager:clean
#./gradlew eoandroidworkmanager:assemble
#./gradlew eoandroidworkmanager:publishReleasePublicationToSonatypeRepository
./gradlew -PnewVersion=0.0.2 eorest:clean
./gradlew -PnewVersion=0.0.2 eorest:jar
./gradlew -PnewVersion=0.0.2 eorest:publishReleasePublicationToSonatypeRepository
./gradlew closeAndReleaseRepository
