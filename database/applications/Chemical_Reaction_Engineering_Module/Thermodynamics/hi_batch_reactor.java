/*
 * hi_batch_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:29 by COMSOL 6.3.0.290. */
public class hi_batch_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Thermodynamics");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Tinit", "700[K]", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("V_reactor", "1[m^3]", "\u95f4\u6b47\u5f0f\u53cd\u5e94\u5668\u4f53\u79ef");
    model.param().set("cinit_H2", "5.8[mol/m^3]", "\u6c22\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cinit_HI", "5.8[mol/m^3]", "\u7898\u5316\u6c22\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cinit_I2", "5.8[mol/m^3]", "\u7898\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("Af_reaction", "8.87e7", "\u6b63\u53cd\u5e94\u9891\u7387\u56e0\u5b50");
    model.param().set("Ef_reaction", "167e3[J/mol]", "\u6b63\u53cd\u5e94\u6d3b\u5316\u80fd");
    model.param().set("Ar_reaction", "3e7", "\u9006\u53cd\u5e94\u9891\u7387\u56e0\u5b50");
    model.param().set("Er_reaction", "184e3[J/mol]", "\u9006\u53cd\u5e94\u6d3b\u5316\u80fd");

    model.component("comp1").physics("re").prop("reactor").set("Vr", "V_reactor");
    model.component("comp1").physics("re").prop("energybalance").set("T", "Tinit");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "H2+I2<=>2HI");
    model.component("comp1").physics("re").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch1").set("Af", "Af_reaction");
    model.component("comp1").physics("re").feature("rch1").set("Ef", "Ef_reaction");
    model.component("comp1").physics("re").feature("rch1").set("Ar", "Ar_reaction");
    model.component("comp1").physics("re").feature("rch1").set("Er", "Er_reaction");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cinit_H2", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cinit_HI", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cinit_I2", 2, 0);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("K_equi", "comp1.re.c_HI^2/(comp1.re.c_I2*comp1.re.c_H2)", "\u5e73\u8861\u5e38\u6570");
    model.component("comp1").variable("var1").set("T_change", "comp1.re.T-700[K]", "\u6e29\u5ea6\u53d8\u5316");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"hydrogen iodide", "10034-85-2", "HI", "COMSOL"}, 
         {"hydrogen", "1333-74-0", "H2", "COMSOL"}, 
         {"iodine", "7553-56-2", "I2", "COMSOL"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("\u6c14\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.component("comp1").physics("re").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "hydrogen", 0, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching")
         .setIndex("PackageSpecies", "hydrogen iodide", 1, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "iodine", 2, 0);
    model.component("comp1").physics("re").prop("energybalance").set("energybalance", "exclude");

    model.study("std1").feature("time").set("tlist", "0.5e5");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_H2", "re.c_I2", "re.c_HI"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();

    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u7b49\u6e29");

    model.component("comp1").physics("re").prop("energybalance").set("energybalance", "include");
    model.component("comp1").physics("re").feature("inits1").set("T0", "Tinit");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.sol("sol1").copySolution("sol3");
    model.sol("sol3").label("\u975e\u7b49\u6e29");

    model.result("pg1").run();
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("data", "dset2");
    model.result("pg1").feature("glob1").set("titletype", "none");
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u7b49\u6e29 c<sub>H<sub>2</sub></sub>", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "\u7b49\u6e29 c<sub>I<sub>2</sub></sub>", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "\u7b49\u6e29 c<sub>HI</sub>", 2);
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").set("data", "dset3");
    model.result("pg1").feature("glob2").setIndex("legends", "\u975e\u7b49\u6e29 c<sub>H<sub>2</sub></sub>", 0);
    model.result("pg1").feature("glob2").setIndex("legends", "\u975e\u7b49\u6e29 c<sub>I<sub>2</sub></sub>", 1);
    model.result("pg1").feature("glob2").setIndex("legends", "\u975e\u7b49\u6e29 c<sub>HI</sub>", 2);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5e73\u8861\u5e38\u6570");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5e73\u8861\u5e38\u6570 (-)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"K_equi"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u5e73\u8861\u5e38\u6570"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6\u53d8\u5316");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6e29\u5ea6\u53d8\u5316 (K)");
    model.result("pg3").set("xlog", true);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"T_change"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u6e29\u5ea6\u53d8\u5316"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"K"});
    model.result("pg3").feature("glob1").set("titletype", "none");
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u53cd\u5e94\u70ed");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").set("descr", new String[]{});
    model.result("pg4").feature("glob1").set("expr", new String[]{"re.Qheat"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u53cd\u5e94\u70ed\u6e90"});
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u7b49\u6e29\u53cd\u5e94\u5668", 0);
    model.result("pg4").feature("glob1").set("data", "dset2");
    model.result("pg4").feature("glob1").set("titletype", "none");
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("legends", "\u975e\u7b49\u6e29\u53cd\u5e94\u5668", 0);
    model.result("pg4").feature("glob2").set("data", "dset3");
    model.result("pg4").run();
    model.result("pg4").set("xlog", true);
    model.result("pg4").run();

    model.title("HI \u95f4\u6b47\u5f0f\u53cd\u5e94\u5668");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u5b8c\u5168\u6df7\u5408\u7684\u95f4\u6b47\u5f0f\u53cd\u5e94\u5668\u6a21\u578b\uff0c\u6a21\u62df\u4e86\u6c22\u6c14\u548c\u6c14\u6001\u7898\u6df7\u5408\u540e\u53d1\u751f\u53cd\u5e94\u751f\u6210 HI\u3002\u7814\u7a76\u4e86\u5176\u4e2d\u7684\u53cd\u5e94\uff0c\u6bd4\u8f83\u4e86\u7b49\u6e29\u548c\u975e\u7b49\u6e29\u6761\u4ef6\u7684\u7ed3\u679c\u3002");

    model.label("hi_batch_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
