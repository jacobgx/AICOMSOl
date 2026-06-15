/*
 * nonisothermal_plug_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:31 by COMSOL 6.3.0.290. */
public class nonisothermal_plug_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("spf", "StationaryPlugflow");
    model.study("std1").feature("spf").set("solnum", "auto");
    model.study("std1").feature("spf").set("notsolnum", "auto");
    model.study("std1").feature("spf").set("outputmap", new String[]{});
    model.study("std1").feature("spf").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("v_inlet", "2[m^3/s]", "\u5165\u53e3\u4f53\u79ef\u6d41\u91cf");
    model.param().set("A_frac", "1[mol/mol]", "A \u7684\u5165\u53e3\u6469\u5c14\u5206\u6570");
    model.param().set("cinlet_A", "18.8[mol/m^3]", "A \u7684\u5165\u53e3\u6d53\u5ea6");
    model.param().set("Finlet_tot", "cinlet_A*v_inlet/A_frac", "\u5165\u53e3\u603b\u6d41\u91cf");
    model.param().set("Finlet_K", "0[mol/s]", "K \u7684\u5165\u53e3\u6469\u5c14\u6d41");
    model.param().set("Finlet_M", "0[mol/s]", "M \u7684\u5165\u53e3\u6469\u5c14\u6d41");
    model.param().set("Finlet_A", "cinlet_A*v_inlet", "A \u7684\u5165\u53e3\u6469\u5c14\u6d41");
    model.param().set("Finlet_N2", "Finlet_tot-Finlet_A", "N2 \u7684\u5165\u53e3\u6469\u5c14\u6d41");
    model.param().set("P_reactor", "(cinlet_A/A_frac)*R_const*T_inlet", "\u53cd\u5e94\u5668\u538b\u529b");
    model.param().set("Af_reaction", "8.2e14", "\u6b63\u53cd\u5e94\u9891\u7387\u56e0\u5b50");
    model.param().set("Ef_reaction", "284.5e3[J/mol]", "\u6b63\u53cd\u5e94\u6d3b\u5316\u80fd");
    model.param().set("Ua", "16500[J/(m^3*s*K)]", "\u4f20\u70ed\u53c2\u6570");
    model.param().set("T_inlet", "1035[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("T_x", "1150[K]", "\u6e29\u5ea6\uff0c\u6362\u70ed\u4ecb\u8d28");

    model.component("comp1").physics("re").prop("reactor").set("reactor", "plugflow");
    model.component("comp1").physics("re").prop("energybalance").set("energybalance", "include");
    model.component("comp1").physics("re").prop("mixture").set("p", "P_reactor");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "A=>K+M");
    model.component("comp1").physics("re").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch1").set("Af", "Af_reaction");
    model.component("comp1").physics("re").feature("rch1").set("Ef", "Ef_reaction");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "N2");
    model.component("comp1").physics("re").feature("inits1").set("T0plug", "T_inlet");
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "Finlet_A", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "Finlet_N2", 3, 0);
    model.component("comp1").physics("re").prop("chemkin").set("thermo", "nonisothermal_plug_flow_thermo.txt");
    model.component("comp1").physics("re").feature("A").set("M", "0.058080040000000006");
    model.component("comp1").physics("re").feature("A").set("speciesEnthalpy", "NASA");
    model.component("comp1").physics("re").feature("A").set("Tlo", "300.0");
    model.component("comp1").physics("re").feature("A").set("Tmid", "1000.0");
    model.component("comp1").physics("re").feature("A").set("Thi", "4000.0");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaLo1", "1.5848079");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaLo2", "0.027413605");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaLo3", "-9.1426603E-6");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaLo4", "-6.6768869E-10");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaLo5", "3.4781512E-13");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaLo6", "-26678.575");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaLo7", "18.843809");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaHi1", "4.261922");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaHi2", "0.023919538");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaHi3", "-1.0714477E-5");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaHi4", "2.249689E-9");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaHi5", "-1.8079303E-13");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaHi6", "-27708.34");
    model.component("comp1").physics("re").feature("A").set("SpeciesThermoaHi7", "3.7906279");
    model.component("comp1").physics("re").feature("K").set("M", "0.042037279999999996");
    model.component("comp1").physics("re").feature("K").set("speciesEnthalpy", "NASA");
    model.component("comp1").physics("re").feature("K").set("Tlo", "300.0");
    model.component("comp1").physics("re").feature("K").set("Tmid", "1000.0");
    model.component("comp1").physics("re").feature("K").set("Thi", "4000.0");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaLo1", "1.5410946");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaLo2", "0.021513643");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaLo3", "-2.575013E-5");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaLo4", "1.8486377E-8");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaLo5", "-5.5939702E-12");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaLo6", "-6955.7078");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaLo7", "14.808619");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaHi1", "4.7307523");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaHi2", "0.0084849084");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaHi3", "-3.7289848E-6");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaHi4", "7.716621E-10");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaHi5", "-6.1322345E-14");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaHi6", "-7649.2303");
    model.component("comp1").physics("re").feature("K").set("SpeciesThermoaHi7", "-0.68485049");
    model.component("comp1").physics("re").feature("M").set("M", "0.016042760000000003");
    model.component("comp1").physics("re").feature("M").set("speciesEnthalpy", "NASA");
    model.component("comp1").physics("re").feature("M").set("Tlo", "300.0");
    model.component("comp1").physics("re").feature("M").set("Tmid", "1000.0");
    model.component("comp1").physics("re").feature("M").set("Thi", "4000.0");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaLo1", "3.8717898");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaLo2", "-0.0042480466");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaLo3", "2.4540181E-5");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaLo4", "-2.1780766E-8");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaLo5", "6.3010622E-12");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaLo6", "-10144.425");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaLo7", "0.66008135");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaHi1", "0.47238333");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaHi2", "0.012680758");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaHi3", "-5.5093741E-6");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaHi4", "1.1295575E-9");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaHi5", "-8.9103779E-14");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaHi6", "-9642.45");
    model.component("comp1").physics("re").feature("M").set("SpeciesThermoaHi7", "16.19909");
    model.component("comp1").physics("re").feature("N2").set("M", "0.0280134");
    model.component("comp1").physics("re").feature("N2").set("speciesEnthalpy", "NASA");
    model.component("comp1").physics("re").feature("N2").set("Tlo", "300.0");
    model.component("comp1").physics("re").feature("N2").set("Tmid", "1000.0");
    model.component("comp1").physics("re").feature("N2").set("Thi", "4000.0");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaLo1", "3.6962069");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaLo2", "-0.0012983164");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaLo3", "2.4640713E-6");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaLo4", "-9.3801238E-10");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaLo5", "-3.703642E-14");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaLo6", "-1063.103");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaLo7", "2.2199845");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaHi1", "2.7292633");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaHi2", "0.0017776002");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaHi3", "-7.6185598E-7");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaHi4", "1.5386678E-10");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaHi5", "-1.1961307E-14");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaHi6", "-836.7934");
    model.component("comp1").physics("re").feature("N2").set("SpeciesThermoaHi7", "7.0662127");
    model.component("comp1").physics("re").prop("chemkin").set("chemkinNo", true);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("X_A", "(re.F0_A-re.F_A)/re.F0_A*100");
    model.component("comp1").variable("var1").descr("X_A", "\u8f6c\u5316\u7387\uff0cA");

    model.study("std1").feature("spf").set("tlist", "range(0,0.05,5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.F_A", "re.F_K", "re.F_M", "re.F_N2"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387"});
    model.result("pg1").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg1").label("\u6469\u5c14\u6d41\u7387 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.T"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result("pg2").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg2").label("\u6e29\u5ea6 (re)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();

    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u7edd\u70ed");

    model.component("comp1").physics("re").prop("energybalance").set("Qextplug", "Ua*(T_x-re.T)");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.sol("sol1").copySolution("sol3");
    model.sol("sol3").label("\u5305\u542b\u70ed\u4ea4\u6362");

    model.component("comp1").physics("re").prop("energybalance").set("Qextplug", 0);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "v_inlet", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^3/s", 0);
    model.study("std1").feature("param").setIndex("pname", "v_inlet", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^3/s", 0);
    model.study("std1").feature("param").setIndex("pname", "A_frac", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 0.5 0.1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"re.F_A", "re.F_K", "re.F_M", "re.F_N2"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387"});
    model.result("pg3").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg3").label("\u6469\u5c14\u6d41\u7387 (re) 1");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("unit", new String[]{""});
    model.result("pg4").feature("glob1").set("expr", new String[]{"re.T"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result("pg4").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg4").label("\u6e29\u5ea6 (re) 1");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").run();

    model.sol("sol4").copySolution("sol8");
    model.sol("sol8").label("\u5305\u542b\u60f0\u6027\u6c14\u4f53");

    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("data", "dset2");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u7edd\u70ed", 0);
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").set("data", "dset3");
    model.result("pg2").feature("glob2").setIndex("legends", "\u5305\u542b\u70ed\u4ea4\u6362", 0);
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg3").run();
    model.result("pg3").label("\u53cd\u5e94\u901f\u7387 (re)");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("data", "dset2");
    model.result("pg3").feature("glob1").set("expr", new String[]{"re.r_1"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u53cd\u5e94\u901f\u7387"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"mol/(m^3*s)"});
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u7edd\u70ed", 0);
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("data", "dset3");
    model.result("pg3").feature("glob2").set("expr", new String[]{"re.r_1"});
    model.result("pg3").feature("glob2").set("descr", new String[]{"\u53cd\u5e94\u901f\u7387"});
    model.result("pg3").feature("glob2").set("unit", new String[]{"mol/(m^3*s)"});
    model.result("pg3").feature("glob2").set("linewidth", 2);
    model.result("pg3").feature("glob2").set("legendmethod", "manual");
    model.result("pg3").feature("glob2").setIndex("legends", "\u5305\u542b\u70ed\u4ea4\u6362", 0);
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").label("A \u7684\u8f6c\u5316\u7387 (re)");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "A \u7684\u8f6c\u5316\u7387 (%)");
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("data", "dset2");
    model.result("pg1").feature("glob1").set("expr", new String[]{"X_A"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u8f6c\u5316\u7387\uff0cA"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u7edd\u70ed", 0);
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").set("data", "dset3");
    model.result("pg1").feature("glob2").setIndex("legends", "\u5305\u542b\u70ed\u4ea4\u6362", 0);
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").label("\u5305\u542b\u60f0\u6027\u6c14\u4f53\u65f6 A \u7684\u8f6c\u5316\u7387 (re)");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u53cd\u5e94\u5668\u5bb9\u79ef (m<sup>3</sup>)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "A \u7684\u8f6c\u5316\u7387 (%)");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("data", "dset5");
    model.result("pg5").feature("glob1").set("expr", new String[]{"X_A"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u8f6c\u5316\u7387\uff0cA"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg5").feature("glob1").set("legendpattern", "\u5165\u53e3\u5904 eval(A_frac*100) mol% A");
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg5").run();

    model.title("\u975e\u7b49\u6e29\u5e73\u63a8\u6d41\u53cd\u5e94\u5668");

    model
         .description("\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u4e2d\u9884\u5b9a\u4e49\u7684\u5e73\u63a8\u6d41\u53cd\u5e94\u5668\u7c7b\u578b\u7528\u4e8e\u5bf9\u4e19\u916e\u7684\u975e\u7b49\u6e29\u88c2\u89e3\u8fdb\u884c\u5efa\u6a21\u3002\u672c\u4f8b\u7814\u7a76\u5728\u7edd\u70ed\u53cd\u5e94\u3001\u6362\u70ed\u5668\u5411\u53cd\u5e94\u5668\u63d0\u4f9b\u80fd\u91cf\u4ee5\u53ca\u5b58\u5728\u60f0\u6027\u6c14\u4f53\u7b49\u60c5\u51b5\u4e0b\uff0c\u5de5\u827a\u8fc7\u7a0b\u4e2d\u7684\u6ce8\u610f\u4e8b\u9879\u3002");

    model.label("nonisothermal_plug_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
