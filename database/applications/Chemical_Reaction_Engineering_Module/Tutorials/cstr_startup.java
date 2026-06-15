/*
 * cstr_startup.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:29 by COMSOL 6.3.0.290. */
public class cstr_startup {

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
    model.param().set("Vr_tank", "1.89[m^3]", "\u53cd\u5e94\u91dc\u4f53\u79ef");
    model.param().set("v_feed", "3.47e-3[m^3/s]", "\u4f53\u79ef\u8fdb\u7ed9\u7387");
    model.param().set("Ea_reaction", "75358[J/mol]", "\u6b63\u53cd\u5e94\u6d3b\u5316\u80fd");
    model.param().set("Af_reaction", "4.71e9[1/s]", "\u6b63\u53cd\u5e94\u9891\u7387\u56e0\u5b50");
    model.param().set("rho_C3H6O", "830[kg/m^3]", "\u5bc6\u5ea6\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("rho_H2O", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6c34");
    model.param().set("rho_C3H8O2", "1036[kg/m^3]", "\u5bc6\u5ea6\uff0c\u4e19\u4e8c\u9187");
    model.param().set("rho_CH3OH", "792[kg/m^3]", "\u5bc6\u5ea6\uff0c\u7532\u9187");
    model.param().set("cp_C3H6O", "146.5[J/(mol*K)]", "\u70ed\u5bb9\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("cp_H2O", "75.4[J/(mol*K)]", "\u70ed\u5bb9\uff0c\u6c34");
    model.param().set("cp_C3H8O2", "192.6[J/(mol*K)]", "\u70ed\u5bb9\uff0c\u4e19\u4e8c\u9187");
    model.param().set("cp_CH3OH", "81.6[J/(mol*K)]", "\u70ed\u5bb9\uff0c\u7532\u9187");
    model.param().set("cp_exch", "75.4[J/mol/K]", "\u70ed\u5bb9\uff0c\u6362\u70ed\u4ecb\u8d28");
    model.param().set("href_C3H6O", "-153.5[kJ/mol]", "\u751f\u6210\u7113\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("href_H2O", "-286.1[kJ/mol]", "\u751f\u6210\u7113\uff0c\u6c34");
    model.param().set("href_C3H8O2", "-525.6[kJ/mol]", "\u751f\u6210\u7113\uff0c\u4e19\u4e8c\u9187");
    model.param().set("href_CH3OH", "-238.6[kJ/mol]", "\u751f\u6210\u7113\uff0c\u7532\u9187");
    model.param().set("Tref", "293[K]", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("Tfeed", "297[K]", "\u8fdb\u6599\u6d41\u6e29\u5ea6");
    model.param().set("Tinit", "340.0[K]", "\u521d\u59cb\u53cd\u5e94\u5668\u6e29\u5ea6");
    model.param().set("Texch", "289[K]", "\u6362\u70ed\u4ecb\u8d28\u6e29\u5ea6");
    model.param().set("Fexch", "126[mol/s]", "\u6362\u70ed\u5668\u6469\u5c14\u6d41\u7387");
    model.param().set("UA", "8441[J/(s*K)]", "\u70ed\u4ea4\u6362\u53c2\u6570");
    model.param().set("cinit_C3H6O", "1400.0[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("cinit_H2O", "55273[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u6c34");
    model.param().set("cfeed_C3H6O", "2903[mol/m^3]", "\u8fdb\u6599\u6d53\u5ea6\uff0c\u73af\u6c27\u4e19\u70f7");
    model.param().set("cfeed_H2O", "36291[mol/m^3]", "\u8fdb\u6599\u6d53\u5ea6\uff0c\u6c34");
    model.param().set("cfeed_CH3OH", "3629[mol/m^3]", "\u8fdb\u6599\u6d53\u5ea6\uff0c\u7532\u9187");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("h_C3H6O", "cp_C3H6O*(re.T-Tref)+href_C3H6O", "\u7113\uff0c\u73af\u6c27\u4e19\u70f7");
    model.component("comp1").variable("var1").set("h_H2O", "cp_H2O*(re.T-Tref)+href_H2O", "\u7113\uff0c\u6c34");
    model.component("comp1").variable("var1")
         .set("h_C3H8O2", "cp_C3H8O2*(re.T-Tref)+href_C3H8O2", "\u7113\uff0c\u4e19\u4e8c\u9187");
    model.component("comp1").variable("var1")
         .set("h_CH3OH", "cp_CH3OH*(re.T-Tref)+href_CH3OH", "\u7113\uff0c\u7532\u9187");
    model.component("comp1").variable("var1")
         .set("hf_C3H6O", "cp_C3H6O*(Tfeed-Tref)+href_C3H6O", "\u8fdb\u6599\u7113\uff0c\u73af\u6c27\u4e19\u70f7");
    model.component("comp1").variable("var1")
         .set("hf_H2O", "cp_H2O*(Tfeed-Tref)+href_H2O", "\u8fdb\u6599\u7113\uff0c\u6c34");
    model.component("comp1").variable("var1")
         .set("hf_CH3OH", "cp_CH3OH*(Tfeed-Tref)+href_CH3OH", "\u8fdb\u6599\u7113\uff0c\u7532\u9187");
    model.component("comp1").variable("var1")
         .set("Q_xch", "Fexch*cp_exch*(Texch-re.T)*(1-exp(-UA/(Fexch*cp_exch)))", "\u70ed\u4ea4\u6362\u91cf");

    model.component("comp1").physics("re").prop("reactor").set("reactor", "cstrvol");
    model.component("comp1").physics("re").prop("energybalance").set("energybalance", "include");
    model.component("comp1").physics("re").prop("energybalance").set("Qext", "Q_xch");
    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").prop("reactor").set("Vr", "Vr_tank");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "C3H6O+H2O=>C3H8O2");
    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1").set("r", "re.kf_1*re.c_C3H6O");
    model.component("comp1").physics("re").feature("rch1").set("bulkFwdOrder", 1);
    model.component("comp1").physics("re").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch1").set("Af", "Af_reaction");
    model.component("comp1").physics("re").feature("rch1").set("Ef", "Ea_reaction");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "CH3OH");
    model.component("comp1").physics("re").feature("C3H6O").set("rho", "rho_C3H6O");
    model.component("comp1").physics("re").feature("H2O").set("rho", "rho_H2O");
    model.component("comp1").physics("re").feature("C3H8O2").set("rho", "rho_C3H8O2");
    model.component("comp1").physics("re").feature("CH3OH").set("rho", "rho_CH3OH");
    model.component("comp1").physics("re").create("feed1", "FeedStream", -1);
    model.component("comp1").physics("re").feature("feed1").set("vf", "v_feed");
    model.component("comp1").physics("re").feature("feed1").set("Tf", "Tfeed");
    model.component("comp1").physics("re").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "cfeed_C3H6O", 0, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("FeedSpeciesEnthalpy", "hf_C3H6O", 0, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("enthalpyType", 0, 0, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("enthalpyType", 0, 1, 0);
    model.component("comp1").physics("re").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "cfeed_CH3OH", 2, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("FeedSpeciesEnthalpy", "hf_CH3OH", 2, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("enthalpyType", 0, 2, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("FeedSpeciesConcentration", "cfeed_H2O", 3, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("FeedSpeciesEnthalpy", "hf_H2O", 3, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("enthalpyType", 0, 3, 0);
    model.component("comp1").physics("re").feature("inits1").set("T0", "Tinit");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cinit_C3H6O", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cinit_H2O", 3, 0);
    model.component("comp1").physics("re").feature("C3H6O").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("re").feature("C3H6O").set("Cp", "cp_C3H6O");
    model.component("comp1").physics("re").feature("C3H6O").set("h", "h_C3H6O");
    model.component("comp1").physics("re").feature("H2O").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("re").feature("H2O").set("Cp", "cp_H2O");
    model.component("comp1").physics("re").feature("H2O").set("h", "h_H2O");
    model.component("comp1").physics("re").feature("C3H8O2").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("re").feature("C3H8O2").set("Cp", "cp_C3H8O2");
    model.component("comp1").physics("re").feature("C3H8O2").set("h", "h_C3H8O2");
    model.component("comp1").physics("re").feature("CH3OH").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("re").feature("CH3OH").set("Cp", "cp_CH3OH");
    model.component("comp1").physics("re").feature("CH3OH").set("h", "h_CH3OH");

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", 4);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_C3H6O", "re.c_H2O", "re.c_C3H8O2", "re.c_CH3OH"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.T"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result("pg2").label("\u6e29\u5ea6 (re)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u73af\u6c27\u4e19\u70f7\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_C3H6O"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("legend", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legend", false);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Vr_tank", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^3", 0);
    model.study("std1").feature("param").setIndex("pname", "Vr_tank", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^3", 0);
    model.study("std1").feature("param").setIndex("pname", "Tinit", 0);
    model.study("std1").feature("param").setIndex("plistarr", "297[K] 340[K] 340[K]", 0);
    model.study("std1").feature("param").setIndex("pname", "Vr_tank", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m^3", 1);
    model.study("std1").feature("param").setIndex("pname", "Vr_tank", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m^3", 1);
    model.study("std1").feature("param").setIndex("pname", "cinit_C3H6O", 1);
    model.study("std1").feature("param").setIndex("plistarr", "0 0 1400[mol/m^3]", 1);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"re.c_C3H6O", "re.c_H2O", "re.c_C3H8O2", "re.c_CH3OH"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg3").label("\u6d53\u5ea6 (re) 1");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("unit", new String[]{""});
    model.result("pg4").feature("glob1").set("expr", new String[]{"re.T"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result("pg4").label("\u6e29\u5ea6 (re) 1");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").run();
    model.result("pg3").label("\u6d53\u5ea6 vs. \u6e29\u5ea6 (re)");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("expr", new String[]{"re.c_C3H6O"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg3").feature("glob1").set("xdataexpr", "re.T");
    model.result("pg3").feature("glob1").set("xdatadescr", "\u6e29\u5ea6");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("autoexpr", false);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u6e29\u5ea6 vs. \u65f6\u95f4 (re)");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("autoexpr", false);
    model.result("pg4").run();

    model.title("\u5168\u6df7\u6d41\u53cd\u5e94\u5668\u7684\u542f\u52a8");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u751f\u4ea7\u4e19\u4e8c\u9187\u7684\u5168\u6df7\u6d41\u53cd\u5e94\u5668 (CSTR) \u7684\u542f\u52a8\u9636\u6bb5\u3002\u7ed3\u679c\u663e\u793a\u4e86\u5f71\u54cd\u53cd\u5e94\u5668\u5b89\u5168\u6027\u7684\u521d\u59cb\u6761\u4ef6\u3002");

    model.label("cstr_startup.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
