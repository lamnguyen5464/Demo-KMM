# clear 
cd ios && rm -rf Pods && rm -rf Podfile.lock && cd ..

ln -s android/kmmshared kmmshared
yarn
adb reverse tcp:8081 tcp:8081
cd ios && pod install && cd .. ||
yarn start