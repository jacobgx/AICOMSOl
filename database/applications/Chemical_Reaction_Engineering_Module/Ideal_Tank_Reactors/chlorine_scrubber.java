/*
 * chlorine_scrubber.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:17 by COMSOL 6.3.0.290. */
public class chlorine_scrubber {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Ideal_Tank_Reactors");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Temp", "273.14[K]", "\u6d17\u6da4\u5854\u6e29\u5ea6");
    model.param().set("cCl_0", "1e-11[mol/m^3]", "Cl \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cCl2_0", "1e-2[mol/m^3]", "Cl2 \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cClO_0", "1e-11[mol/m^3]", "ClO \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cH_0", "1e-6[mol/m^3]", "H \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cH2O_0", "55500[mol/m^3]", "H2O \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cHClO_0", "1e-11[mol/m^3]", "HClO \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cOH_0", "1e-2[mol/m^3]", "OH \u521d\u59cb\u6d53\u5ea6");
    model.param().set("kfreac_1", "1.565e6[m^3/(s*mol)]", "\u53cd\u5e94 1 \u7684\u6b63\u53cd\u5e94\u5e38\u6570");
    model.param()
         .set("krreac_1", "3.485e-5[m^3/(s*mol)]", "\u53cd\u5e94 1 \u7684\u53ef\u9006\u53cd\u5e94\u5e38\u6570");
    model.param().set("Kequi_2", "1e8*cH2O_0[m^3/mol]", "\u53cd\u5e94 2 \u7684\u5e73\u8861\u5e38\u6570");
    model.param().set("Kequi_3", "2.79e3*cH2O_0[m^3/mol]", "\u53cd\u5e94 3 \u7684\u5e73\u8861\u5e38\u6570");
    model.param()
         .set("kfreac_4", "1.648e1[m^3/(s*mol)]/cH2O_0[m^3/mol]", "\u53cd\u5e94 4 \u7684\u6b63\u53cd\u5e94\u5e38\u6570");
    model.param()
         .set("krreac_4", "3.7e-10[m^9/(s*mol^3)]", "\u53cd\u5e94 4 \u7684\u53ef\u9006\u53cd\u5e94\u5e38\u6570");

    model.component("comp1").physics("re").prop("energybalance").set("T", "Temp");
    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "Cl2 + OH- <=> HClO + Cl-");
    model.component("comp1").physics("re").feature("rch1").set("kf", "kfreac_1");
    model.component("comp1").physics("re").feature("rch1").set("kr", "krreac_1");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "OH- + H+ = H2O");
    model.component("comp1").physics("re").feature("rch2").set("Keq0", "Kequi_2");
    model.component("comp1").physics("re").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "HClO + OH- = ClO- + H2O");
    model.component("comp1").physics("re").feature("rch3").set("Keq0", "Kequi_3");
    model.component("comp1").physics("re").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch4").set("formula", "Cl2 + H2O <=> ClO- + Cl- + 2 H+");
    model.component("comp1").physics("re").feature("rch4").set("kf", "kfreac_4");
    model.component("comp1").physics("re").feature("rch4").set("kr", "krreac_4");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cCl_0", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cCl2_0", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cClO_0", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cH_0", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cH2O_0", 4, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cHClO_0", 5, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cOH_0", 6, 0);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_Cl2", "re.c_Cl_1m", "re.c_H_1p", "re.c_ClO_1m", "re.c_OH_1m", "re.c_HClO"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlog", true);
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_Cl2"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_Cl2", "re.c_OH_1m"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_Cl2", "re.c_OH_1m", "re.c_H_1p"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "Cl<sub>2</sub>", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "OH<sup>-</sup>", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "H<sup>+</sup>", 2);
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u6d17\u6da4\u5854\u4e2d\u7684\u6c2f\u6c14\u4e2d\u548c");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u6d17\u6da4\u5854\u4e2d\u6c2f\u6c14\u5728\u6c34\u6eb6\u6db2\u4e2d\u53d1\u751f\u4e2d\u548c\u7684\u52a8\u529b\u5b66\u3002\u5efa\u6a21\u8fc7\u7a0b\u5305\u62ec\u56db\u4e2a\u5e73\u8861\u53cd\u5e94\uff0c\u5e76\u5047\u5b9a\u6d17\u6da4\u5854\u4e2d\u7684\u6d41\u4f53\u4f53\u79ef\u6052\u5b9a\u4e14\u5b8c\u5168\u6df7\u5408\u3002");

    model.label("chlorine_scrubber.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
