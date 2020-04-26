#!/bin/bash

#echo $GPG_KEY_CONTENTS | base64 -d > /secret.gpg
./gradlew -PnewVersion=0.0.3 eoandroidconcurrency:clean
./gradlew -PnewVersion=0.0.3 eoandroidconcurrency:assemble
./gradlew -PnewVersion=0.0.3 eoandroidconcurrency:publishReleasePublicationToSonatypeRepository
./gradlew closeAndReleaseRepository
#./gradlew eoandroidworkmanager:clean
#./gradlew eoandroidworkmanager:assemble
#./gradlew eoandroidworkmanager:publishReleasePublicationToSonatypeRepository
./gradlew -PnewVersion=0.0.3 eorest:clean
./gradlew -PnewVersion=0.0.3 eorest:jar
./gradlew -PnewVersion=0.0.3 eorest:publishReleasePublicationToSonatypeRepository
./gradlew closeAndReleaseRepository
