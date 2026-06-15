/*
 * ground_motion_seismic_event.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:07 by COMSOL 6.3.0.290. */
public class ground_motion_seismic_event {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Elastic_Waves");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("elte", "ElasticWavesTimeExplicit", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/elte", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Gm", "0.2", "\u5c71\u4f53\u9ad8\u5ea6");
    model.param().set("f0", "2[Hz]", "\u6e90\u4e2d\u5fc3\u9891\u7387");
    model.param().set("t0", "0.6[s]", "\u6e90\u65f6\u79fb");
    model.param().set("dS", "1e3[1/m^2]", "\u6e90\u8303\u56f4");
    model.param().set("cp", "3.2[km/s]", "\u538b\u529b\u6ce2\u901f");
    model.param().set("cs", "1.8475[km/s]", "\u526a\u5207\u6ce2\u901f");
    model.param().set("rho", "2200[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("K", "rho*(cp^2 - 4*cs^2/3)", "\u4f53\u79ef\u6a21\u91cf");
    model.param().set("G", "rho*cs^2", "\u526a\u5207\u6a21\u91cf");
    model.param().set("nu", "0.5*(1 - 3*G/(3*K + G))", "\u6cca\u677e\u6bd4");
    model.param().set("cr", "cs*(0.87 + 1.12*nu)/(1 + nu)", "\u745e\u5229\u6ce2\u901f\u5ea6");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "G_space");
    model.func("an1").set("expr", "1/sqrt(pi*dS)*exp(-x^2/dS)");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "1");
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "G_time");
    model.func("an2").set("expr", "1e9*(2*(pi*f0*(t - t0))^2 - 1)*exp(-(pi*f0*(t - t0))^2)");
    model.func("an2").set("args", "t");
    model.func("an2").setIndex("argunit", "s", 0);
    model.func("an2").set("fununit", "Pa");
    model.func("an2").setIndex("plotargs", 3, 0, 2);
    model.func("an2").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").label("\u8109\u51b2\u9891\u7387\u8c31");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "FFT of G_time (Pa)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u4fe1\u53f7\u7684 FFT \u7ed3\u679c (Pa)");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("display", "fourier");
    model.result("pg1").feature("plot1").set("fouriershow", "spectrum");
    model.result("pg1").feature("plot1").set("scale", "multiplyperiod");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("freqrangeactive", true);
    model.result("pg1").feature("plot1").set("freqmax", 10);
    model.result("pg1").run();

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"40[km]", "20[km]"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-20[km]", "-20[km]"});
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "2[km]", 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", "2[km]");
    model.component("comp1").geom("geom1").feature("pc1")
         .set("coord", new String[]{"s", "0.2*(exp(-((s - 1[km])/0.3[km])^2) - exp(-(1[km]/0.3[km])^2))[km]"});
    model.component("comp1").geom("geom1").feature("pc1").set("pos", new String[]{"-6[km]", "0"});
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"2.4[km]", "0.7[km]"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-6.2[km]", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("pc1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input").set("fin", 4, 5);
    model.component("comp1").geom("geom1").run("cmd1");

    model.component("comp1").coordSystem().create("ab1", "AbsorbingLayer");
    model.component("comp1").coordSystem("ab1").selection().set(1, 2, 3, 5, 6);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5730\u9762\u6750\u6599");

    model.component("comp1").physics("elte").feature("eltem1").set("IsotropicOption", "CpCs");

    model.component("comp1").material("mat1").propertyGroup()
         .create("CpCs", "CpCs", "Pressure_wave_and_shear_wave_speeds");
    model.component("comp1").material("mat1").propertyGroup("CpCs").set("cp", new String[]{"3.2[km/s]"});
    model.component("comp1").material("mat1").propertyGroup("CpCs").set("cs", new String[]{"1.8475[km/s]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2200[kg/m^3]"});

    model.component("comp1").physics("elte").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("elte").feature("bndl1").selection().set(10, 11);
    model.component("comp1").physics("elte").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "G_space(x)*G_time(t)", "0"});
    model.component("comp1").physics("elte").create("lrb1", "LowReflectingBoundary", 1);
    model.component("comp1").physics("elte").feature("lrb1").selection().set(1, 2, 3, 7, 13, 17, 18);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "cr/(2*f0)/1.5");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0, 1, 12)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6\u5927\u5c0f (elte)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 6);
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (elte)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "elte.p");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("resolution", "custom");
    model.result("pg3").feature("surf1").set("refine", 6);
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(4);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortable", "AuroraAustralis");
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormax", 0.5);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 7, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 10, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 7, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("unit", "MPa");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u538b\u529b\u548c\u526a\u5207\u6ce2");
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "sqrt(abs(elte.I2s))*sign(elte.I2s)");
    model.result("pg4").feature("surf1").set("unit", "MPa");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1")
         .set("descr", "\u7b2c\u4e8c\u4e3b\u4e0d\u53d8\u91cf\u7684\u5e26\u7b26\u53f7\u5e73\u65b9\u6839");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", -0.24);
    model.result("pg4").feature("surf1").set("rangecolormax", 0.3);
    model.result("pg4").feature("surf1").set("colortable", "Twilight");
    model.result("pg4").run();
    model.result("pg4").set("showlegends", false);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 10, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 13, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").run();
    model.result("pg4").set("showlegends", true);

    model.title("\u5730\u9707\u540e\u7684\u5730\u9762\u8fd0\u52a8\uff1a\u4e00\u5ea7\u5c0f\u5c71\u7684\u6563\u5c04");

    model
         .description("\u672c\u6559\u7a0b\u91c7\u7528\u4e8c\u7ef4\u6a21\u578b\u6a21\u62df\u5730\u9707\u540e\u5f39\u6027\u6ce2\u5728\u5730\u9762\u7684\u4f20\u64ad\uff0c\u7814\u7a76\u4e86\u7406\u60f3\u534a\u7a7a\u95f4\u5728\u6709\u5c0f\u5c71\u5b58\u5728\u65f6\uff0c\u5730\u9762\u62d3\u6251\u7ed3\u6784\u5bf9\u6ce2\u4f20\u64ad\u7684\u5f71\u54cd\u3002\u8fd9\u4e2a\u6a21\u578b\u662f Lamb \u95ee\u9898\u7684\u53d8\u4f53\u3002\u6a21\u578b\u4e2d\u4f7f\u7528\u201c\u5f39\u6027\u6ce2\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u7269\u7406\u573a\u63a5\u53e3\u6a21\u62df\u5f39\u6027\u6ce2\u7684\u4f20\u64ad\uff0c\u83b7\u5f97\u4e86\u538b\u529b\u6ce2\u3001\u526a\u5207\u6ce2\u3001\u745e\u5229\u6ce2\u548c\u51af\u65bd\u5bc6\u7279\u6ce2\u7684\u4f20\u64ad\u548c\u6563\u5c04\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("ground_motion_seismic_event.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
