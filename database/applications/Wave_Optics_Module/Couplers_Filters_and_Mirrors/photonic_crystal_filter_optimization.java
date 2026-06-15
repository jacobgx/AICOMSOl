/*
 * photonic_crystal_filter_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:37 by COMSOL 6.3.0.290. */
public class photonic_crystal_filter_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Couplers_Filters_and_Mirrors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "photonic_crystal.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").label("GaAs (Gallium arsenide) (Skauli et al. 2003: n 0.97-17 um)");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("an1")
         .set("expr", "sqrt((5.372514)+(5.466742)*x^2/(x^2-(0.19636481728249))+(0.02429960)*x^2/(x^2-(0.76500440081209))+(1.957522)*x^2/(x^2-(1362.8353555600002)))");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("an1").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("an1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"an1(c_const/freq)", "0", "0", "0", "an1(c_const/freq)", "0", "0", "0", "an1(c_const/freq)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat1").selection()
         .set(1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7a7a\u6c14");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().all();
    model.component("comp1").physics("ewfd").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr2").selection().set(5);
    model.component("comp1").physics("ewfd").feature("sctr2").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd").feature("sctr2").set("E0i", new int[]{0, 0, 1});

    model.study("std1").feature("wave").set("plist", "1[um] 1.3[um]");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "0.75e-6", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "2.5e-6", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "0.75e-6", 1, 1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "cln1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").run();

    model.title("\u5149\u5b50\u6676\u4f53");

    model
         .description("\u901a\u8fc7\u79fb\u9664\u5149\u5b50\u6676\u4f53\u7ed3\u6784\u4e2d\u7684\u4e00\u4e9b\u6676\u67f1\uff0c\u53ef\u4ee5\u4ea7\u751f\u5149\u5b50\u6ce2\u5bfc\uff0c\u6839\u636e\u6676\u67f1\u95f4\u8ddd\u53ef\u4ee5\u5f97\u5230\u5149\u5b50\u5e26\u9699\u3002\u5728\u8fd9\u4e00\u5149\u5b50\u5e26\u9699\u5185\uff0c\u53ea\u6709\u7279\u5b9a\u9891\u7387\u8303\u56f4\u5185\u7684\u6ce2\u624d\u80fd\u901a\u8fc7\u672c\u4f8b\u4e2d\u7684\u6ce2\u5bfc\u51e0\u4f55\u4f20\u64ad\u3002");

    model.label("photonic_crystal.mph");

    model.result("pg2").run();

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").label("\u76ee\u6807\u51fd\u6570");
    model.component("comp1").probe("bnd1").set("probename", "obj");
    model.component("comp1").probe("bnd1").set("type", "integral");
    model.component("comp1").probe("bnd1").selection().set(45);
    model.component("comp1").probe("bnd1").set("expr", "ewfd.nPoav");
    model.component("comp1").probe("bnd1").set("descr", "\u529f\u7387\u6d41\u51fa\uff0c\u65f6\u95f4\u5e73\u5747");

    model.study("std1").feature("wave").set("probesel", "none");
    model.study("std1").label("\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u4f20\u8f93");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"obj"});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{"\u76ee\u6807\u51fd\u6570"});
    model.result().evaluationGroup("eg1").run();

    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u8981\u79fb\u52a8\u7684\u57df");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "0.2e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "3.2e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "0.2e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "3.2e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u53d8\u5f62\u57df");
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"boxsel1"});

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common("fsd1").selection().named("geom1_adjsel1");
    model.component("comp1").common().create("tsf1", "Transformation");
    model.component("comp1").common("tsf1").selection().named("geom1_boxsel1");
    model.component("comp1").common("tsf1").set("moveType", "Distance");
    model.component("comp1").common("tsf1").set("maximumDisplacement", "0.1[um]");
    model.component("comp1").common("tsf1").set("scalingType", "No_scaling");

    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("plotgroup", "Default");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", true);
    model.study("std1").feature("wave").setSolveFor("/frame/material1", false);
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 20);
    model.study("std2").feature("sho").set("movelimit", 0.2);
    model.study("std2").feature("sho").setIndex("optobj", "if(lambda0<1.15[um],-1,1)*log(comp1.obj[m/W])", 0);
    model.study("std2").feature("sho").set("objectivescaling", "init");
    model.study("std2").feature("sho").set("probesel", "none");
    model.study("std2").label("\u5f62\u72b6\u4f18\u5316");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (ewfd) 1");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1").label("\u5f62\u72b6\u4f18\u5316/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg4").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("frametype", "geometry");
    model.result("pg4").set("edgecolor", "gray");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "1");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "fromtheme");
    model.result("pg4").create("arwp1", "ArrowPoint");
    model.result("pg4").feature("arwp1").label("\u5e73\u79fb (\u53d8\u6362 1)");
    model.result("pg4").feature("arwp1").set("expr", new String[]{"tsf1.moveXg", "tsf1.moveYg"});
    model.result("pg4").feature("arwp1").set("arrowbase", "head");
    model.result("pg4").feature("arwp1").set("scaleactive", true);
    model.result("pg4").feature("arwp1").set("scale", "1");
    model.result("pg4").feature("arwp1").create("def1", "Deform");
    model.result("pg4").feature("arwp1").feature("def1")
         .set("expr", new String[]{"-tsf1.scaleXg-tsf1.rotateXg", "-tsf1.scaleYg-tsf1.rotateYg"});
    model.result("pg4").feature("arwp1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("arwp1").feature("def1").set("scale", "1");
    model.result("pg4").feature("arwp1").create("col1", "Color");
    model.result("pg4").feature("arwp1").feature("col1").set("expr", "tsf1.rel_move");
    model.result("pg4").feature("arwp1").feature("col1").set("rangecoloractive", "on");
    model.result("pg4").feature("arwp1").feature("col1").set("rangecolormin", 0);
    model.result("pg4").feature("arwp1").feature("col1").set("rangecolormax", 1);
    model.result("pg4").create("arwp2", "ArrowPoint");
    model.result("pg4").feature("arwp2").label("\u7f29\u653e (\u53d8\u6362 1)");
    model.result("pg4").feature("arwp2").set("expr", new String[]{"tsf1.scaleXg", "tsf1.scaleYg"});
    model.result("pg4").feature("arwp2").set("arrowbase", "head");
    model.result("pg4").feature("arwp2").set("scaleactive", true);
    model.result("pg4").feature("arwp2").set("scale", "1");
    model.result("pg4").feature("arwp2").set("inheritplot", "arwp1");
    model.result("pg4").feature("arwp2").create("def1", "Deform");
    model.result("pg4").feature("arwp2").feature("def1")
         .set("expr", new String[]{"-tsf1.rotateXg", "-tsf1.rotateYg"});
    model.result("pg4").feature("arwp2").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("arwp2").feature("def1").set("scale", "1");
    model.result("pg4").feature("arwp2").create("col1", "Color");
    model.result("pg4").feature("arwp2").feature("col1").set("expr", "tsf1.rel_scale");
    model.result("pg4").feature("arwp2").feature("col1").set("rangecoloractive", "on");
    model.result("pg4").feature("arwp2").feature("col1").set("rangecolormin", 0);
    model.result("pg4").feature("arwp2").feature("col1").set("rangecolormax", 1);
    model.result("pg4").create("arwp3", "ArrowPoint");
    model.result("pg4").feature("arwp3").label("\u65cb\u8f6c (\u53d8\u6362 1)");
    model.result("pg4").feature("arwp3").set("expr", new String[]{"tsf1.rotateXg", "tsf1.rotateYg"});
    model.result("pg4").feature("arwp3").set("arrowbase", "head");
    model.result("pg4").feature("arwp3").set("scaleactive", true);
    model.result("pg4").feature("arwp3").set("scale", "1");
    model.result("pg4").feature("arwp3").set("inheritplot", "arwp1");
    model.result("pg4").feature("arwp3").create("col1", "Color");
    model.result("pg4").feature("arwp3").feature("col1").set("expr", "tsf1.rel_rotate");
    model.result("pg4").feature("arwp3").feature("col1").set("rangecoloractive", "on");
    model.result("pg4").feature("arwp3").feature("col1").set("rangecolormin", 0);
    model.result("pg4").feature("arwp3").feature("col1").set("rangecolormax", 1);
    model.result("pg3").run();

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg4");

    model.sol("sol2").feature("o1").set("nojacmethod", false);
    model.sol("sol2").feature("o1").feature("s1").create("se1", "Segregated");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss1").label("\u7535\u573a");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_E", "comp1_tsf1_move"});
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_material_disp", "comp1_tsf1_move"});
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ssDef").label("\u4f18\u5316");

    model.study("std2").feature("wave").set("plist", "1[um] 1.3[um]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg3").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg3").feature("surf1").set("colortable", "WaveLight");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").stepPrevious(0);
    model.result("pg3").run();
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset2");
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u7f29\u7565\u56fe");
    model.result("pg5").set("edges", false);
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("radiusexpr", "3e-9");
    model.result("pg5").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "gray");
    model.result("pg5").feature("line1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("line1").feature("filt1").set("expr", "(5e-7<Xg)*(Yg<28e-7)*(7e-7<Yg)");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("line2", "line1");
    model.result("pg5").run();
    model.result("pg5").feature("line2").set("data", "dset2");
    model.result("pg5").feature("line2").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").set("data", "dset2");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"material.dX", "ewfd.Hy"});
    model.result("pg5").feature("arwl1").setIndex("expr", "material.dY", 1);
    model.result("pg5").feature("arwl1").set("arrowcount", "1e3");
    model.result("pg5").feature("arwl1").set("arrowbase", "head");
    model.result("pg5").feature("arwl1").set("scaleactive", true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("arwl1").feature().copy("filt1", "pg5/line1/filt1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("arwl1").create("col1", "Color");
    model.result("pg5").run();
    model.result("pg5").feature("arwl1").feature("col1").set("expr", "sqrt(material.dX^2+material.dY^2)");
    model.result("pg5").feature("arwl1").feature("col1").set("colorlegend", false);
    model.result("pg5").run();

    model.title("\u7528\u4e8e\u4fe1\u53f7\u6ee4\u6ce2\u7684\u5149\u5b50\u6676\u4f53\u7684\u4f18\u5316");

    model
         .description("\u672c\u6a21\u578b\u5efa\u7acb\u5728\u5149\u5b50\u6676\u4f53\u6a21\u578b\u7684\u57fa\u7840\u4e0a\uff0c\u5bf9\u5149\u5b50\u6676\u4f53\u7684\u7ed3\u6784\u8fdb\u884c\u7814\u7a76\u3002\u8fd9\u79cd\u7ed3\u6784\u5177\u6709\u5e26\u9699\uff0c\u56e0\u6b64\uff0c\u53ea\u6709\u7279\u5b9a\u9891\u7387\u8303\u56f4\u5185\u7684\u6ce2\u624d\u80fd\u901a\u8fc7\u8f6e\u5ed3\u6240\u793a\u7684\u6ce2\u5bfc\u51e0\u4f55\u8fdb\u884c\u4f20\u64ad\u3002\u8be5\u6a21\u578b\u901a\u8fc7\u6539\u53d8\u67f1\u7684\u4f4d\u7f6e\uff0c\u4ee5\u6700\u5927\u9650\u5ea6\u5730\u63d0\u9ad8\u6240\u9700\u9891\u7387\u4e0b\u7684\u4f20\u8f93\u4e0e\u5bc4\u751f\u9891\u7387\u4e0b\u7684\u4f20\u8f93\u6bd4\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("photonic_crystal_filter_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
