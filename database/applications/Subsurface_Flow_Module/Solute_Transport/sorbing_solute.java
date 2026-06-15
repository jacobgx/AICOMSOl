/*
 * sorbing_solute.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:32 by COMSOL 6.3.0.290. */
public class sorbing_solute {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Solute_Transport");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowRichards", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpeciesInPorousMedia", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rhof", "1e3[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("rhob", "1400[kg/m^3]", "\u672c\u4f53\u5bc6\u5ea6");
    model.param().set("poro_1", "0.339", "\u5b54\u9699\u7387\uff0c\u5c42 1");
    model.param().set("poro_2", "0.399", "\u5b54\u9699\u7387\uff0c\u5c42 2");
    model.param().set("thetar_1", "0.001", "\u6b8b\u4f59\u6db2\u4f53\u4f53\u79ef\u5206\u6570\uff0c\u5c42 1");
    model.param().set("thetar_2", "0.001", "\u6b8b\u4f59\u6db2\u4f53\u4f53\u79ef\u5206\u6570\uff0c\u5c42 2");
    model.param().set("Sp_1", "(poro_1-thetar_1)/(1[m]*g_const*rhof)", "\u50a8\u6c34\u7cfb\u6570\uff0c\u5c42 1");
    model.param().set("Sp_2", "(poro_2-thetar_2)/(1[m]*g_const*rhof)", "\u50a8\u6c34\u7cfb\u6570\uff0c\u5c42 2");
    model.param().set("Ks_1", "0.454[m/d]", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387\uff0c\u5c42 1");
    model.param().set("Ks_2", "0.298[m/d]", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387\uff0c\u5c42 2");
    model.param().set("alpha_1", "1.39[1/m]", "Van Genuchten \u53c2\u6570 \u03b1\uff0c\u5c42 1");
    model.param().set("alpha_2", "1.74[1/m]", "Van Genuchten \u53c2\u6570 \u03b1\uff0c\u5c42 2");
    model.param().set("n_1", "1.6", "Van Genuchten \u53c2\u6570 n\uff0c\u5c42 1");
    model.param().set("n_2", "1.38", "Van Genuchten \u53c2\u6570 n\uff0c\u5c42 2");
    model.param().set("Hp0", "0.01[m]", "\u73af\u5f62\u6c34\u6c60\u4e2d\u7684\u538b\u529b\u6c34\u5934");
    model.param().set("kp", "1e-4[m^3/kg]", "\u5206\u914d\u7cfb\u6570");
    model.param().set("Dl", "3.74e-3[m^2/d]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("phil", "0.05[1/d]", "\u6db2\u4f53\u4e2d\u7684\u8870\u51cf\u7387");
    model.param().set("phip", "0.01[1/d]", "\u571f\u58e4\u4e2d\u7684\u8870\u51cf\u7387");
    model.param().set("alphar", "0.005[m]", "\u7eb5\u5411\u5f25\u6563\u5ea6");
    model.param().set("alphaz", "0.001[m]", "\u6a2a\u5411\u5f25\u6563\u5ea6");
    model.param().set("c0", "1[mol/m^3]", "\u73af\u5f62\u6c34\u6c60\u4e2d\u7684\u6eb6\u8d28\u6d53\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{1.5, 0.9});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, -1.3});
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.25, 0);
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{1.5, 0.4});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, -0.4});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.25, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("ie1").selection().set(3, 4);
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");

