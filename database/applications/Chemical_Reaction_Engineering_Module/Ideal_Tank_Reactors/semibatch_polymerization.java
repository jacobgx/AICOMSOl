/*
 * semibatch_polymerization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:18 by COMSOL 6.3.0.290. */
public class semibatch_polymerization {

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
    model.param().set("Vr_init", "10[m^3]", "\u521d\u59cb\u53cd\u5e94\u5668\u4f53\u79ef");
    model.param().set("cinlet_M", "density_M/Mm_M", "\u7269\u8d28 M \u7684\u5165\u53e3\u6d53\u5ea6");
    model.param().set("cinlet_P", "0[mol/m^3]", "\u7269\u8d28 P \u7684\u5165\u53e3\u6d53\u5ea6");
    model.param().set("cinlet_H2O", "11000[mol/m^3]", "\u6c34\u7684\u5165\u53e3\u6d53\u5ea6");
    model.param()
         .set("cinit_H2O", "density_H2O/Mm_H2O", "\u53cd\u5e94\u5668\u4e2d\u6c34\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("Mm_M", "0.1[kg/mol]", "\u7269\u8d28 M \u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("Mm_P", "0.1[kg/mol]", "\u7269\u8d28 P \u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("Mm_H2O", "0.018[kg/mol]", "\u6c34\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("density_M", "800[kg/m^3]", "\u7269\u8d28 M \u7684\u5bc6\u5ea6");
    model.param().set("density_P", "1100[kg/m^3]", "\u7269\u8d28 P \u7684\u5bc6\u5ea6");
    model.param().set("density_H2O", "1000[kg/m^3]", "\u6c34\u7684\u5bc6\u5ea6");
    model.param().set("kf_reaction", "(1/600)[1/s]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("vfmax", "1[m^3/min]", "\u6700\u5927\u4f53\u79ef\u8fdb\u7ed9\u7387");
    model.param().set("t_cond", "11.2[min]", "\u8fdb\u6599\u6539\u53d8\u7684\u65f6\u95f4");
    model.param().set("fill", "0", "\u586b\u6599\u63a7\u5236\u53c2\u6570");

    model.func().create("step1", "Step");
    model.func("step1").set("location", "t_cond");

    model.component("comp1").physics("re").prop("reactor").set("reactor", "semibatch");
    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "M=>P");
    model.component("comp1").physics("re").feature("rch1").set("kf", "kf_reaction");
    model.component("comp1").physics("re").feature("M").set("M", "Mm_M");
    model.component("comp1").physics("re").feature("M").set("rho", "density_M");
    model.component("comp1").physics("re").feature("P").set("M", "Mm_P");
    model.component("comp1").physics("re").feature("P").set("rho", "density_P");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("re").feature("H2O").set("M", "Mm_H2O");
    model.component("comp1").physics("re").feature("H2O").set("rho", "density_H2O");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("vf_cond1", "vfmax*(1-step1(t/1[s]))", "\u5de5\u4f5c\u6761\u4ef6 1 \u7684\u4f53\u79ef\u8fdb\u7ed9\u7387");
    model.component("comp1").variable("var1")
         .set("vf_condc", "-re.vp*step1(t/1[s])", "\u5de5\u4f5c\u6761\u4ef6 2 \u7684\u7528\u4e8e\u8865\u507f\u4f53\u79ef\u4ea7\u91cf\u7684\u4f53\u79ef\u8fdb\u7ed9\u7387");
    model.component("comp1").variable("var1").set("vfs", "vf_cond1+fill*vf_condc", "\u4f53\u79ef\u8fdb\u7ed9\u7387");
    model.component("comp1").variable("var1").set("m_mon", "re.c_M*re.M_M*re.Vr", "\u5355\u4f53\u8d28\u91cf");

    model.component("comp1").physics("re").create("feed1", "FeedStream", -1);
    model.component("comp1").physics("re").feature("feed1").set("vf", "vfs");
    model.component("comp1").physics("re").feature("feed1").setIndex("FeedSpeciesConcentration", "cinlet_H2O", 0, 0);
    model.component("comp1").physics("re").feature("feed1").setIndex("FeedSpeciesConcentration", "cinlet_M", 1, 0);
    model.component("comp1").physics("re").feature("inits1").set("Vr0", "Vr_init");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cinit_H2O", 0, 0);

    model.study("std1").feature("time").set("tlist", "range(0,0.1,3000)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_M", "re.c_P", "re.c_H2O"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "t");
    model.result("pg1").feature("glob1").set("xdataunit", "min");
    model.result("pg1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Vr_init", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^3", 0);
    model.study("std1").feature("param").setIndex("pname", "Vr_init", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^3", 0);
    model.study("std1").feature("param").setIndex("pname", "fill", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.c_M", "re.c_P", "re.c_H2O"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").label("\u6d53\u5ea6 (re) 1");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg2").run();
    model.result("pg2").label("\u4f53\u79ef\u8fdb\u6599\u901f\u7387");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.sumvf"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u4f53\u79ef\u6d41\u91cf\u603b\u548c"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"m^3/s"});
    model.result("pg2").feature("glob1").set("titletype", "manual");
    model.result("pg2").feature("glob1").set("title", "\u4f53\u79ef\u8fdb\u6599\u901f\u7387 (m<sup>3</sup>/s)");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "t");
    model.result("pg2").feature("glob1").set("xdataunit", "min");
    model.result("pg2").feature("glob1").set("linestyle", "cycle");
    model.result("pg2").feature("glob1").set("linecolor", "blue");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u5de5\u4f5c\u6761\u4ef6 1", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "\u5de5\u4f5c\u6761\u4ef6 2", 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u53cd\u5e94\u5668\u5bb9\u79ef");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"re.Vr"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u53cd\u5e94\u5668\u5bb9\u79ef"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"m^3"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "t");
    model.result("pg3").feature("glob1").set("xdataunit", "min");
    model.result("pg3").feature("glob1").set("linestyle", "cycle");
    model.result("pg3").feature("glob1").set("linecolor", "blue");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u5de5\u4f5c\u6761\u4ef6 1", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "\u5de5\u4f5c\u6761\u4ef6 2", 1);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u53cd\u5e94\u5668\u5bb9\u79ef (m<sup>3</sup>)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5355\u4f53\u8d28\u91cf");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"m_mon"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u5355\u4f53\u8d28\u91cf"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"kg"});
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "t");
    model.result("pg4").feature("glob1").set("xdataunit", "min");
    model.result("pg4").feature("glob1").set("linestyle", "cycle");
    model.result("pg4").feature("glob1").set("linecolor", "blue");
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u5de5\u4f5c\u6761\u4ef6 1", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "\u5de5\u4f5c\u6761\u4ef6 2", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u5355\u4f53\u8d28\u91cf (kg)");
    model.result("pg4").run();

    model.title("\u534a\u95f4\u6b47\u805a\u5408\u53cd\u5e94");

    model
         .description("\u9884\u5b9a\u4e49\u7684\u534a\u95f4\u6b47\u5f0f\u53cd\u5e94\u5668\u6a21\u578b\u7528\u6765\u5bf9\u6eb6\u6db2\u4e2d\u7684\u805a\u5408\u53cd\u5e94\u5efa\u6a21\u3002\u53cd\u5e94\u7269\u6df7\u5408\u6eb6\u6db2\u7684\u4f53\u79ef\u968f\u7740\u805a\u5408\u8fc7\u7a0b\u800c\u51cf\u5c0f\u3002\u4eff\u771f\u8868\u660e\u8003\u8651\u8fd9\u79cd\u4f53\u79ef\u53d8\u5316\u5c06\u663e\u8457\u63d0\u9ad8\u805a\u5408\u7269\u4ea7\u7387\u3002");

    model.label("semibatch_polymerization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
