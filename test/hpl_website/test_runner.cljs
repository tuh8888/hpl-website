(ns hpl-website.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [hpl-website.core-test]
   [hpl-website.common-test]))

(enable-console-print!)

(doo-tests 'hpl-website.core-test
           'hpl-website.common-test)
