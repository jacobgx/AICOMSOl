/*
 * tank_flow_system.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:19 by COMSOL 6.3.0.290. */
public class tank_flow_system {

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
    model.param().set("Mn_S", "0.018[kg/mol]", "\u6eb6\u5242\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("Mn_A", "0.032[kg/mol]", "A \u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("Mn_B", "0.032[kg/mol]", "B \u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("density_S", "1000[kg/m^3]", "\u6eb6\u5242\u5bc6\u5ea6");
    model.param()
         .set("cinit_S", "density_S/Mn_S", "\u53cd\u5e94\u91dc\u4e2d\u6eb6\u5242\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("kf_reaction", "(1/600)[1/s]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param()
         .set("Vr_init_tank1", "1[m^3]", "\u521d\u59cb\u53cd\u5e94\u5668\u4f53\u79ef\uff0c\u53cd\u5e94\u91dc 1");
    model.param()
         .set("Vr_init_tank2", "1.5[m^3]", "\u521d\u59cb\u53cd\u5e94\u5668\u4f53\u79ef\uff0c\u53cd\u5e94\u91dc 2");
    model.param()
         .set("v_inlet", "1[m^3/min]", "\u8fdb\u5165\u53cd\u5e94\u91dc 1 \u7684\u4f53\u79ef\u8fdb\u7ed9\u7387");
    model.param()
         .set("cinlet_A1", "1000[mol/m^3]", "\u7269\u8d28 A \u7684\u5165\u53e3\u6d53\u5ea6\uff0c\u53cd\u5e94\u91dc 1");
    model.param()
         .set("v_fresh2", "0.5[m^3/min]", "\u8fdb\u5165\u53cd\u5e94\u91dc 2 \u7684\u65b0\u4f53\u79ef\u8fdb\u7ed9\u7387");
    model.param()
         .set("cfresh2_A", "1000[mol/m^3]", "\u8fdb\u5165\u53cd\u5e94\u91dc 2 \u7684\u7269\u8d28 A \u7684\u65b0\u5165\u53e3\u8fdb\u6599\u6d53\u5ea6");
    model.param().set("cinlet_S", "cinit_S", "\u6eb6\u5242\u7684\u5165\u53e3\u6d53\u5ea6");
    model.param()
         .set("v_outlet1", "0.9[m^3/min]", "\u4ece\u53cd\u5e94\u91dc 1 \u6d41\u51fa\u7684\u4f53\u79ef\u51fa\u53e3\u901f\u7387");
    model.param()
         .set("v_outlet2", "1[m^3/min]", "\u4ece\u53cd\u5e94\u91dc 2 \u6d41\u51fa\u7684\u4f53\u79ef\u51fa\u53e3\u901f\u7387");

    model.component("comp1").physics("re").prop("reactor").set("reactor", "cstrmass");
    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "A=>B");
    model.component("comp1").physics("re").feature("rch1").set("kf", "kf_reaction");
    model.component("comp1").physics("re").feature("A").set("M", "Mn_A");
    model.component("comp1").physics("re").feature("B").set("M", "Mn_B");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "S");
    model.component("comp1").physics("re").feature("S").set("sType", "solvent");
    model.component("comp1").physics("re").feature("S").set("M", "Mn_S");
    model.component("comp1").physics("re").feature("S").set("rho", "density_S");
    model.component("comp1").physics("re").prop("reactor").set("reactorparsource", "UserDefined");
    model.component("comp1").physics("re").prop("SimpropMass").set("Species", new String[]{"A", "B", "S"});
    model.component("comp1").physics("re").prop("SimpropMass")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u6eb6\u5242"});
    model.component("comp1").physics("re").prop("SimpropMass")
         .set("Notes", new String[]{"re.R_A", "re.R_B", "solvent"});
    model.component("comp1").physics("re").prop("reactor")
         .set("vp", "re.Vr*(re.R_A*re.M_A+re.R_B*re.M_B+re.R_S*re.M_S)/max(re.rho_S,eps)");
    model.component("comp1").physics("re").prop("reactor").set("v", "v_outlet1");
    model.component("comp1").physics("re").feature("inits1").set("Vr0", "Vr_init_tank1");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cinit_S", 2, 0);
    model.component("comp1").physics("re").create("feed1", "FeedStream", -1);
    model.component("comp1").physics("re").feature("feed1").set("vf", "v_inlet");
    model.component("comp1").physics("re").feature("feed1").setIndex("FeedSpeciesConcentration", "cinlet_A1", 0, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("FeedSpeciesConcentration", "cinlet_S", 2, 0);
    model.component("comp1").physics().copy("re2", "re");
    model.component("comp1").physics("re2").prop("reactor").set("v", "v_outlet2");
    model.component("comp1").physics("re2").feature("inits1").set("Vr0", "Vr_init_tank2");
    model.component("comp1").physics("re2").feature("feed1")
         .label("\u8fdb\u6599\u5165\u53e3 1 - \u6765\u81ea\u91dc 1");
    model.component("comp1").physics("re2").feature("feed1").set("vf", "v_outlet1");
    model.component("comp1").physics("re2").feature("feed1").setIndex("FeedSpeciesConcentration", "re.c_A", 0, 0);
    model.component("comp1").physics("re2").feature("feed1").setIndex("FeedSpeciesConcentration", "re.c_B", 1, 0);
    model.component("comp1").physics("re2").feature("feed1").setIndex("FeedSpeciesConcentration", "re.c_S", 2, 0);
    model.component("comp1").physics("re2").create("feed2", "FeedStream", -1);
    model.component("comp1").physics("re2").feature("feed2").label("\u8fdb\u6599\u5165\u53e3 2 - \u65b0\u6599");
    model.component("comp1").physics("re2").feature("feed2").set("vf", "v_fresh2");
    model.component("comp1").physics("re2").feature("feed2").setIndex("FeedSpeciesConcentration", "cfresh2_A", 0, 0);
    model.component("comp1").physics("re2").feature("feed2").setIndex("FeedSpeciesConcentration", "cinlet_S", 2, 0);

    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,120)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.re.Vr<=Vr_init_tank1*0.01", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 2", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 2", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.re2.Vr<=Vr_init_tank2*0.01", 1);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_A", "re.c_B"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re2.c_A", "re2.c_B"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").label("\u6d53\u5ea6 (re2)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u91dc\u5185\u6d53\u5ea6");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").label("\u91dc 1");
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u91dc 1 \u4e2d\u7684 A", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "\u91dc 1 \u4e2d\u7684 B", 1);
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").label("\u91dc 2");
    model.result("pg1").feature("glob2").set("expr", new String[]{"re2.c_A"});
    model.result("pg1").feature("glob2").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob2").set("unit", new String[]{"mol/m^3"});
    model.result("pg1").feature("glob2").set("expr", new String[]{"re2.c_A", "re2.c_B"});
    model.result("pg1").feature("glob2").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob2").setIndex("legends", "\u91dc 2 \u4e2d\u7684 A", 0);
    model.result("pg1").feature("glob2").setIndex("legends", "\u91dc 2 \u4e2d\u7684 B", 1);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u91dc\u5bb9\u79ef");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").label("\u91dc 1");
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.Vr"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u53cd\u5e94\u5668\u5bb9\u79ef"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"m^3"});
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("autoplotlabel", true);
    model.result("pg2").feature("glob1").set("autosolution", false);
    model.result("pg2").feature("glob1").set("autoexpr", false);
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").label("\u91dc 2");
    model.result("pg2").run();

    model.title("\u7406\u60f3\u6405\u62cc\u53cd\u5e94\u91dc\u7cfb\u7edf");

    model
         .description("\u5728\u5316\u5de5\u4e1a\u548c\u751f\u5316\u4e1a\u5e38\u8981\u6c42\u53cd\u5e94\u91dc\u5145\u5206\u6405\u62cc\u4e14\u6db2\u9762\u53ef\u63a7\u3002\u672c\u4f8b\u63cf\u8ff0\u5982\u4f55\u4f7f\u7528\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u5bf9\u7406\u60f3\u7684\u7ea7\u8054\u53cd\u5e94\u91dc\u7cfb\u7edf\u5efa\u6a21\uff0c\u5176\u8fdb\u6599\u53e3\u548c\u51fa\u53e3\u6599\u6d41\u53ef\u63a7\u3002\u8fd8\u76d1\u63a7\u4e86\u6bcf\u4e2a\u53cd\u5e94\u5668\u4e2d\u7684\u4f53\u79ef\u53d8\u5316\u3002");

    model.label("tank_flow_system.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
