function fn() {
  karate.configure('connectTimeout', 500000);
  karate.configure('readTimeout', 500000);
  karate.configure('ssl', true);

  var envData = read('../configurations/paths.json');

  var config = {
        urlPerson: '',
        urlCourse: ''
  }

  config.urlPerson = envData.urlPerson;
  config.urlCourse = envData.urlCourse;

  return config;
}