/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
} from 'react-native';
const App = () => {
  const [timeStart, setTimeStart] = React.useState();
  const [timeEnd, setTimeEnd] = React.useState();

  React.useEffect(() => {
    setTimeStart(new Date());
    fetch(
      'https://miniapp.dev.mservice.io/rigver-appversion/v1/features?last_update=0',
    ).then(res => {
      res.json().then(res => {
        setTimeEnd(new Date());
      });
    });
  }, []);

  return (
    <SafeAreaView style={styles.main}>
      <Text>Time start requesting: {JSON.stringify(timeStart) || '...'}</Text>
      <Text>Time end requesting: {JSON.stringify(timeEnd) || '...'}</Text>
      <Text>
        Duration:{' '}
        {JSON.stringify((timeEnd?.getTime() - timeStart?.getTime()) / 1000) ||
          '...'}
      </Text>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  main: {
    flex: 1,
    justifyContent: 'center',
  },
});

export default App;
