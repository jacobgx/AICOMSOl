/*
 * nonideal_cstr.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:30 by COMSOL 6.3.0.290. */
public class nonideal_cstr {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("tau", "2400[s]", "\u7a7a\u95f4\u65f6\u95f4\uff0c\u5b9e\u9645\u53cd\u5e94\u5668");
    model.param().set("c_T0", "1000[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0c\u5b9e\u9645\u53cd\u5e94\u5668");
    model.param().set("c_w", "55500[mol/m^3]", "\u6d53\u5ea6\uff0c\u6eb6\u5242");
    model.param().set("Mn_T", "0.0132[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u793a\u8e2a\u5242");
    model.param().set("rho_w", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6c34");
    model.param().set("Vtot", "1[m^3]", "\u5b9e\u9645\u53cd\u5e94\u5668\u603b\u4f53\u79ef");
    model.param().set("v1", "1[m^3/s]", "CSTR \u4e4b\u95f4\u7684\u6700\u5927\u4f53\u79ef\u6d41\u7387");
    model.param()
         .set("v0", "Vtot/tau", "\u8fdb\u5165\u5b9e\u9645\u53cd\u5e94\u5668\u7684\u5165\u53e3\u8fdb\u6599\u4f53\u79ef\u901f\u7387");
    model.param()
         .set("alpha", "0.5", "\u5145\u5206\u6405\u62cc\u65f6\u7684\u4f53\u79ef\u5206\u6570\uff1a\u5f85\u8ba1\u7b97");
    model.param().set("beta", "0.1", "\u6d41\u4f53\u4ea4\u6362\u7387\uff1a\u5f85\u8ba1\u7b97");

    model.component("comp1").physics("re")
         .label("\u53cd\u5e94\u5de5\u7a0b - \u5168\u6df7\u6d41\u53cd\u5e94\u5668 1");
    model.component("comp1").physics("re").tag("re1");
    model.component("comp1").physics("re1").prop("reactor").set("reactor", "cstrmass");
    model.component("comp1").physics("re1").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re1").prop("reactor").set("reactorparsource", "UserDefined");
    model.component("comp1").physics("re1").prop("reactor").set("v", "(1+beta)*v0");
    model.component("comp1").physics("re1").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re1").feature("spec1").set("specName", "T");
    model.component("comp1").physics("re1").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re1").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("re1").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("re1").feature("T").set("M", "Mn_T");
    model.component("comp1").physics("re1").feature("H2O").set("rho", "rho_w");
    model.component("comp1").physics("re1").feature("inits1").set("Vr0", "alpha*Vtot");
    model.component("comp1").physics("re1").feature("inits1").setIndex("initialValue", "c_w", 0, 0);
    model.component("comp1").physics("re1").create("feed1", "FeedStream", -1);
    model.component("comp1").physics("re1").feature("feed1").set("vf", "v0");
    model.component("comp1").physics("re1").feature("feed1").setIndex("FeedSpeciesConcentration", "c_w", 0, 0);
    model.component("comp1").physics("re1").feature("feed1").setIndex("FeedSpeciesConcentration", "c_T0", 1, 0);
    model.component("comp1").physics("re1").create("feed2", "FeedStream", -1);
    model.component("comp1").physics("re1").feature("feed2").set("vf", "v0*beta");
    model.component("comp1").physics("re1").feature("feed2").setIndex("FeedSpeciesConcentration", "c_w", 0, 0);
    model.component("comp1").physics("re1").feature("feed2").setIndex("FeedSpeciesConcentration", "re2.c_T", 1, 0);
    model.component("comp1").physics().copy("re2", "re1");
    model.component("comp1").physics("re2").label("\u53cd\u5e94\u5de5\u7a0b - CSTR 2");
    model.component("comp1").physics("re2").prop("reactor").set("v", "v0*beta");
    model.component("comp1").physics("re2").feature("inits1").set("Vr0", "(1-alpha)*Vtot");
    model.component("comp1").physics("re2").feature("inits1").setIndex("initialValue", "c_w", 0, 0);
    model.component("comp1").physics("re2").feature("feed1").set("vf", "v0*beta");
    model.component("comp1").physics("re2").feature("feed1").setIndex("FeedSpeciesConcentration", "re1.c_T", 1, 0);
    model.component("comp1").physics("re2").feature().remove("feed2");

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u731c\u6d4b\u503c");
    model.study("std1").feature("time").set("tlist", 24000);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re1.c_T"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re1)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re2.c_T"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg2").label("\u6d53\u5ea6 (re2)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u975e\u7406\u60f3\u53cd\u5e94\u5668\u5185\u7684\u6d53\u5ea6");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1")
         .set("ylabel", "\u53cd\u5e94\u5668\u51fa\u53e3\u5904\u7684\u793a\u8e2a\u5242 (mol/m<sup>3</sup>)");
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").label("\u4eff\u771f\uff0c\u521d\u59cb\u731c\u6d4b\u503c");
    model.result("pg1").feature("glob1").set("autoplotlabel", true);
    model.result("pg1").feature("glob1").set("autosolution", false);
    model.result("pg1").feature("glob1").set("autoexpr", false);
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u5b9e\u9a8c\u6570\u636e");
    model.result().table("tbl1").importData("nonideal_cstr_data.csv");
    model.result("pg1").run();
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").label("\u5b9e\u9a8c\u6570\u636e");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("autoplotlabel", true);
    model.result("pg1").feature("tblp1").set("autoheaders", false);
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linemarker", "circle");
    model.result("pg2").run();
    model.result("pg2").label("\u5168\u6df7\u6d41\u53cd\u5e94\u5668\u4e2d\u7684\u6d53\u5ea6");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").label("\u7406\u60f3\u5168\u6df7\u6d41\u53cd\u5e94\u5668 2");
    model.result("pg2").feature("glob1").set("autoplotlabel", true);
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").label("\u7406\u60f3\u5168\u6df7\u6d41\u53cd\u5e94\u5668 1");
    model.result("pg2").feature("glob2").setIndex("expr", "re1.c_T", 0);
    model.result("pg2").run();

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").set("source", "resultTable");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col2", "re1.c_T");
    model.component("comp1").common("lso1").setEntry("variableName", "col2", "c_T");
    model.component("comp1").common("lso1").setEntry("unit", "col2", "mol/m^3");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/re1", true);
    model.study("std2").feature("time").setSolveFor("/physics/re2", true);
    model.study("std2").feature("time").set("tlist", 24000);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u53c2\u6570\u4f30\u8ba1");
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").setIndex("pname", "tau", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "2400[s]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "tau", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "2400[s]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "c_T0", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "1000[mol/m^3]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "c_T0", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "1000[mol/m^3]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "alpha", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 0.5, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 0);
    model.study("std2").feature("lsqo").setIndex("ubound", 1, 0);
    model.study("std2").feature("lsqo").setIndex("pname", "beta", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 0.1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 1);
    model.study("std2").feature("lsqo").setIndex("ubound", 1, 1);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").label("\u4eff\u771f\uff0c\u4f18\u5316");
    model.result("pg1").feature("glob2").set("data", "dset2");
    model.result("pg1").feature("glob2").set("expr", new String[]{});
    model.result("pg1").feature("glob2").set("descr", new String[]{});
    model.result("pg1").feature("glob2").setIndex("expr", "re1.c_T", 0);
    model.result("pg1").feature("glob2").set("autoplotlabel", true);
    model.result("pg1").feature("glob2").set("autosolution", false);
    model.result("pg1").feature("glob2").set("autoexpr", false);

    model.study("std2").feature("lsqo").set("plot", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("glob1").create("comp1", "Comparison");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").feature("comp1").set("metric", "rms");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("glob2").create("comp1", "Comparison");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").feature("comp1").set("metric", "rms");
    model.result("pg1").run();

    model.title("\u975e\u7406\u60f3\u53cd\u5e94\u5668\u6a21\u578b\u53c2\u6570\u4f30\u8ba1");

    model
         .description("\u672c\u4f8b\u4e2d\u8bbe\u7f6e\u4e86\u4e00\u4e2a\u201c\u6b7b\u533a\u6a21\u578b\u201d\uff0c\u5373\u975e\u7406\u60f3\u53cd\u5e94\u5668\u6a21\u578b\u3002\u5176\u4e2d\u4f7f\u7528\u4e86\u4e24\u79cd\u7406\u60f3 CSTR \u4ea4\u6362\u53cd\u5e94\uff0c\u8868\u793a\u5b9e\u9645\u53cd\u5e94\u5668\u4e2d\u7684\u9ad8\u6405\u62cc\u533a\u548c\u4f4e\u6405\u62cc\u533a\u3002\u5bf9\u4e8e\u8fd9\u4e24\u4e2a\u533a\u57df\uff0c\u901a\u8fc7\u53c2\u6570\u4f30\u8ba1\u786e\u5b9a\u4e86\u4e24\u4e2a\u6709\u5173\u4f53\u79ef\u548c\u4ea4\u6362\u901f\u7387\u7684\u53c2\u6570\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u4f18\u5316\u6a21\u5757\u201d\u3002");

    model.label("nonideal_cstr.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
