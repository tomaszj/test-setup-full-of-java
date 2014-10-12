/* Exports a function which returns an object that overrides the default &
 *   plugin file patterns (used widely through the app configuration)
 *
 * To see the default definitions for Lineman's file paths and globs, see:
 *
 *   - https://github.com/linemanjs/lineman/blob/master/config/files.coffee
 */
module.exports = function(lineman) {
  //Override file patterns here
  return {
    js: {
      vendor: [
        "vendor/bower/jquery/dist/jquery.min.js",
        "vendor/bower/bootstrap/dist/js/bootstrap.js",
        "vendor/js/angular.js",
        "vendor/js/**/*.js"
      ],
      app: [
        "app/js/app.js",
        "app/js/**/*.js"
      ]
    },

    less: {
      compile: {
        options: {
          paths: [
            "vendor/bower/bootstrap/dist/css/bootstrap.min.css",
            "vendor/bower/bootstrap/dist/css/bootstrap-theme.min.css",
            "vendor/css/**/*.css",
            "app/css/**/*.less"
          ]
        }
      }
    }
  };
};
