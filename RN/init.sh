# clear 
cd ios && rm -rf Pods && rm -rf Podfile.lock && cd ..
rm -rf kmmshared
rm -rf node_modules 

ln -s android/kmmshared kmmshared 
yarn 
adb reverse tcp:8081 tcp:8081 
cd ios && pod install && cd .. 
yarn start