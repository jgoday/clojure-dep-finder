# clojure-dep-finder

A Clojure app to find dependencies in clojars.
It modifies leiningen project.clj file with the new dependency included.

## Options
 Switches               Default  Desc
 --------               -------  ----
 -h, --no-help, --help  false    Print this help
 -n, -no-write          false    Don't overwrite project.clj file
 -d, --no-desc, --desc  false    Show description

## Usage
java -jar clojure-dep-finder.jar korma -n
or
lein run -- korma -n


## License

Copyright © 2014 Javier Goday

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
