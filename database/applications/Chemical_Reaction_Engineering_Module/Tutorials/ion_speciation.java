/*
 * ion_speciation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:29 by COMSOL 6.3.0.290. */
public class ion_speciation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "H2O=H++OH-");
    model.component("comp1").physics("re").feature("rch1").set("Keq0", "1.80505415e-16[M]");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "NH4+=H++NH3");
    model.component("comp1").physics("re").feature("rch2").set("Keq0", "5.49540874e-10[M]");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "CuNH3++=NH3+Cu++");
    model.component("comp1").physics("re").feature("rch3").set("Keq0", "5.01187234e-05[M]");
    model.component("comp1").physics("re").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch4").set("formula", "Cu(NH3)2++=NH3+CuNH3++");
    model.component("comp1").physics("re").feature("rch4").set("Keq0", "5.01187234e-05[M]");
    model.component("comp1").physics("re").create("rch5", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch5").set("formula", "Cu(NH3)3++=NH3+Cu(NH3)2++");
    model.component("comp1").physics("re").feature("rch5").set("Keq0", "0.00125893[M]");
    model.component("comp1").physics("re").create("rch6", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch6").set("formula", "Cu(NH3)4++=NH3+Cu(NH3)3++");
    model.component("comp1").physics("re").feature("rch6").set("Keq0", "0.00630957[M]");
    model.component("comp1").physics("re").create("rch7", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch7").set("formula", "Cu(NH3)5++=NH3+Cu(NH3)4++");
    model.component("comp1").physics("re").feature("rch7").set("Keq0", "3.98107171[M]");
    model.component("comp1").physics("re").create("rch8", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch8").set("formula", "2Cu++ + OH- + H2O = Cu2(OH)2++ + H+");
    model.component("comp1").physics("re").feature("rch8").set("Keq0", "45.34091032[M^-2]");

    model.component("comp1").variable().create("var1");

    model.param().set("init_conc_H2O", "55.4[M]");
    model.param().descr("init_conc_H2O", "From density and molar mass");
    model.param().set("init_conc_Cu", "0.01[M]");
    model.param().descr("init_conc_Cu", "Initial concentration copper");
    model.param().set("init_conc_NH3", "1e-12[M]");
    model.param().descr("init_conc_NH3", "Initial concentration ammonia");
    model.param().set("src_rate_NH3", "1e-3[M/s]");
    model.param().descr("src_rate_NH3", "Rate of [NH3] addition");
    model.param().set("t0", "init_conc_NH3/src_rate_NH3");
    model.param().descr("t0", "First output time");
    model.param().set("final_conc_NH3", "1[M]");
    model.param().descr("final_conc_NH3", "Final total concentration ammonia");
    model.param().set("tend", "final_conc_NH3/src_rate_NH3");
    model.param().descr("tend", "Final output time");

    model.component("comp1").variable("var1")
         .set("c_sum_ammines", "re.c_CuNH32_2p+re.c_CuNH33_2p+re.c_CuNH34_2p+re.c_CuNH35_2p+re.c_CuNH3_2p");
    model.component("comp1").variable("var1").descr("c_sum_ammines", "Ammine complexes");
    model.component("comp1").variable("var1").set("c_sum_hydroxides", "2*re.c_Cu2OH2_2p");
    model.component("comp1").variable("var1").descr("c_sum_hydroxides", "Hydroxy complexes");
    model.component("comp1").variable("var1").set("c_sum_Cu_tot", "re.c_Cu_2p + c_sum_hydroxides + c_sum_ammines");
    model.component("comp1").variable("var1").descr("c_sum_Cu_tot", "Total conc. copper");
    model.component("comp1").variable("var1")
         .set("c_sum_NH3_tot", "re.c_NH4_1p+re.c_NH3+re.c_CuNH3_2p+2*re.c_CuNH32_2p+3*re.c_CuNH33_2p+4*re.c_CuNH34_2p+5*re.c_CuNH35_2p");
    model.component("comp1").variable("var1").descr("c_sum_NH3_tot", "Total conc. ammonia");
    model.component("comp1").variable("var1").set("c_ref_NH3_tot", "init_conc_NH3 + (t-t0)*src_rate_NH3");
    model.component("comp1").variable("var1").descr("c_ref_NH3_tot", "Total conc. ammonia (ref)");
    model.component("comp1").variable("var1").set("c_sum_H1p_tot", "re.c_H_1p+re.c_NH4_1p+re.c_H2O");
    model.component("comp1").variable("var1").descr("c_sum_H1p_tot", "Total conc. protons");
    model.component("comp1").variable("var1").set("c_sum_OH1m_tot", "re.c_OH_1m+re.c_H2O+2*re.c_Cu2OH2_2p");
    model.component("comp1").variable("var1").descr("c_sum_OH1m_tot", "Total conc. hydroxide");

    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "init_conc_Cu", 4, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "init_conc_H2O", 8, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "init_conc_NH3", 9, 0);
    model.component("comp1").physics("re").feature("inits1").set("preEquilibrationEnabled", true);
    model.component("comp1").physics("re").create("add1", "AdditionalSourceFeature", -1);
    model.component("comp1").physics("re").feature("add1").setIndex("AddR", "src_rate_NH3", 9, 0);

    model.study("std1").feature("time").set("tlist", "10^range(log10(t0/1[s]),0.5,log10(tend/1[s]))");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-6");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "0.1*t0");
    model.sol("sol1").feature("t1").set("consistent", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_H_1p", "re.c_OH_1m", "re.c_NH3", "re.c_CuNH32_2p", "re.c_CuNH33_2p", "re.c_CuNH3_2p", "re.c_CuNH34_2p", "re.c_CuNH35_2p", "re.c_Cu_2p", "re.c_H2O", 
         "re.c_NH4_1p", "re.c_Cu2OH2_2p"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", 
         "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", "1e-3");
    model.result("pg1").set("xmax", "1e3");
    model.result("pg1").set("ymin", "1e-9");
    model.result("pg1").set("ymax", "1e5");
    model.result("pg1").set("xlog", true);
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "H<sup>+</sup>", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "OH<sup>-</sup>", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "NH<sub>3</sub>", 2);
    model.result("pg1").feature("glob1").setIndex("legends", "Cu(NH<sub>3</sub>)<sub>2</sub> <sup>2+</sup>", 3);
    model.result("pg1").feature("glob1").setIndex("legends", "Cu(NH<sub>3</sub>)<sub>3</sub> <sup>2+</sup>", 4);
    model.result("pg1").feature("glob1").setIndex("legends", "CuNH<sub>3</sub> <sup>2+</sup>", 5);
    model.result("pg1").feature("glob1").setIndex("legends", "Cu(NH<sub>3</sub>)<sub>4</sub> <sup>2+</sup>", 6);
    model.result("pg1").feature("glob1").setIndex("legends", "Cu(NH<sub>3</sub>)<sub>5</sub> <sup>2+</sup>", 7);
    model.result("pg1").feature("glob1").setIndex("legends", "Cu<sup>2+</sup>", 8);
    model.result("pg1").feature("glob1").setIndex("legends", "H<sub>2</sub>O", 9);
    model.result("pg1").feature("glob1").setIndex("legends", "NH<sub>4</sub> <sup>+</sup>", 10);
    model.result("pg1").feature("glob1").setIndex("legends", "Cu<sub>2</sub>(OH)<sub>2</sub> <sup>2+</sup>", 11);
    model.result("pg1").feature("glob1").set("linestyle", "cycle");
    model.result("pg1").feature("glob1").set("colorcycle", "long");
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").set("expr", new String[]{});
    model.result("pg1").feature("glob2").set("descr", new String[]{});
    model.result("pg1").feature("glob2").setIndex("expr", "c_sum_NH3_tot", 0);
    model.result("pg1").feature("glob2").set("linecolor", "gray");
    model.result("pg1").feature("glob2").set("linewidth", 5);
    model.result("pg1").feature().move("glob2", 0);
    model.result("pg1").feature("glob2").set("autodescr", true);
    model.result("pg1").feature("glob2").set("autoexpr", false);
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "c_sum_H1p_tot/init_conc_H2O-1", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "H+", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "c_sum_OH1m_tot/init_conc_H2O-1", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "OH-", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "c_sum_NH3_tot/c_ref_NH3_tot-1", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "NH3", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "c_sum_Cu_tot/init_conc_Cu-1", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Cu", 3);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").clearTableData();
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg2").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("linestyle", "cycle");
    model.result("pg2").feature("tblp1").set("linewidth", 2);
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("ylogsec", true);
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", "1e-3");
    model.result("pg3").set("xmax", "1e2");
    model.result("pg3").set("ymin", 0);
    model.result("pg3").set("yminsec", "1e-3");
    model.result("pg3").set("ymaxsec", "1e2");
    model.result("pg3").set("legendpos", "middleleft");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").setIndex("expr", "c_sum_NH3_tot", 0);
    model.result("pg3").feature("glob1").set("plotonsecyaxis", true);
    model.result("pg3").feature("glob1").set("linecolor", "gray");
    model.result("pg3").feature("glob1").set("linewidth", 5);
    model.result("pg3").feature("glob1").set("autodescr", true);
    model.result("pg3").feature("glob1").set("autoexpr", false);
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").set("expr", new String[]{});
    model.result("pg3").feature("glob2").set("descr", new String[]{});
    model.result("pg3").feature("glob2").setIndex("expr", "c_sum_ammines/c_sum_Cu_tot", 0);
    model.result("pg3").feature("glob2").setIndex("descr", "Ammine complexes", 0);
    model.result("pg3").feature("glob2").setIndex("expr", "c_sum_hydroxides/c_sum_Cu_tot", 1);
    model.result("pg3").feature("glob2").setIndex("descr", "Hydroxy complexes", 1);
    model.result("pg3").feature("glob2").setIndex("expr", "re.c_Cu_2p/c_sum_Cu_tot", 2);
    model.result("pg3").feature("glob2").setIndex("descr", "Free cupric", 2);
    model.result("pg3").feature("glob2").set("linestyle", "cycle");
    model.result("pg3").feature("glob2").set("linewidth", 2);
    model.result("pg3").feature("glob2").set("autodescr", true);
    model.result("pg3").feature("glob2").set("autoexpr", false);
    model.result("pg3").run();

    model.title("\u6c28\u6eb6\u6db2\u4e2d\u7684\u9178\u78b1\u5e73\u8861\u548c\u94dc\u5f62\u6001");

    model
         .description("\u94dc\u79bb\u5b50\u5728\u6c34\u6eb6\u6db2\u4e2d\u4e0e\u6c28\u53d1\u751f\u5f3a\u70c8\u7684\u4eb2\u548c\u4f5c\u7528\uff0c\u5f62\u6210\u6df1\u84dd\u8272\u7684\u9ad8\u5ea6\u7740\u8272\u7edc\u5408\u7269\u3002\u5177\u6709\u4e0d\u540c\u914d\u4f4d\u6570\u7684\u4e0d\u540c\u80fa\u914d\u4f53\u7edc\u5408\u7269\u7684\u76f8\u5bf9\u91cf\u7531\u5f62\u6210\u914d\u4f4d\u5316\u5408\u7269\u7684\u5e73\u8861\u7684\u7a33\u5b9a\u5e38\u6570\u51b3\u5b9a\u3002\u901a\u8fc7\u8f93\u5165\u4e00\u7cfb\u5217\u5e73\u8861\u53cd\u5e94\u5e76\u5f15\u5165\u5916\u90e8\u6c28\u6e90\uff0c\u53ef\u4ee5\u4f7f\u7528\u201c\u77ac\u6001\u201d\u6c42\u89e3\u5668\u6765\u751f\u6210\u5f62\u6001\u56fe\uff0c\u4ee5\u663e\u793a\u4e0d\u540c\u7edc\u5408\u7269\u7684\u6d53\u5ea6\u5982\u4f55\u968f\u6240\u6dfb\u52a0\u6c28\u7684\u603b\u6d53\u5ea6\u800c\u53d8\u5316\u3002");

    model.label("ion_speciation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
