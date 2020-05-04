#!/bin/bash

#echo $GPG_KEY_CONTENTS | base64 -d > /secret.gpg
./gradlew -PnewVersion=0.0.4 eoandroidconcurrency:clean
./gradlew -PnewVersion=0.0.4 eoandroidconcurrency:assemble
./gradlew -PnewVersion=0.0.4 eoandroidconcurrency:publish eoandroidconcurrency:closeAndReleaseRepository
#./gradlew -PnewVersion=0.0.4 eoandroidworkmanager:clean
#./gradlew -PnewVersion=0.0.4 eoandroidworkmanager:assemble
#./gradlew -PnewVersion=0.0.4 eoandroidworkmanager:publish eoandroidworkmanager:closeAndReleaseRepository
./gradlew -PnewVersion=0.0.4 eorest:clean
./gradlew -PnewVersion=0.0.4 eorest:jar
./gradlew -PnewVersion=0.0.4 eorest:publish eorest:closeAndReleaseRepository
