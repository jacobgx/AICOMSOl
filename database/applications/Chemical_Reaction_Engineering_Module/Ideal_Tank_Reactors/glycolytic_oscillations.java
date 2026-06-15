/*
 * glycolytic_oscillations.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:18 by COMSOL 6.3.0.290. */
public class glycolytic_oscillations {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Ideal_Tank_Reactors");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("J0", "50[mM/min]");
    model.param().set("k1", "550[1/(mM*min)]");
    model.param().set("Ki", "1[mM]");
    model.param().set("k2", "9.8[1/min]");
    model.param().set("kGAPDHp", "328.8[1/(mM*min)]");
    model.param().set("kGAPDHm", "57823.1[1/(mM*min)]");
    model.param().set("kPGKp", "76411.1[1/(mM*min)]");
    model.param().set("kPGKm", "23.7[1/(mM*min)]");
    model.param().set("k4", "80[1/(mM*min)]");
    model.param().set("k5", "9.7[1/min]");
    model.param().set("k6", "2000[1/(mM*min)]");
    model.param().set("k7", "28[1/min]");
    model.param().set("k8", "85.7[1/(mM*min)]");
    model.param().set("kappa", "375[1/min]");
    model.param().set("phi", "0.1");
    model.param().set("A", "4[mM]");
    model.param().set("N", "1[mM]");
    model.param().set("n", "4");
    model.param().set("k9", "80[1/min]");
    model.param().set("A30", "1.65[mM]");
    model.param().set("N20", "0.5[mM]");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("f_A3", "(1+(re.c_A3/Ki)^n)^-1");
    model.component("comp1").variable("var1").set("v3_denom", "(kGAPDHm*re.c_N2+kPGKp*(A-re.c_A3))");
    model.component("comp1").variable("var1").set("k3fwd", "kGAPDHp*kPGKp/v3_denom");
    model.component("comp1").variable("var1").set("k3rev", "kGAPDHm*kPGKm/v3_denom");

    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "S1+2A3=>S2+2A2");
    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1").set("r", "k1*re.c_S1*re.c_A3*f_A3");
    model.component("comp1").physics("re").feature("rch1").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re").create("add1", "AdditionalSourceFeature", -1);
    model.component("comp1").physics("re").feature("add1").label("\u9644\u52a0\u6e90 - J0");
    model.component("comp1").physics("re").feature("add1").setIndex("AddR", "J0", 2, 0);
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "S2=>2S3");
    model.component("comp1").physics("re").feature("rch2").set("kf", "k2");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "A2+N1+S3<=>A3+N2+S4");
    model.component("comp1").physics("re").feature("rch3").set("kf", "k3fwd");
    model.component("comp1").physics("re").feature("rch3").set("kr", "k3rev");
    model.component("comp1").physics("re").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch4").set("formula", "S4+A2=>S5+A3");
    model.component("comp1").physics("re").feature("rch4").set("kf", "k4");
    model.component("comp1").physics("re").create("rch5", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch5").set("formula", "S5=>S6");
    model.component("comp1").physics("re").feature("rch5").set("kf", "k5");
    model.component("comp1").physics("re").create("rch6", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch6").set("formula", "S6+N2=>N1");
    model.component("comp1").physics("re").feature("rch6").set("kf", "k6");
    model.component("comp1").physics("re").create("rch7", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch7").set("formula", "A3=>A2");
    model.component("comp1").physics("re").feature("rch7").set("kf", "k7");
    model.component("comp1").physics("re").create("rch8", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch8").set("formula", "S3+N2=>N1");
    model.component("comp1").physics("re").feature("rch8").set("kf", "k8");
    model.component("comp1").physics("re").create("rch9", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch9").set("formula", "S6ex=>0S6ex");
    model.component("comp1").physics("re").feature("rch9").set("kf", "k9");
    model.component("comp1").physics("re").create("rch10", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch10").set("formula", "S6<=>0.1S6ex");
    model.component("comp1").physics("re").feature("rch10").label("J: S6<=>0.1S6ex");
    model.component("comp1").physics("re").feature("rch10").set("r", "kappa*(re.c_S6-re.c_S6ex)");
    model.component("comp1").physics("re").feature("rch10").set("bulkFwdOrder", 1);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "A-A30", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "A30", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "N-N20", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "N20", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 1.09, 4, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 5.1, 5, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 0.55, 6, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 0.66, 7, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 8.31, 8, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 0.08, 9, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", 0.02, 10, 0);

    model.study("std1").feature("time").set("tlist", "range(0,0.01,5)");
    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_S1", "re.c_A3", "re.c_S2", "re.c_A2", "re.c_S3", "re.c_N1", "re.c_N2", "re.c_S4", "re.c_S5", "re.c_S6", 
         "re.c_S6ex"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", 
         "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("linestyle", "cycle");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6 (re) ATP \u548c NADH vs. \u65f6\u95f4");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "ATP \u548c NADH \u7684\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0.01);
    model.result("pg2").set("xmax", 0.5);
    model.result("pg2").set("ymin", 0);
    model.result("pg2").set("ymax", 4);
    model.result("pg2").set("yminsec", 0);
    model.result("pg2").set("showgrid", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("expr", new String[]{});
    model.result("pg2").feature("glob1").set("descr", new String[]{});
    model.result("pg2").feature("glob1").setIndex("expr", "re.c_A3", 0);
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg2").feature("glob2").setIndex("expr", "re.c_N2", 0);
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "re.c_N2 (NADH)", 0);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linewidth", 3);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "re.c_A3 (ATP)", 0);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u8d28\u91cf\u5b88\u6052 A \u548c N");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").setIndex("expr", "re.c_N1+re.c_N2-N", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "re.c_A2+re.c_A3-A", 1);
    model.result("pg3").run();

    model.title("\u4ee3\u8c22\u53cd\u5e94\u7f51\u7edc\u4e2d\u7684\u632f\u8361");

    model
         .description("\u5728\u4e00\u5b9a\u6761\u4ef6\u4e0b\uff0c\u7cd6\u9175\u89e3\u7684\u53cd\u5e94\u901f\u7387\u53ef\u80fd\u8868\u73b0\u51fa\u4e00\u4e2a\u6781\u9650\u5faa\u73af\uff0c\u5176\u4e2d\u6d53\u5ea6\u4ee5\u5316\u5b66\u52a8\u529b\u5b66\u4e2d\u4e0d\u5e38\u89c1\u7684\u65b9\u5f0f\u5468\u671f\u6027\u53d8\u5316\u3002\u672c\u4f8b\u4f7f\u7528\u6587\u732e\u4e2d\u7684\u96c6\u603b\u52a8\u529b\u5b66\u6a21\u578b\u6765\u7814\u7a76\u8461\u8404\u7cd6\u7684\u6d88\u8017\u901f\u7387\u5982\u4f55\u968f\u8f85\u9176 NADH \u548c\u80fd\u91cf\u8f7d\u4f53 ATP \u800c\u53d8\u5316\u3002\u5728\u5747\u5300\u7cfb\u7edf\u4e2d\u7814\u7a76\u6d53\u5ea6\u6ce2\u52a8\u7684\u65f6\u95f4\u6f14\u5316\uff0c\u5e76\u7528\u9690\u5f0f\u65b9\u5f0f\u5904\u7406\u8de8\u7ec6\u80de\u819c\u7684\u8f6c\u8fd0\u8fc7\u7a0b\u3002");

    model.label("glycolytic_oscillations.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