    model.component("comp1").physics("dl").feature("usporous1").set("storageModelType", "userdef");
    model.component("comp1").physics("dl").feature("usporous1").set("Sp", "Sp_1");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1")
         .set("Ks", new String[]{"Ks_1", "0", "0", "0", "Ks_1", "0", "0", "0", "Ks_1"});
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("alpha", "alpha_1");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("n", "n_1");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("theta_r", "thetar_1");
    model.component("comp1").physics("dl").feature().duplicate("usporous2", "usporous1");
    model.component("comp1").physics("dl").feature("usporous2").selection().set(2, 4);
    model.component("comp1").physics("dl").feature("usporous2").set("Sp", "Sp_2");
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1")
         .set("Ks", new String[]{"Ks_2", "0", "0", "0", "Ks_2", "0", "0", "0", "Ks_2"});
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1").set("alpha", "alpha_2");
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1").set("n", "n_2");
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1").set("theta_r", "thetar_2");
    model.component("comp1").physics("dl").feature("gr1").set("GravityType", "Elevation");
    model.component("comp1").physics("dl").feature("init1").set("InitType", "Hp");
    model.component("comp1").physics("dl").feature("init1").set("Hp", "-(z+1.2)");
    model.component("comp1").physics("dl").feature().duplicate("init2", "init1");
    model.component("comp1").physics("dl").feature("init2").selection().set(2, 4);
    model.component("comp1").physics("dl").feature("init2").set("Hp", "-(z+1.2)-0.2*(z+0.4)");
    model.component("comp1").physics("dl").create("ph1", "PressureHead", 1);
    model.component("comp1").physics("dl").feature("ph1").selection().set(5);
    model.component("comp1").physics("dl").feature("ph1").set("Hp0", "Hp0");
    model.component("comp1").physics("dl").create("pl1", "PerviousLayer", 1);
    model.component("comp1").physics("dl").feature("pl1").selection().set(2, 8);
    model.component("comp1").physics("dl").feature("pl1").set("Hb", -2);
    model.component("comp1").physics("dl").feature("pl1").set("Rb", "1/5[d]");
    model.component("comp1").physics("tds").create("usporous1", "UnsaturatedPorousMedium", 2);
    model.component("comp1").physics("tds").feature("usporous1").selection().all();
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("SaturationType", "LiquidVolumeFraction");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1").set("theta_l", "dl.theta_l");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("FluidFractionTimeChange", "TimeChangeInPressureHead");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("dHpdt_src", "root.comp1.dl.dHpdt");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1").set("Cm", "dl.Cm");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("DL_c", new String[]{"Dl", "0", "0", "0", "Dl", "0", "0", "0", "Dl"});
    model.component("comp1").physics("tds").feature("usporous1").create("ads1", "Adsorptions", 2);
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").set("SorptionType", "UserDefined");
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").setIndex("cP", "kp*c", 0);
    model.component("comp1").physics("tds").feature("usporous1").create("disp1", "Dispersion", 2);
    model.component("comp1").physics("tds").feature("usporous1").feature("disp1")
         .set("DispersionTensor", "Dispersivity");
    model.component("comp1").physics("tds").feature("usporous1").feature("disp1")
         .set("DispersivityModel", "TransverseIsotropic");
    model.component("comp1").physics("tds").feature("usporous1").feature("disp1")
         .set("alpha", new String[]{"alphar", "0", "alphaz"});
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").selection().all();
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_c", "(phip*kp*rhob-phil*dl.theta_l)*c", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(2, 8, 12, 13);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(5);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c0", 0);

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").label("\u591a\u5b54\u6750\u6599\uff1a\u4e0b\u5c42");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").propertyGroup("def")
         .set("density", new String[]{"rhof"});
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-poro_1");
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"rhob"});
    model.component("comp1").material().duplicate("pmat2", "pmat1");
    model.component("comp1").material("pmat2").label("\u591a\u5b54\u6750\u6599\uff1a\u4e0a\u5c42");
    model.component("comp1").material("pmat2").selection().set(2, 4);
    model.component("comp1").material("pmat2").feature("solid1").set("vfrac", "1-poro_2");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,0.9) range(1,1,5)");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6d41\u7f51 (dl)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "dl.H");
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "green");
    model.result("pg1").feature("con1").set("smooth", "internal");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("madv", "manual");
    model.result("pg1").feature("str1").set("msatfactor", 1.4);
    model.result("pg1").feature("str1").set("color", "blue");
    model.result("pg1").feature("str1").set("resolution", "extrafine");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    model.result("pg1").label("\u6d41\u7f51 (dl)");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(1, 2);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("con1").set("contourlabels", true);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().dataset("rev1").set("defaultPlotIDs", new String[]{"pg2|tds"});
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").setIndex("looplevel", 15, 0);
    model.result("pg2").label("\u6d53\u5ea6, 3D (tds)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").label("\u6d53\u5ea6, 3D (tds)");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", "last", 0);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u963b\u6ede\u56e0\u5b50");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "tds.RF_c");
    model.result("pg3").feature("surf1").set("descr", "\u963b\u6ede\u56e0\u5b50");
    model.result("pg3").feature("surf1").set("titletype", "custom");
    model.result("pg3").feature("surf1").set("unitintitle", false);
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("color", "black");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6709\u6548\u9971\u548c\u5ea6 (dl)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "dl.Se");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("contourtype", "filled");
    model.result("pg4").feature("con1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg4").feature("con1").set("colortabletrans", "reverse");
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg4").label("\u6709\u6548\u9971\u548c\u5ea6 (dl)");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").run();
    model.result("pg4").feature("con1").set("number", 15);
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").run();
    model.result("pg2").run();

    model.title("\u53d8\u9971\u548c\u6d41\u52a8\u4e0e\u4f20\u9012 - \u5438\u9644\u6eb6\u8d28");

    model
         .description("\u5728\u672c\u4f8b\u4e2d\uff0c\u5728\u5730\u9762\u4e0a\u5f62\u6210\u73af\u72b6\u7684\u79ef\u6c34\u643a\u5e26\u4e00\u79cd\u5316\u5b66\u7269\u8d28\u8fdb\u5165\u76f8\u5bf9\u5e72\u71e5\u7684\u571f\u58e4\u67f1\u4e2d\u3002\u5f53\u6c34\u7a7f\u8fc7\u53d8\u9971\u548c\u7684\u571f\u58e4\u67f1\u65f6\uff0c\u5316\u5b66\u7269\u8d28\u4f1a\u9644\u7740\u5230\u56fa\u4f53\u9897\u7c92\u4e0a\uff0c\u4ece\u800c\u51cf\u7f13\u6eb6\u8d28\u76f8\u5bf9\u4e8e\u6c34\u7684\u8fd0\u79fb\u901f\u5ea6\u3002\u6b64\u5916\uff0c\u5728\u6db2\u76f8\u548c\u56fa\u76f8\u4e2d\uff0c\u5316\u5b66\u6d53\u5ea6\u90fd\u4f1a\u56e0\u751f\u7269\u964d\u89e3\u800c\u8870\u51cf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("sorbing_solute.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
