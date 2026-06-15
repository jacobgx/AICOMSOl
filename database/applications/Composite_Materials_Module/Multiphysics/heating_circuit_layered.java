/*
 * heating_circuit_layered.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:33 by COMSOL 6.3.0.290. */
public class heating_circuit_layered {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");
    model.component("comp1").physics("lshell").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("htlsh", "HeatTransferInShellsLM", "geom1");
    model.component("comp1").physics("htlsh").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("ecis", "ElectricCurrentsShell", "geom1");
    model.component("comp1").physics("ecis").prop("DefaultDescription")
         .set("DefaultDescription", "Electric_currents_in_layered_shells");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth_mat", "from_mat");

    model.component("comp1").multiphysics().create("tel1", "ThermalExpansionLS", 2);
    model.component("comp1").multiphysics("tel1").set("Heat_physics", "htlsh");
    model.component("comp1").multiphysics("tel1").set("Lshell_physics", "lshell");
    model.component("comp1").multiphysics("tel1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/htlsh", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ecis", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/tel1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("V_in", "12[V]", "\u8f93\u5165\u7535\u538b");
    model.param().set("d_glass", "2[mm]", "\u73bb\u7483\u539a\u5ea6");
    model.param().set("d_layer", "10[um]", "\u5c42\u539a\u5ea6");
    model.param().set("sigma_silver", "6.3e7[S/m]", "\u94f6\u7684\u7535\u5bfc\u7387");
    model.param().set("sigma_nichrome", "9.3e5[S/m]", "\u954d\u94ec\u5408\u91d1\u7684\u7535\u5bfc\u7387");
    model.param().set("T_air", "20[degC]", "\u7a7a\u6c14\u6e29\u5ea6");
    model.param().set("h_air", "5[W/(m^2*K)]", "\u4f20\u70ed\u819c\u7cfb\u6570\uff0c\u7a7a\u6c14");
    model.param().set("T_fluid", "353[K]", "\u6d41\u4f53\u6e29\u5ea6");
    model.param().set("h_fluid", "20[W/(m^2*K)]", "\u4f20\u70ed\u819c\u7cfb\u6570\uff0c\u6d41\u4f53");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("pos", new int[]{7, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("sq2", "sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("pos", new int[]{30, 8});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "file");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("filename", "heating_circuit_layered_polygon.txt");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("pol1", 2, 3, 4, 5, 6, 7, 8, 23, 24, 25, 26, 27, 28, 29, 34, 36, 37, 41, 42);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("pointinsketch")
         .set("fil1", 6, 7, 8, 9, 10, 11, 12, 26, 27, 28, 29, 30, 31, 37, 40, 43, 46, 49, 50);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{80, 130});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("fin");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat1").label("Silica glass");
    model.material("mat1").set("family", "custom");
    model.material("mat1").set("diffuse", "custom");
    model.material("mat1").set("ambient", "custom");
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.99);
    model.material("mat1").set("roughness", 0.02);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.material("mat1").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.17");
    model.material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.material("mat1").label("\u77f3\u82f1\u73bb\u7483");
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("\u94f6\u5c42");
    model.material().create("mat3", "Common", "");
    model.material("mat3").label("\u954d\u94ec\u5408\u91d1\u5c42");
    model.component("comp1").material().create("stlmat1", "LayeredMaterialStack");
    model.component("comp1").material("stlmat1").feature().remove("stllmat1");
    model.component("comp1").material("stlmat1").set("middlePlane", "bottom");
    model.component("comp1").material("stlmat1").feature().create("lmat1", "LayeredMaterial", "comp1");
    model.component("comp1").material("stlmat1").feature("lmat1").label("\u73bb\u7483");
    model.component("comp1").material("stlmat1").feature("lmat1").setIndex("thickness", "d_glass", 0);
    model.component("comp1").material("stlmat1").feature("lmat1").setIndex("meshPoints", 3, 0);
    model.component("comp1").material("stlmat1").feature().create("lmat2", "LayeredMaterial", "comp1");
    model.component("comp1").material("stlmat1").feature("lmat2").label("\u94f6");
    model.component("comp1").material("stlmat1").feature("lmat2").selection().set(2, 4);
    model.component("comp1").material("stlmat1").feature("lmat2").setIndex("link", "mat2", 0);
    model.component("comp1").material("stlmat1").feature("lmat2").setIndex("thickness", "d_layer", 0);
    model.component("comp1").material("stlmat1").feature("lmat2").setIndex("meshPoints", 1, 0);
    model.component("comp1").material("stlmat1").feature().create("lmat3", "LayeredMaterial", "comp1");
    model.component("comp1").material("stlmat1").feature("lmat3").label("\u954d\u94ec\u5408\u91d1");
    model.component("comp1").material("stlmat1").feature("lmat3").selection().set(3);
    model.component("comp1").material("stlmat1").feature("lmat3").setIndex("link", "mat3", 0);
    model.component("comp1").material("stlmat1").feature("lmat3").setIndex("thickness", "d_layer", 0);
    model.component("comp1").material("stlmat1").feature("lmat3").setIndex("meshPoints", 1, 0);

    model.view().create("view7", 2);
    model.view("view7").set("showgrid", false);
    model.view("view7").axis().set("viewscaletype", "manual");
    model.view("view7").axis().set("yscale", 99.50248756218906);

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("view", "view7");
    model.result("pg1").set("ispendingzoom", true);
    model.result("pg1").set("edges", false);
    model.result("pg1").label("\u5c42\u6a2a\u622a\u9762\u9884\u89c8");
    model.result("pg1").create("arrow1", "ArrowData");
    model.result("pg1").feature("arrow1").set("pointdata", new double[][]{{-0.02}, {0}});
    model.result("pg1").feature("arrow1").set("vectordata", new double[][]{{0}, {5.025E-4}});
    model.result("pg1").feature("arrow1").set("coloring", "uniform");
    model.result("pg1").feature("arrow1").set("color", "custom");
    model.result("pg1").feature("arrow1").set("customcolor", new double[]{1, 0, 0});
    model.result("pg1").feature("arrow1").set("autoscale", 0.01005);
    model.result("pg1").create("surf1", "SurfaceData");
    model.result("pg1").feature("surf1")
         .set("pointdata", new double[][]{{0, 0.30666666666666664, 0, 0.30666666666666664}, {0, 0, 0.002, 0.002}});
    model.result("pg1").feature("surf1").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf1").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", 1);
    model.result("pg1").feature("surf1").set("rangecolormax", 3);
    model.result("pg1").feature("surf1").set("rangedataactive", true);
    model.result("pg1").feature("surf1").set("rangedatamin", 1);
    model.result("pg1").feature("surf1").set("rangedatamax", 3);
    model.result("pg1").feature("surf1").set("coloring", "colortable");
    model.result("pg1").feature("surf1").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf1").set("colortablerev", true);
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").create("line1", "LineData");
    model.result("pg1").feature("line1")
         .set("pointdata", new double[][]{{0, 0.01, 0.30666666666666664, 0.31666666666666665, 0, 0.01, 0.30666666666666664, 0.31666666666666665}, {0, 0, 0, 0, 0.002, 0.002, 0.002, 0.002}});
    model.result("pg1").feature("line1").set("elementdata", new int[][]{{0, 4, 4, 6}, {2, 6, 0, 2}});
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "custom");
    model.result("pg1").feature("line1").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").create("tann_uppermiddle", "TableAnnotation");
    model.result("pg1").feature("tann_uppermiddle").set("source", "localtable");
    model.result("pg1").feature("tann_uppermiddle").set("showpoint", false);
    model.result("pg1").feature("tann_uppermiddle").set("showframe", false);
    model.result("pg1").feature("tann_uppermiddle").set("anchorpoint", "uppermiddle");
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.15833333333333333, 0, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -1.005E-4, 0, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c1\u201d:}}\\newline \\unicode{\u73bb\u7483}", 0, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf2", "SurfaceData");
    model.result("pg1").feature("surf2")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.6399999999999999, 0.3333333333333333, 0.6399999999999999}, {0, 0, 0.002, 0.002}});
    model.result("pg1").feature("surf2").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf2").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf2").set("rangecoloractive", true);
    model.result("pg1").feature("surf2").set("rangecolormin", 1);
    model.result("pg1").feature("surf2").set("rangecolormax", 3);
    model.result("pg1").feature("surf2").set("rangedataactive", true);
    model.result("pg1").feature("surf2").set("rangedatamin", 1);
    model.result("pg1").feature("surf2").set("rangedatamax", 3);
    model.result("pg1").feature("surf2").set("coloring", "colortable");
    model.result("pg1").feature("surf2").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf2").set("colortablerev", true);
    model.result("pg1").feature("surf2").set("colorlegend", false);
    model.result("pg1").create("surf3", "SurfaceData");
    model.result("pg1").feature("surf3")
         .set("pointdata", new double[][]{{0.3433333333333333, 0.6499999999999999, 0.3433333333333333, 0.6499999999999999}, {0.002, 0.002, 0.00201, 0.00201}});
    model.result("pg1").feature("surf3").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf3").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf3").set("rangecoloractive", true);
    model.result("pg1").feature("surf3").set("rangecolormin", 1);
    model.result("pg1").feature("surf3").set("rangecolormax", 3);
    model.result("pg1").feature("surf3").set("rangedataactive", true);
    model.result("pg1").feature("surf3").set("rangedatamin", 1);
    model.result("pg1").feature("surf3").set("rangedatamax", 3);
    model.result("pg1").feature("surf3").set("coloring", "colortable");
    model.result("pg1").feature("surf3").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf3").set("colortablerev", true);
    model.result("pg1").feature("surf3").set("colorlegend", false);
    model.result("pg1").create("line2", "LineData");
    model.result("pg1").feature("line2")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.3433333333333333, 0.6399999999999999, 0.6499999999999999, 0.3333333333333333, 0.3433333333333333, 0.6399999999999999, 0.6499999999999999, 0.3333333333333333, 0.3433333333333333, 0.6399999999999999, 0.6499999999999999}, {0, 0, 0, 0, 0.002, 0.002, 0.002, 0.002, 0.00201, 0.00201, 0.00201, 0.00201}});
    model.result("pg1").feature("line2")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 9, 9, 11}, {2, 7, 0, 2, 11, 5, 7}});
    model.result("pg1").feature("line2").set("coloring", "uniform");
    model.result("pg1").feature("line2").set("color", "custom");
    model.result("pg1").feature("line2").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.49166666666666664, 1, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -1.005E-4, 1, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c2\u201d:}}\\newline \\unicode{\u73bb\u7483}\\newline \\unicode{\u94f6}", 1, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf4", "SurfaceData");
    model.result("pg1").feature("surf4")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.9733333333333333, 0.6666666666666666, 0.9733333333333333}, {0, 0, 0.002, 0.002}});
    model.result("pg1").feature("surf4").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf4").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf4").set("rangecoloractive", true);
    model.result("pg1").feature("surf4").set("rangecolormin", 1);
    model.result("pg1").feature("surf4").set("rangecolormax", 3);
    model.result("pg1").feature("surf4").set("rangedataactive", true);
    model.result("pg1").feature("surf4").set("rangedatamin", 1);
    model.result("pg1").feature("surf4").set("rangedatamax", 3);
    model.result("pg1").feature("surf4").set("coloring", "colortable");
    model.result("pg1").feature("surf4").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf4").set("colortablerev", true);
    model.result("pg1").feature("surf4").set("colorlegend", false);
    model.result("pg1").create("surf5", "SurfaceData");
    model.result("pg1").feature("surf5")
         .set("pointdata", new double[][]{{0.6766666666666666, 0.9833333333333333, 0.6766666666666666, 0.9833333333333333}, {0.002, 0.002, 0.00201, 0.00201}});
    model.result("pg1").feature("surf5").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf5").set("colordata", new double[]{3, 3, 3, 3});
    model.result("pg1").feature("surf5").set("rangecoloractive", true);
    model.result("pg1").feature("surf5").set("rangecolormin", 1);
    model.result("pg1").feature("surf5").set("rangecolormax", 3);
    model.result("pg1").feature("surf5").set("rangedataactive", true);
    model.result("pg1").feature("surf5").set("rangedatamin", 1);
    model.result("pg1").feature("surf5").set("rangedatamax", 3);
    model.result("pg1").feature("surf5").set("coloring", "colortable");
    model.result("pg1").feature("surf5").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf5").set("colortablerev", true);
    model.result("pg1").feature("surf5").set("colorlegend", false);
    model.result("pg1").create("line3", "LineData");
    model.result("pg1").feature("line3")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.6766666666666666, 0.9733333333333333, 0.9833333333333333, 0.6666666666666666, 0.6766666666666666, 0.9733333333333333, 0.9833333333333333, 0.6666666666666666, 0.6766666666666666, 0.9733333333333333, 0.9833333333333333}, {0, 0, 0, 0, 0.002, 0.002, 0.002, 0.002, 0.00201, 0.00201, 0.00201, 0.00201}});
    model.result("pg1").feature("line3")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 9, 9, 11}, {2, 7, 0, 2, 11, 5, 7}});
    model.result("pg1").feature("line3").set("coloring", "uniform");
    model.result("pg1").feature("line3").set("color", "custom");
    model.result("pg1").feature("line3").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.825, 2, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -1.005E-4, 2, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c3\u201d:}}\\newline \\unicode{\u73bb\u7483}\\newline \\unicode{\u954d\u94ec\u5408\u91d1}", 2, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("line4", "LineData");
    model.result("pg1").feature("line4")
         .set("pointdata", new double[][]{{-0.01, 0.030133333333333324, -0.01, 0.030133333333333324, 0.040166666666666656, 0.08029999999999998, 0.040166666666666656, 0.08029999999999998, 0.09033333333333332, 0.13046666666666665, 0.09033333333333332, 0.13046666666666665, 0.14049999999999996, 0.18063333333333328, 0.14049999999999996, 0.18063333333333328, 0.19066666666666662, 0.23079999999999995, 0.19066666666666662, 0.23079999999999995, 0.2408333333333333, 0.2809666666666666, 0.2408333333333333, 0.2809666666666666, 0.2909999999999999, 0.3311333333333333, 0.2909999999999999, 0.3311333333333333, 0.3411666666666666, 0.3813, 0.3411666666666666, 0.3813, 0.39133333333333326, 0.43146666666666655, 0.39133333333333326, 0.43146666666666655, 0.4414999999999999, 0.48163333333333325, 0.4414999999999999, 0.48163333333333325, 0.4916666666666666, 0.5317999999999999, 0.4916666666666666, 0.5317999999999999, 0.5418333333333333, 0.5819666666666666, 0.5418333333333333, 0.5819666666666666, 0.5919999999999999, 0.6321333333333332, 0.5919999999999999, 0.6321333333333332, 0.6421666666666666, 0.6822999999999999, 0.6421666666666666, 0.6822999999999999, 0.6923333333333332, 0.7324666666666666, 0.6923333333333332, 0.7324666666666666, 0.7424999999999998, 0.7826333333333332, 0.7424999999999998, 0.7826333333333332, 0.7926666666666665, 0.8327999999999999, 0.7926666666666665, 0.8327999999999999, 0.8428333333333332, 0.8829666666666666, 0.8428333333333332, 0.8829666666666666, 0.8929999999999998, 0.9331333333333331, 0.8929999999999998, 0.9331333333333331, 0.9431666666666665, 0.9832999999999998, 0.9431666666666665, 0.9832999999999998}, {-1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5, -1.005E-5, -1.005E-5, 1.005E-5, 1.005E-5}});
    model.result("pg1").feature("line4")
         .set("elementdata", new int[][]{{0, 1, 3, 2, 4, 5, 7, 6, 8, 9, 11, 10, 12, 13, 15, 14, 16, 17, 19, 18, 20, 21, 23, 22, 24, 25, 27, 26, 28, 29, 31, 30, 32, 33, 35, 34, 36, 37, 39, 38, 40, 41, 43, 42, 44, 45, 47, 46, 48, 49, 51, 50, 52, 53, 55, 54, 56, 57, 59, 58, 60, 61, 63, 62, 64, 65, 67, 66, 68, 69, 71, 70, 72, 73, 75, 74, 76, 77, 79, 78}, {1, 3, 2, 0, 5, 7, 6, 4, 9, 11, 10, 8, 13, 15, 14, 12, 17, 19, 18, 16, 21, 23, 22, 20, 25, 27, 26, 24, 29, 31, 30, 28, 33, 35, 34, 32, 37, 39, 38, 36, 41, 43, 42, 40, 45, 47, 46, 44, 49, 51, 50, 48, 53, 55, 54, 52, 57, 59, 58, 56, 61, 63, 62, 60, 65, 67, 66, 64, 69, 71, 70, 68, 73, 75, 74, 72, 77, 79, 78, 76}});
    model.result("pg1").feature("line4").label("\u5c42\u4e2d\u9762");
    model.result("pg1").feature("line4").set("coloring", "uniform");
    model.result("pg1").feature("line4").set("color", "custom");
    model.result("pg1").feature("line4").set("customcolor", new double[]{1, 0, 0});
    model.result("pg1").set("ylabel", "\u539a\u5ea6\u5750\u6807");
    model.result("pg1").set("defaultaxisunits", new String[]{"", "m"});
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").physics("lshell").feature("lemm1").set("SolidModel", "Isotropic");
    model.component("comp1").physics("lshell").create("rms1", "RigidMotionSuppression", 2);
    model.component("comp1").physics("lshell").create("contls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist_src", "stlmat1_z1");
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist", "stlmat1_z2");
    model.component("comp1").physics("lshell").create("contls2", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls2").set("shelllist_src", "stlmat1_z1");
    model.component("comp1").physics("lshell").feature("contls2").set("shelllist", "stlmat1_z3");
    model.component("comp1").physics("lshell").create("contls3", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls3").set("shelllist_src", "stlmat1_z2");
    model.component("comp1").physics("lshell").feature("contls3").set("shelllist", "stlmat1_z3");
    model.component("comp1").physics("lshell").feature("contls3").setIndex("shelllist_lCheck", 1, 1, 0);
    model.component("comp1").physics("lshell").create("wrp1", "Warpage", 2);
    model.component("comp1").physics("lshell").feature("wrp1").selection().all();
    model.component("comp1").physics("lshell").feature("wrp1").set("applyTo", "bottom");
    model.component("comp1").physics("htlsh").feature("sls1").set("isLayerwiseConstant", false);
    model.component("comp1").physics("htlsh").create("hfi1", "HeatFluxInterface", 2);
    model.component("comp1").physics("htlsh").feature("hfi1").selection().all();
    model.component("comp1").physics("htlsh").feature("hfi1").set("applyTo", "top");
    model.component("comp1").physics("htlsh").feature("hfi1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("htlsh").feature("hfi1").set("h", "h_air");
    model.component("comp1").physics("htlsh").feature("hfi1").set("Text", "T_air");
    model.component("comp1").physics("htlsh").create("hfi2", "HeatFluxInterface", 2);
    model.component("comp1").physics("htlsh").feature("hfi2").selection().all();
    model.component("comp1").physics("htlsh").feature("hfi2").set("applyTo", "bottom");
    model.component("comp1").physics("htlsh").feature("hfi2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("htlsh").feature("hfi2").set("h", "h_fluid");
    model.component("comp1").physics("htlsh").feature("hfi2").set("Text", "T_fluid");
    model.component("comp1").physics("htlsh").create("contls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("htlsh").feature("contls1").set("shelllist_src", "stlmat1_z1");
    model.component("comp1").physics("htlsh").feature("contls1").set("shelllist", "stlmat1_z2");
    model.component("comp1").physics("htlsh").create("contls2", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("htlsh").feature("contls2").set("shelllist_src", "stlmat1_z1");
    model.component("comp1").physics("htlsh").feature("contls2").set("shelllist", "stlmat1_z3");
    model.component("comp1").physics("htlsh").create("contls3", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("htlsh").feature("contls3").set("shelllist_src", "stlmat1_z2");
    model.component("comp1").physics("htlsh").feature("contls3").set("shelllist", "stlmat1_z3");
    model.component("comp1").physics("htlsh").feature("contls3").setIndex("shelllist_lCheck", 1, 1, 0);
    model.component("comp1").physics("ecis").selection().set(2, 3, 4);
    model.component("comp1").physics("ecis").prop("LayerSelection").set("allLayers", false);
    model.component("comp1").physics("ecis").prop("LayerSelection").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("ecis").create("bgnd1", "BoundaryGround", 1);
    model.component("comp1").physics("ecis").feature("bgnd1").selection().set(38);
    model.component("comp1").physics("ecis").feature("bgnd1").set("shelllist", "stlmat1_z2");
    model.component("comp1").physics("ecis").create("bpot1", "BoundaryElectricPotential", 1);
    model.component("comp1").physics("ecis").feature("bpot1").selection().set(5);
    model.component("comp1").physics("ecis").feature("bpot1").set("shelllist", "stlmat1_z2");
    model.component("comp1").physics("ecis").feature("bpot1").set("V0", "V_in");
    model.component("comp1").physics("ecis").create("cls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist_src", "stlmat1_z2");
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist", "stlmat1_z3");
    model.component("comp1").physics("ecis").feature("cls1").setIndex("shelllist_lCheck", 1, 1, 0);

    model.component("comp1").multiphysics().create("ehls1", "ElectromagneticHeatingLayeredShell", 2);
    model.component("comp1").multiphysics("ehls1").set("bndType", "allShell");

    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat2").propertyGroup("Enu").set("E", new String[]{"83e9"});
    model.material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.37"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"10500"});
    model.material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"420"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"230"});
    model.material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"sigma_silver"});
    model.material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", new String[]{"18.9e-6"});
    model.material("mat3").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat3").propertyGroup("Enu").set("E", new String[]{"213e9"});
    model.material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.33"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"9000"});
    model.material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"15"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"20"});
    model.material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"sigma_nichrome"});
    model.material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", new String[]{"10e-6"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1lshelllshl", "LayeredMaterial");
    model.result().dataset("dset1lshelllshl").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1lshelllshl");
    model.result("pg2").label("\u5e94\u529b (lshell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"lshell.misesGp"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").label("Warpage_interface");
    model.result().dataset("lshl1").set("data", "none");
    model.result().dataset("lshl1").set("evaluatein", "interfaces");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection().set(1, 2, 3, 4);
    model.result().dataset().create("lshl2", "LayeredMaterial");
    model.result().dataset("lshl2").set("data", "dset1");
    model.result().dataset("lshl2").selection().geom("geom1", 2);
    model.result().dataset("lshl2").selection().set(1, 2, 3, 4);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6\uff0c\u58f3 (htlsh)");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("data", "lshl2");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "lshl2");
    model.result().dataset("dset1lshelllshl").set("data", "dset1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u52bf (ecis)");
    model.result("pg4").set("data", "dset1lshelllshl");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "V");
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "mm", "mm"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b5m", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b\uff0c\u88c5\u914d (lshell)");
    model.result("pg2").run();

    model.component("comp1").view("view1").set("scenelight", false);

    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").create("lss1", "LayeredMaterialSlice");
    model.result("pg5").feature("lss1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg5").feature("lss1").set("threshold", "manual");
    model.result("pg5").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("lss1").set("colortable", "Prism");
    model.result("pg5").feature("lss1").set("colortabletrans", "none");
    model.result("pg5").feature("lss1").set("colorscalemode", "linear");
    model.result("pg5").feature("lss1").set("locdef", "relative");
    model.result("pg5").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg5").feature("lss1").create("def", "Deform");
    model.result("pg5").feature("lss1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg5").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg5").run();
    model.result().move("pg5", 2);
    model.result("pg5").label("\u5e94\u529b\uff0c\u73bb\u7483 (lshell)");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5")
         .set("title", "\u591a\u5c42\u6750\u6599\u5207\u9762\uff1avon Mises \u5e94\u529b (MPa)\uff0c\u73bb\u7483");
    model.result("pg5").run();
    model.result("pg5").feature("lss1").set("locdef", "physical");
    model.result("pg5").feature("lss1").set("localzphys", "0 d_glass");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result().move("pg6", 3);
    model.result("pg6").label("\u5e94\u529b\uff0c\u5bfc\u7535\u5c42 (lshell)");
    model.result("pg6")
         .set("title", "\u591a\u5c42\u6750\u6599\u5207\u9762\uff1avon Mises \u5e94\u529b (MPa)\uff0c\u5bfc\u7535\u5c42");
    model.result("pg6").run();
    model.result("pg6").feature("lss1").set("localzphys", "d_glass+d_layer");
    model.result("pg6").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "ecis.Vc");
    model.result("pg4").run();
    model.result().dataset().create("dset1lshelllshlge", "LayeredMaterial");
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().dataset("dset1lshelllshlge").set("data", "dset1");
    model.result().dataset("dset1lshelllshlge").set("scale", "50*0.015264337522473748");
    model.result().dataset("dset1lshelllshlge")
         .set("defaultPlotIDs", new String[]{"shellGeometry|lshell", "plyAngle|lshell"});
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1lshelllshlge");
    model.result("pg7").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg7").feature("surf1").set("threshold", "manual");
    model.result("pg7").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg7").run();
    model.result().move("pg7", 4);
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result().move("pg8", 1);
    model.result("pg8").label("\u5806\u53e0\u533a");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "stlmat1.zone");
    model.result("pg8").feature("surf1").set("descr", "\u6676\u5e26\u6307\u6570");
    model.result("pg8").feature("surf1").set("coloring", "colortable");
    model.result("pg8").feature("surf1").set("colortable", "TrafficLight");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u8868\u9762\u635f\u8017");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "ecis.Qsh");
    model.result("pg9").feature("surf1").set("descr", "\u8868\u9762\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u754c\u9762\u5e94\u529b");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u591a\u5c42\u6750\u6599\u5207\u9762\uff1a\u754c\u9762\u5e94\u529b (MPa)");
    model.result("pg10").create("lss1", "LayeredMaterialSlice");
    model.result("pg10").feature("lss1").set("expr", "sqrt(lshell.sxz^2+lshell.syz^2)");
    model.result("pg10").feature("lss1").set("locdef", "physical");
    model.result("pg10").feature("lss1").set("localzphys", "d_glass");
    model.result("pg10").feature("lss1").create("sel1", "Selection");
    model.result("pg10").feature("lss1").feature("sel1").selection().set(2, 3, 4);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u7fd8\u66f2 (wrp1)");
    model.result("pg11").set("data", "lshl1");
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").label("\u7fd8\u66f2\u4f4d\u79fb");
    model.result("pg11").feature("surf1").set("expr", "lshell.wrp1.dispn_warp");
    model.result("pg11").feature("surf1").set("colortable", "WaveLight");
    model.result("pg11").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result("pg11").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("surf1").feature("sel1").selection().set(1, 2, 3, 4);
    model.result("pg11").feature("surf1").feature().create("def1", "Deform");
    model.result("pg11").feature("surf1").feature().create("mrkr1", "Marker");
    model.result("pg11").feature().create("surf2", "Surface");
    model.result("pg11").feature("surf2").label("\u5e73\u5747\u4f4d\u79fb");
    model.result("pg11").feature("surf2").set("expr", "lshell.wrp1.disp_avg");
    model.result("pg11").feature("surf2").set("coloring", "uniform");
    model.result("pg11").feature("surf2").set("color", "gray");
    model.result("pg11").feature("surf2").set("smooth", "internal");
    model.result("pg11").feature("surf2").set("inheritcolor", false);
    model.result("pg11").feature("surf2").set("inheritrange", false);
    model.result("pg11").feature("surf2").set("showsolutionparams", "on");
    model.result("pg11").feature("surf2").set("data", "parent");
    model.result("pg11").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg11").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("surf2").feature("sel1").selection().set(1, 2, 3, 4);
    model.result("pg11").feature("surf2").set("inheritplot", "surf1");
    model.result("pg11").feature("surf2").feature().create("def1", "Deform");
    model.result("pg11").feature("surf2").feature("def1")
         .set("expr", new String[]{"lshell.wrp1.u_avg", "lshell.wrp1.v_avg", "lshell.wrp1.w_avg"});
    model.result("pg11").label("\u7fd8\u66f2 (wrp1)");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().dataset().duplicate("dset1lshelllshl1", "dset1lshelllshl");
    model.result().dataset("dset1lshelllshl1").set("evaluatein", "interfaces");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u4ea7\u751f\u7684\u603b\u70ed\u91cf");
    model.result().evaluationGroup("eg1").set("data", "dset1lshelllshl1");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().set(2, 3, 4);
    model.result().evaluationGroup("eg1").feature("int1").set("locinput", "manual");
    model.result().evaluationGroup("eg1").feature("int1").set("locdef", "physical");
    model.result().evaluationGroup("eg1").feature("int1").set("expr", new String[]{"ecis.Qsh"});
    model.result().evaluationGroup("eg1").feature("int1")
         .set("descr", new String[]{"\u8868\u9762\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1"});
    model.result().evaluationGroup("eg1").feature("int1").set("unit", new String[]{"W"});
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u4f20\u9012\u81f3\u6d41\u4f53\u7684\u603b\u70ed\u91cf");
    model.result().evaluationGroup("eg2").set("data", "dset1lshelllshl1");
    model.result().evaluationGroup("eg2").create("int1", "IntSurface");
    model.result().evaluationGroup("eg2").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg2").feature("int1").selection().all();
    model.result().evaluationGroup("eg2").feature("int1").set("expr", new String[]{"htlsh.hfi2.q0"});
    model.result().evaluationGroup("eg2").feature("int1")
         .set("descr", new String[]{"\u8fb9\u754c\u5bf9\u6d41\u70ed\u901a\u91cf"});
    model.result().evaluationGroup("eg2").feature("int1").set("unit", new String[]{"W"});
    model.result().evaluationGroup("eg2").run();
    model.result("pg2").run();

    model.title("\u52a0\u70ed\u7535\u8def - \u591a\u5c42\u58f3\u7248\u672c");

    model
         .description("\u8fd9\u4e2a\u591a\u7269\u7406\u573a\u793a\u4f8b\u6a21\u62df\u52a0\u70ed\u7535\u8def\u8bbe\u5907\u7684\u7535\u70ed\u4ea7\u751f\u3001\u4f20\u70ed\u4ee5\u53ca\u673a\u68b0\u5e94\u529b\u548c\u53d8\u5f62\u3002\u8be5\u8bbe\u5907\u7531\u6c89\u79ef\u5728\u73bb\u7483\u677f\u4e0a\u7684\u7535\u963b\u5c42\u7ec4\u6210\u3002\u5f53\u5bf9\u7535\u8def\u65bd\u52a0\u7535\u538b\u65f6\uff0c\u8be5\u7535\u963b\u5c42\u4f1a\u4ea7\u751f\u7126\u8033\u70ed\uff0c\u4ece\u800c\u5f15\u53d1\u7ed3\u6784\u7684\u53d8\u5f62\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("heating_circuit_layered.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
