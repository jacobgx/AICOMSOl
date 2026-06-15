/*
 * liquid_chromatography.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:20 by COMSOL 6.3.0.290. */
public class liquid_chromatography {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpeciesInPorousMedia", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("c1");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"c1", "c2"});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_c", "120[mm]", "\u67f1\u957f\u5ea6");
    model.param()
         .set("c01", "1[mol/m^3]", "\u8fdb\u6837\u5668\u5165\u53e3\u7684\u6700\u5927\u6d53\u5ea6\uff0c\u7ec4\u5206 1");
    model.param()
         .set("c02", "10[mol/m^3]", "\u8fdb\u6837\u5668\u5165\u53e3\u7684\u6700\u5927\u6d53\u5ea6\uff0c\u7ec4\u5206 2");
    model.param().set("S", "1e5[m^2/kg]", "\u9897\u7c92\u6bd4\u8868\u9762\u79ef");
    model.param().set("rho_p", "2300[kg/m^3]", "\u56fa\u4f53\u6750\u6599\u9897\u7c92\u5bc6\u5ea6");
    model.param().set("eps_p", "0.6", "\u5b54\u9699\u7387");
    model.param().set("v_l", "1.3[mm/s]", "\u7ebf\u6027\u901f\u5ea6\uff0c\u6d41\u52a8\u76f8");
    model.param().set("D_1", "1e-8[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u7ec4\u5206 1");
    model.param().set("D_2", "1e-8[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u7ec4\u5206 2");
    model.param().set("K1", "0.04[m^3/mol]", "Langmuir \u5438\u9644\u5e38\u6570\uff0c\u7ec4\u5206 1");
    model.param().set("K2", "0.05[m^3/mol]", "Langmuir \u5438\u9644\u5e38\u6570\uff0c\u7ec4\u5206 2");
    model.param().set("n01", "1e-6[mol/m^2]", "\u5355\u5c42\u5438\u9644\u91cf\uff0c\u7ec4\u5206 1");
    model.param().set("n02", "5e-7[mol/m^2]", "\u5355\u5c42\u5438\u9644\u91cf\uff0c\u7ec4\u5206 2");

    model.func().create("gp1", "GaussianPulse");
    model.func("gp1").label("\u6ce8\u5165\u8109\u51b2");
    model.func("gp1").set("funcname", "p1");
    model.func("gp1").set("location", 3);

    model.variable().create("var1");
    model.variable("var1").set("pulse_inj", "2.5*p1(t/1[s])");
    model.variable("var1").descr("pulse_inj", "\u5e45\u503c\u4e3a 1 \u7684\u77ac\u6001\u6ce8\u5165\u8109\u51b2");
    model.variable("var1").set("rho_c", "rho_p*(1-eps_p)");
    model.variable("var1").descr("rho_c", "\u67f1\u4e2d\u4ecb\u8d28\u7684\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_c", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tds").feature("porous1").create("ads1", "Adsorptions", 1);
    model.component("comp1").physics("tds").feature("porous1").feature("ads1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("porous1").feature("ads1").setIndex("species", true, 1);
    model.component("comp1").physics("tds").feature("porous1").feature("ads1").set("rho_db_mat", "userdef");
    model.component("comp1").physics("tds").feature("porous1").feature("ads1").set("rho_db", "rho_c");
    model.component("comp1").physics("tds").feature("porous1").feature("ads1").setIndex("KL", "K1", 0);
    model.component("comp1").physics("tds").feature("porous1").feature("ads1").setIndex("cPmax", "S*n01", 0);
    model.component("comp1").physics("tds").feature("porous1").feature("ads1").setIndex("KL", "K2", 1);
    model.component("comp1").physics("tds").feature("porous1").feature("ads1").setIndex("cPmax", "S*n02", 1);
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("u", new String[]{"v_l", "0", "0"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_c1", new String[]{"D_1", "0", "0", "0", "D_1", "0", "0", "0", "D_1"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_c2", new String[]{"D_2", "0", "0", "0", "D_2", "0", "0", "0", "D_2"});
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro", "eps_p");
    model.component("comp1").physics("tds").create("in1", "Inflow", 0);
    model.component("comp1").physics("tds").feature("in1").selection().set(1);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "c01*pulse_inj", 0);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "c02*pulse_inj", 1);
    model.component("comp1").physics("tds").create("out1", "Outflow", 0);
    model.component("comp1").physics("tds").feature("out1").selection().set(2);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1e-4");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,1,420)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "0.0010");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_c1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_c1").set("scaleval", "c01");
    model.sol("sol1").feature("v1").feature("comp1_c2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_c2").set("scaleval", "c02");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("atolglobal", "1e-4");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg1").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tds)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"c1"});
    model.result("pg1").feature("lngr1").label("\u7269\u8d28 c1");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autosolution", false);
    model.result("pg1").feature("lngr1").set("autoexpr", false);
    model.result("pg1").feature("lngr1").set("autodescr", false);
    model.result("pg1").feature("lngr1").set("legendprefix", "c1 ");
    model.result("pg1").feature("lngr1").set("descractive", true);
    model.result("pg1").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "x");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature("lngr2").set("expr", new String[]{"c2"});
    model.result("pg1").feature("lngr2").label("\u7269\u8d28 c2");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("autosolution", false);
    model.result("pg1").feature("lngr2").set("autoexpr", false);
    model.result("pg1").feature("lngr2").set("autodescr", false);
    model.result("pg1").feature("lngr2").set("legendprefix", "c2 ");
    model.result("pg1").feature("lngr2").set("descractive", true);
    model.result("pg1").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u6d53\u5ea6, c1 (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"c1"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, c2 (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"c2"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "interp", 0);
    model.result("pg1").setIndex("interp", "10 100 200 300", 0);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u67f1\u957f\u5ea6 (m)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("ymin", 0);
    model.result("pg1").set("legendcolumncount", 2);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("linewidth", 2);
    model.result("pg1").feature("lngr1").set("autosolution", true);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg1").feature("lngr2").set("linewidth", 2);
    model.result("pg1").feature("lngr2").set("autosolution", true);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "interp", 0);
    model.result("pg2").setIndex("interp", "5 50 100 200 300 385", 0);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u67f1\u957f\u5ea6 (m)");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("ymin", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").setIndex("interp", "5 50 100 200 300 385", 0);
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u67f1\u957f\u5ea6 (m)");
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("ymin", 0);
    model.result("pg3").set("legendlayout", "outside");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("linewidth", 2);
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u68c0\u6d4b\u5230\u7684\u6d53\u5ea6");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(2);
    model.result("pg4").feature("ptgr1").set("expr", "c1+c2");
    model.result("pg4").feature("ptgr1").set("linewidth", 2);
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg4").run();
    model.result().export().create("anim1", "Animation");
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
    model.result().export("anim1").label("\u6db2\u76f8\u8272\u8c31\u52a8\u753b");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("looplevelinput", "interp");
    model.result().export("anim1").set("interp", "range(0,2,420)");
    model.result().export("anim1").run();

    model.title("\u6db2\u76f8\u8272\u8c31\u6cd5");

    model
         .description("\u8272\u8c31\u6cd5\u662f\u7528\u4e8e\u5206\u79bb\u590d\u6742\u6df7\u5408\u7269\u4e2d\u5bc6\u5207\u76f8\u5173\u7ec4\u5206\u7684\u4e00\u7ec4\u91cd\u8981\u65b9\u6cd5\u3002\u672c\u4f8b\u6a21\u62df\u9ad8\u6548\u6db2\u76f8\u8272\u8c31 (HPLC) \u67f1\u4e2d\u7684\u5206\u79bb\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("liquid_chromatography.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
