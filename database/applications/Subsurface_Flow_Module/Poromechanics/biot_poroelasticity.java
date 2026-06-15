/*
 * biot_poroelasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:31 by COMSOL 6.3.0.290. */
public class biot_poroelasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Poromechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{5200, 440});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -440});
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 20, 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 20, 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{1200, 320});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{4000, -440});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1, 3);
    model.component("comp1").variable("var1").set("S_sk", "1e-5[m^-1]");
    model.component("comp1").variable("var1").descr("S_sk", "\u571f\u4f53\u9aa8\u67b6\u6bd4\u50a8\u6c34\u7387");
    model.component("comp1").variable("var1").set("K_s", "25[m/day]");
    model.component("comp1").variable("var1").descr("K_s", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(2);
    model.component("comp1").variable("var2")
         .set("S_sk", "1e-4[m^-1]", "\u571f\u4f53\u9aa8\u67b6\u6bd4\u50a8\u6c34\u7387");
    model.component("comp1").variable("var2")
         .set("K_s", "0.01[m/day]", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u6d41\u4f53");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().set(1, 3);
    model.component("comp1").material("pmat1").set("porosity", "0.25");
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material().duplicate("pmat2", "pmat1");
    model.component("comp1").material("pmat2").selection().set(2);
    model.component("comp1").material("pmat2").set("porosity", "0.025");

    model.component("comp1").physics("dl").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl").feature("gr1").set("GravityType", "Elevation");
    model.component("comp1").physics("dl").feature("porous1").set("storageModelType", "userdef");
    model.component("comp1").physics("dl").feature("porous1").set("Sp", "S_sk/dl.rho/g_const");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1")
         .set("fluidType", "compressibleLinearized");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("K", new String[]{"K_s", "0", "0", "0", "K_s", "0", "0", "0", "K_s"});

    model.material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.material("mat1").propertyGroup("def").set("compressibility", new String[]{"4e-10"});

    model.component("comp1").physics("dl").create("hh1", "HydraulicHead", 1);
    model.component("comp1").physics("dl").feature("hh1").selection().set(1);
    model.component("comp1").physics("dl").feature("hh1").set("H0", "-6[m/year]*t");
    model.component("comp1").physics("dl").create("hh2", "HydraulicHead", 1);
    model.component("comp1").physics("dl").feature("hh2").selection().set(5, 7, 12);

    model.component("comp1").cpl().create("genproj1", "GeneralProjection");
    model.component("comp1").cpl("genproj1").selection().all();

    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").set("b", "genproj1(-dl.H*(y<=dest(y))*S_sk)");
    model.component("comp1").variable("var3").descr("b", "\u56fa\u7ed3");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("yscale", 10);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,1,10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.result("pg1").run();
    model.result("pg1").label("\u6c34\u5934");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u65f6\u95f4 = 10 \u5e74\uff0c\u8868\u9762\uff1a\u6c34\u5934 (m) \u6d41\u7ebf\uff1a\u901f\u5ea6\u573a");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "dl.H");
    model.result("pg1").feature("surf1").set("descr", "\u6c34\u5934");
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u56fa\u7ed3");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "b");
    model.result("pg2").feature("con1").set("descr", "\u56fa\u7ed3");
    model.result("pg2").feature("con1").set("number", 15);
    model.result("pg2").feature("con1").set("contourtype", "filled");
    model.result("pg2").feature("con1").set("colortable", "Cividis");
    model.result("pg2").run();

    model.title("Terzaghi \u56fa\u7ed3");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6c42\u89e3\u4f20\u7edf\u7684\u9971\u548c\u6d41\u4f53\u6d41\u52a8\u95ee\u9898\u6765\u7814\u7a76\u6c34\u5934\u53d8\u5316\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u7528\u4e8e\u8bc4\u4f30\u6c89\u79ef\u7269\u7684\u5782\u76f4\u538b\u5b9e\u3002\u8be5\u5206\u6790\u4ee5 Terzaghi \u7406\u8bba\u548c\u6709\u6548\u5e94\u529b\u6982\u5ff5\u4e3a\u57fa\u7840\uff0c\u5e76\u5c06\u6c42\u89e3\u7ed3\u679c\u4e0e\u516c\u5f00\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u672c\u4f8b\u901a\u8fc7\u5728\u201cBiot \u591a\u5b54\u5f39\u6027\u201d\u6a21\u578b\u4e2d\u8fdb\u884c\u4fee\u6b63\uff0c\u4ee5\u5206\u6790\u53cc\u5411\u8026\u5408\u7684\u6d41\u4f53\u6d41\u52a8\u548c\u56fa\u4f53\u53d8\u5f62\u3002");

    model.label("terzaghi_compaction.mph");

    model.result("pg2").run();

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/solid", false);

    model.component("comp1").multiphysics().create("poro1", "PoroelasticCoupling", 2);

    model.study("std1").feature("time").setSolveFor("/multiphysics/poro1", false);

    model.component("comp1").multiphysics("poro1").selection().all();
    model.component("comp1").multiphysics("poro1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("poro1").set("PorousMediaFlow_physics", "dl");

    model.component("comp1").physics("dl").feature("porous1").set("storageModelType", new String[]{"poroelastic"});
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2, 8, 9);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 3, 5);

    model.component("comp1").multiphysics("poro1").set("alphaB_mat", "userdef");

    model.component("comp1").material("pmat1").propertyGroup("def").set("density", new String[]{"2750"});
    model.component("comp1").material("pmat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("pmat1").propertyGroup("Enu").set("E", new String[]{"800[MPa]"});
    model.component("comp1").material("pmat1").propertyGroup("Enu").set("nu", new String[]{"0.25"});
    model.component("comp1").material("pmat2").propertyGroup("def").set("density", new String[]{"2750"});
    model.component("comp1").material("pmat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("pmat2").propertyGroup("Enu").set("E", new String[]{"80[MPa]"});
    model.component("comp1").material("pmat2").propertyGroup("Enu").set("nu", new String[]{"0.25"});

    model.component("comp1").variable("var3").set("Q_biot", "-poro1.alphaB*d(solid.evol,TIME)*dl.rho");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/dl", true);
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/poro1", true);
    model.study("std2").feature("time").set("tunit", "a");
    model.study("std2").feature("time").set("tlist", "range(0,0.01,0.2) range(1,1,10)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (dl)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").set("smooth", "internal");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 31, 0);
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u6c34\u5934\uff0c\u591a\u5b54\u5f39\u6027");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "dl.H");
    model.result("pg5").feature("surf1").set("descr", "\u6c34\u5934");
    model.result("pg5").run();
    model.result("pg5").create("con1", "Contour");
    model.result("pg5").feature("con1").set("expr", "solid.disp");
    model.result("pg5").feature("con1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg5").feature("con1").set("levelmethod", "levels");
    model.result("pg5").feature("con1").set("levels", "range(0,0.2,2.3)");
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("color", "black");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").feature("con1").set("resolution", "extrafine");
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"d(dl.H,X)", "d(dl.H,Y)"});
    model.result("pg5").feature("arws1").set("descractive", true);
    model.result("pg5").feature("arws1").set("descr", "\u6c34\u5934\u68af\u5ea6 (1)");
    model.result("pg5").feature("arws1").set("xnumber", 25);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def1").set("scale", 10);
    model.result("pg5").run();
    model.result("pg5").feature("con1").feature().copy("def1", "pg5/surf1/def1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("arws1").set("scaleactive", true);
    model.result("pg5").run();
    model.result("pg5").feature("arws1").set("scale", "2e4");
    model.result("pg5").feature("arws1").feature().copy("def1", "pg5/surf1/def1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 23, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 26, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 31, 0);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").label("von Mises \u5e94\u529b");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("threshold", "none");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", 10);
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("startmethod", "coord");
    model.result("pg4").feature("str1").set("xcoord", 0);
    model.result("pg4").feature("str1").set("ycoord", "range(-450,50,-50)");
    model.result("pg4").feature("str1").set("color", "white");
    model.result("pg4").feature("str1").set("smooth", "everywhere");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature().copy("def", "pg4/surf1/def");
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u56fa\u6db2\u8026\u5408\u9879");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "Q_biot");
    model.result("pg6").feature("surf1").set("descr", "");
    model.result("pg6").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").setIndex("genpoints", 1000, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 5000, 1, 0);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6c34\u5e73\u5e94\u53d8");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{23, 26, 31}, 0);
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "solid.eXX");
    model.result("pg7").feature("lngr1").set("descr", "\u5e94\u53d8\u5f20\u91cf\uff0cXX \u5206\u91cf");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "X");
    model.result("pg7").feature("lngr1").set("xdatadescr", "X \u5750\u6807");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg5").run();
    model.result().duplicate("pg8", "pg5");
    model.result("pg8").run();
    model.result("pg8").label("\u6c34\u5934\uff0c\u591a\u5b54\u5f39\u6027\uff0c\u9635\u5217");
    model.result("pg8").set("plotarrayenable", true);
    model.result("pg8").set("arrayshape", "square");
    model.result("pg8").set("relrowpadding", 0.02);
    model.result("pg8").setIndex("looplevel", 23, 0);
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("manualindexing", true);
    model.result("pg8").feature("surf1").set("rowindex", 1);
    model.result("pg8").feature("con1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("con1").set("manualindexing", true);
    model.result("pg8").feature("con1").set("rowindex", 1);
    model.result("pg8").feature("arws1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws1").set("manualindexing", true);
    model.result("pg8").feature("arws1").set("rowindex", 1);
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf2", "surf1");
    model.result("pg8").feature().duplicate("con2", "con1");
    model.result("pg8").feature().duplicate("arws2", "arws1");
    model.result("pg8").feature("surf2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").set("data", "dset2");
    model.result("pg8").feature("surf2").setIndex("looplevel", 26, 0);
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").feature("surf2").set("colindex", 1);
    model.result("pg8").feature("con2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("con2").set("data", "dset2");
    model.result("pg8").feature("con2").setIndex("looplevel", 26, 0);
    model.result("pg8").feature("con2").set("colindex", 1);
    model.result("pg8").feature("arws2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws2").set("data", "dset2");
    model.result("pg8").feature("arws2").setIndex("looplevel", 26, 0);
    model.result("pg8").feature("arws2").set("colindex", 1);
    model.result("pg8").feature("surf2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf3", "surf2");
    model.result("pg8").feature().duplicate("con3", "con2");
    model.result("pg8").feature().duplicate("arws3", "arws2");
    model.result("pg8").feature("surf3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").setIndex("looplevel", 29, 0);
    model.result("pg8").feature("surf3").set("rowindex", 0);
    model.result("pg8").feature("surf3").set("colindex", 0);
    model.result("pg8").feature("con3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("con3").setIndex("looplevel", 29, 0);
    model.result("pg8").feature("con3").set("rowindex", 0);
    model.result("pg8").feature("con3").set("colindex", 0);
    model.result("pg8").feature("arws3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws3").setIndex("looplevel", 29, 0);
    model.result("pg8").feature("arws3").set("rowindex", 0);
    model.result("pg8").feature("arws3").set("colindex", 0);
    model.result("pg8").feature("surf3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf4", "surf3");
    model.result("pg8").feature().duplicate("con4", "con3");
    model.result("pg8").feature().duplicate("arws4", "arws3");
    model.result("pg8").feature("surf4").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf4").setIndex("looplevel", 31, 0);
    model.result("pg8").feature("surf4").set("colindex", 1);
    model.result("pg8").feature("con4").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("con4").setIndex("looplevel", 31, 0);
    model.result("pg8").feature("con4").set("colindex", 1);
    model.result("pg8").feature("arws4").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws4").setIndex("looplevel", 31, 0);
    model.result("pg8").feature("arws4").set("colindex", 1);
    model.result("pg8").run();
    model.result("pg8").create("tlan1", "TableAnnotation");
    model.result("pg8").feature("tlan1").set("arraydim", "2");
    model.result("pg8").feature("tlan1").set("source", "localtable");
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "0.2e4", 0, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", 100, 0, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u65f6\u95f4 = 2 \u5e74", 0, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "0.85e4", 1, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", 100, 1, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u65f6\u95f4 = 5 \u5e74", 1, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "0.2e4", 2, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", -450, 2, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u65f6\u95f4 = 8 \u5e74", 2, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "0.85e4", 3, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", -450, 3, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u65f6\u95f4 = 10 \u5e74", 3, 2);
    model.result("pg8").feature("tlan1").set("anchorpoint", "uppermiddle");
    model.result("pg8").feature("tlan1").set("showpoint", false);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8")
         .set("title", "\u8868\u9762\uff1a\u6c34\u5934 (m) \u7b49\u503c\u7ebf\uff1a\u4f4d\u79fb\u5927\u5c0f (m) \u9762\u4e0a\u7bad\u5934\uff1a\u6c34\u5934\u68af\u5ea6 (1)");
    model.result("pg8").set("paramindicator", "");
    model.result("pg8").run();

    model.title("Biot \u591a\u5b54\u5f39\u6027");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u5730\u4e0b\u6c34\u6d41\u6a21\u5757\u201d\u4e2d\u7684\u201c\u591a\u5b54\u5f39\u6027\u201d\u63a5\u53e3\uff0c\u8ba1\u7b97\u968f\u62bd\u6c34\u8fc7\u7a0b\u4ea7\u751f\u7684\u591a\u5b54\u4ecb\u8d28\u7684\u53d8\u5f62\u3002\u6b64\u6a21\u578b\u57fa\u4e8e\u201cTerzaghi \u56fa\u7ed3\u201d\u793a\u4f8b\u6a21\u578b\uff0c\u6bd4\u8f83\u8fd9\u4e24\u4e2a\u6a21\u578b\u7684\u5206\u6790\u7ed3\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("biot_poroelasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
