/*
 * zone_electrophoresis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:17 by COMSOL 6.3.0.290. */
public class zone_electrophoresis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Electrokinetic_Effects");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("el", "ElectrophoreticTransport", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/el", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/el", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "20[cm]", "\u95f4\u9694\u957f\u5ea6");
    model.param().set("T", "25[degC]", "\u6e29\u5ea6");
    model.param().set("mob_HAC", "4.24e-8[m^2/V/s]/F_const", "\u8fc1\u79fb\u7387");
    model.param().set("mob_tris", "2.41e-8[m^2/(V*s)]/F_const", "\u8fc1\u79fb\u7387");
    model.param().set("mob_ani", "3.25e-8[m^2/(V*s)]/F_const", "\u8fc1\u79fb\u7387");
    model.param().set("mob_pyr", "3.0e-8[m^2/(V*s)]/F_const", "\u8fc1\u79fb\u7387");
    model.param().set("HAC_c0", "20[mM]", "\u521d\u59cb\u6d53\u5ea6");
    model.param().set("tris_c0", "12[mM]", "\u521d\u59cb\u6d53\u5ea6");
    model.param().set("pyr_c0", "1[mM]", "\u521d\u59cb\u6d53\u5ea6");
    model.param().set("ani_c0", "1[mM]", "\u521d\u59cb\u6d53\u5ea6");
    model.param().set("pKa_HAC", "4.76", "\u9178\u5e38\u6570\uff08\u5bf9\u6570\uff09");
    model.param().set("pKa_tris", "8.3", "\u9178\u5e38\u6570\uff08\u5bf9\u6570\uff09");
    model.param().set("pKa_ani", "4.8", "\u9178\u5e38\u6570\uff08\u5bf9\u6570\uff09");
    model.param().set("pKa_pyr", "5.18", "\u9178\u5e38\u6570\uff08\u5bf9\u6570\uff09");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("el").create("wb1", "WeakBase", 1);
    model.component("comp1").physics("el").feature("wb1").label("\u5f31\u78b1 - tris");
    model.component("comp1").physics("el").feature("wb1").set("speciesname", "tris");
    model.component("comp1").physics("el").feature("wb1").set("pKam", "pKa_tris");
    model.component("comp1").physics("el").feature("wb1").set("um", "mob_tris");
    model.component("comp1").physics("el").feature("wb1").feature("initc1").set("initc", "tris_c0");
    model.component("comp1").physics("el").feature("wb1").create("conc1", "Concentration", 0);
    model.component("comp1").physics("el").feature("wb1").feature("conc1").selection().set(1);
    model.component("comp1").physics("el").feature("wb1").feature("conc1").set("c0", "tris_c0");
    model.component("comp1").physics("el").feature("wb1").create("out1", "Outflow", 0);
    model.component("comp1").physics("el").feature("wb1").feature("out1").selection().set(2);
    model.component("comp1").physics("el").create("wa1", "WeakAcid", 1);
    model.component("comp1").physics("el").feature("wa1").label("\u5f31\u9178 - HAC");
    model.component("comp1").physics("el").feature("wa1").set("speciesname", "HAC");
    model.component("comp1").physics("el").feature("wa1").set("pKam", "pKa_HAC");
    model.component("comp1").physics("el").feature("wa1").set("um", "mob_HAC");
    model.component("comp1").physics("el").feature("wa1").feature("initc1").set("initc", "HAC_c0");
    model.component("comp1").physics("el").feature("wa1").create("conc1", "Concentration", 0);
    model.component("comp1").physics("el").feature("wa1").feature("conc1").selection().set(2);
    model.component("comp1").physics("el").feature("wa1").feature("conc1").set("c0", "HAC_c0");
    model.component("comp1").physics("el").feature("wa1").create("out1", "Outflow", 0);
    model.component("comp1").physics("el").feature("wa1").feature("out1").selection().set(1);
    model.component("comp1").physics("el").feature().duplicate("wb2", "wb1");
    model.component("comp1").physics("el").feature("wb2").label("\u5f31\u78b1 - ani");
    model.component("comp1").physics("el").feature("wb2").set("speciesname", "ani");
    model.component("comp1").physics("el").feature("wb2").set("pKam", "pKa_ani");
    model.component("comp1").physics("el").feature("wb2").set("um", "mob_ani");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("\u6837\u672c\u51fd\u6570");
    model.component("comp1").func("an1").set("funcname", "sample");
    model.component("comp1").func("an1").set("expr", "rect1((x-L*0.0375)/(L*0.025))");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").set("fununit", "1");
    model.component("comp1").func("an1").setIndex("plotargs", "L", 0, 2);

    model.component("comp1").physics("el").feature("wb2").feature("initc1").set("initc", "sample(x)*ani_c0");
    model.component("comp1").physics("el").feature("wb2").feature("conc1").set("c0", 0);
    model.component("comp1").physics("el").feature().duplicate("wb3", "wb2");
    model.component("comp1").physics("el").feature("wb3").label("\u5f31\u78b1 - pyr");
    model.component("comp1").physics("el").feature("wb3").set("speciesname", "pyr");
    model.component("comp1").physics("el").feature("wb3").set("pKam", "pKa_pyr");
    model.component("comp1").physics("el").feature("wb3").set("um", "mob_pyr");
    model.component("comp1").physics("el").feature("wb3").feature("initc1").set("initc", "sample(x)*pyr_c0");
    model.component("comp1").physics("el").create("eip1", "ElectrolytePotential", 0);
    model.component("comp1").physics("el").feature("eip1").selection().set(1);
    model.component("comp1").physics("el").create("icd1", "ElectrolyteNormalCurrentDensity", 0);
    model.component("comp1").physics("el").feature("icd1").selection().set(2);
    model.component("comp1").physics("el").feature("icd1").set("nil", "-2500[A/m^2]");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 1000);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,0.5,5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"el.pH"});
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").label("pH (el)");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"el.sigmal"});
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u5bfc\u7387 (el)");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"phil"});
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (el)");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"el.c_tris"});
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").label("\u6469\u5c14\u6d53\u5ea6 - tris (el)");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"el.c_HAC"});
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").label("\u6469\u5c14\u6d53\u5ea6 - HAC (el)");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"el.c_ani"});
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").label("\u6469\u5c14\u6d53\u5ea6 - ani (el)");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"el.c_pyr"});
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").label("\u6469\u5c14\u6d53\u5ea6 - pyr (el)");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{1, 9}, 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("linewidth", 2);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", new int[]{1, 9}, 0);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevel", new int[]{1, 9}, 0);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", new int[]{1, 9}, 0);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("linewidth", 2);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{1, 9}, 0);
    model.result("pg5").set("titletype", "label");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{1, 9}, 0);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{1, 9}, 0);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u6837\u54c1\u6d53\u5ea6");
    model.result("pg8").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg8").set("titletype", "label");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("unit", "mM");
    model.result("pg8").feature("lngr1").set("xdataunit", "cm");
    model.result("pg8").feature("lngr1").set("linewidth", 2);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").setIndex("legends", "\u5421\u5576", 0);
    model.result("pg8").feature().duplicate("lngr2", "lngr1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "el.c_ani");
    model.result("pg8").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg8").feature("lngr2").setIndex("legends", "\u82ef\u80fa", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", new int[]{7}, 0);
    model.result("pg8").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg8");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("frametime", 0.5);
    model.result().export("anim1").run();

    model.title("\u533a\u5e26\u7535\u6cf3");

    model
         .description("\u533a\u5e26\u7535\u6cf3 (ZE) \u662f\u4e00\u79cd\u901a\u5e38\u7528\u4e8e\u5206\u6790\u86cb\u767d\u8d28\u3001\u6838\u9178\u548c\u751f\u7269\u805a\u5408\u7269\u7684\u7535\u6cf3\u5206\u79bb\u6280\u672f\u3002\u5728\u6b64\u8fc7\u7a0b\u4e2d\uff0c\u6837\u54c1\u4e2d\u7684\u4e0d\u540c\u7269\u8d28\u5728\u5177\u6709\u7535\u4f4d\u68af\u5ea6\u7684\u3001\u8fde\u7eed\u7535\u89e3\u8d28\u7f13\u51b2\u7cfb\u7edf\u4e2d\u4f20\u8f93\u3002\u7531\u4e8e\u8fc1\u79fb\u7387\u7684\u5dee\u5f02\uff0c\u6837\u54c1\u4e2d\u7684\u4e0d\u540c\u7269\u8d28\u6700\u7ec8\u5c06\u5206\u79bb\u6210\u6e05\u6670\u53ef\u89c1\u7684\u4e0d\u540c\u5cf0\u3002\n\n\u672c\u6559\u7a0b\u4ecb\u7ecd\u7535\u6cf3\u8f93\u9001 \u63a5\u53e3\u3002\u542b\u6709\u4e59\u9178\u548c\u4e09(\u7f9f\u7532\u57fa)\u6c28\u57fa\u7532\u70f7\u7684\u7f13\u51b2\u7cfb\u7edf\u7528\u4e8e\u5c06\u542b\u6709\u5421\u5576\u548c\u82ef\u80fa\u7684\u6837\u54c1\u5206\u79bb\u4e3a\u4e24\u4e2a\u6e05\u6670\u53ef\u89c1\u7684\u5cf0\u3002");

    model.label("zone_electrophoresis.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
