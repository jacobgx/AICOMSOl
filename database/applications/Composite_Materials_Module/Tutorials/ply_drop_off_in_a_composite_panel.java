/*
 * ply_drop_off_in_a_composite_panel.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:35 by COMSOL 6.3.0.290. */
public class ply_drop_off_in_a_composite_panel {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);

    model.param().set("a", "0.1[m]");
    model.param().descr("a", "Side length");
    model.param().set("th", "1[mm]");
    model.param().descr("th", "Lamina thickness");
    model.param().set("alpha", "3[deg]");
    model.param().descr("alpha", "Taper angle");
    model.param().set("lr", "th/tan(alpha)");
    model.param().descr("lr", "Resin pocket length");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup()
         .create("OrthotropicStrengthParameters", "OrthotropicStrengthParameters", "Orthotropic strength parameters, Voigt notation");
    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat1")
         .label("Unidirectional fiber lamina: AS4/APC2 carbon/PEEK thermoplastic [fiber volume fraction 58%]");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("density", "1570[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.2E-6[1/K]", "0", "0", "0", "24E-6[1/K]", "0", "0", "0", "24E-6[1/K]"});
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .label("Orthotropic strength parameters, Voigt notation");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmats", new String[]{"2060[MPa]", "78[MPa]", "78[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmats", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmacs", new String[]{"1590[MPa]", "200[MPa]", "200[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmacs", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmass", new String[]{"157[MPa]", "157[MPa]", "157[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmass", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"138[GPa]", "8.7[GPa]"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.28", "0.45"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", "5[GPa]");
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.material("mat2").label("Epoxy polymer");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").set("density", "1250[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"75E-6[1/K]", "0", "0", "0", "75E-6[1/K]", "0", "0", "0", "75E-6[1/K]"});
    model.material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat2").propertyGroup("def").set("heatcapacity", "796[J/(kg*K)]");
    model.material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").set("E", "3.25[GPa]");
    model.material("mat2").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.265");
    model.material("mat2").propertyGroup("Enu")
         .setPropertyInfo("nu", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat2").propertyGroup("IsotropicStrengthParameters").label("Isotropic strength parameters");
    model.material("mat2").propertyGroup("IsotropicStrengthParameters").set("sigmat", "87.5[MPa]");
    model.material("mat2").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("thickness", "th", 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("rotation", "90.0", 1);
    model.material("lmat1").label("\u94fa\u5c42 [0/90]");
    model.material().create("lmat2", "LayeredMaterial", "");
    model.material("lmat2").setIndex("rotation", "-30.0", 0);
    model.material("lmat2").setIndex("thickness", "th", 0);
    model.material("lmat2").label("\u94fa\u5c42 [-30]");
    model.material().duplicate("lmat3", "lmat2");
    model.material("lmat3").label("\u94fa\u5c42 [30]");
    model.material("lmat3").setIndex("rotation", "30.0", 0);
    model.material().duplicate("lmat4", "lmat3");
    model.material("lmat4").label("\u94fa\u5c42 [-60]");
    model.material("lmat4").setIndex("rotation", "-60.0", 0);
    model.material().duplicate("lmat5", "lmat4");
    model.material("lmat5").setIndex("rotation", "60.0", 0);
    model.material("lmat5").label("\u94fa\u5c42 [60]");
    model.material().duplicate("lmat6", "lmat1");
    model.material("lmat6").setIndex("rotation", "-45.0", 0);
    model.material("lmat6").setIndex("rotation", "45.0", 1);
    model.material("lmat6").label("\u94fa\u5c42 [-45/45]");
    model.material().duplicate("lmat7", "lmat5");
    model.material("lmat7").setIndex("link", "mat2", 0);
    model.material("lmat7").setIndex("rotation", "20.0", 0);
    model.material("lmat7").label("\u73af\u6c27\u6811\u8102");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "a");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"lr", "a"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"a", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"lr", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{4, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", "a");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2")
         .set("pos", new String[]{"a+4*lr", "0"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("stlmat1", "LayeredMaterialStack");
    model.component("comp1").material("stlmat1").feature("stllmat1").label("\u94fa\u5c42 [0/90]");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat2", "stllmat1");
    model.component("comp1").material("stlmat1").feature("stllmat2").set("link", "lmat2");
    model.component("comp1").material("stlmat1").feature("stllmat2").label("\u94fa\u5c42 [-30]");
    model.component("comp1").material("stlmat1").feature("stllmat2").selection().set(1);
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat3", "stllmat2");
    model.component("comp1").material("stlmat1").feature("stllmat3").set("link", "lmat7");
    model.component("comp1").material("stlmat1").feature("stllmat3").set("thicknessScale_check", true);
    model.component("comp1").material("stlmat1").feature("stllmat3").set("thicknessScale", "1.0-0.99*(X-a)/lr");
    model.component("comp1").material("stlmat1").feature("stllmat3").selection().set(2);
    model.component("comp1").material("stlmat1").feature("stllmat3").label("\u73af\u6c27\u6811\u8102-1");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat4", "stllmat2");
    model.component("comp1").material("stlmat1").feature("stllmat4").set("link", "lmat3");
    model.component("comp1").material("stlmat1").feature("stllmat4").selection().set(1, 2);
    model.component("comp1").material("stlmat1").feature("stllmat4").label("\u94fa\u5c42 [30]");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat5", "stllmat3");
    model.component("comp1").material("stlmat1").feature("stllmat5").set("thicknessScale", "1.0-0.99*(X-a-lr)/lr");
    model.component("comp1").material("stlmat1").feature("stllmat5").selection().set(3);
    model.component("comp1").material("stlmat1").feature("stllmat5").label("\u73af\u6c27\u6811\u8102-2");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat6", "stllmat4");
    model.component("comp1").material("stlmat1").feature("stllmat6").set("link", "lmat4");
    model.component("comp1").material("stlmat1").feature("stllmat6").selection().set(1, 2, 3);
    model.component("comp1").material("stlmat1").feature("stllmat6").label("\u94fa\u5c42 [-60]");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat7", "stllmat5");
    model.component("comp1").material("stlmat1").feature("stllmat7").selection().set(4);
    model.component("comp1").material("stlmat1").feature("stllmat7").set("thicknessScale", "1.0-0.99*(X-a-2*lr)/lr");
    model.component("comp1").material("stlmat1").feature("stllmat7").label("\u73af\u6c27\u6811\u8102-3");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat8", "stllmat6");
    model.component("comp1").material("stlmat1").feature("stllmat8").set("link", "lmat5");
    model.component("comp1").material("stlmat1").feature("stllmat8").selection().set(1, 2, 3, 4);
    model.component("comp1").material("stlmat1").feature("stllmat8").label("\u94fa\u5c42 [60]");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat9", "stllmat7");
    model.component("comp1").material("stlmat1").feature("stllmat9").selection().set(5);
    model.component("comp1").material("stlmat1").feature("stllmat9").set("thicknessScale", "1.0-0.99*(X-a-3*lr)/lr");
    model.component("comp1").material("stlmat1").feature("stllmat9").label("\u73af\u6c27\u6811\u8102-4");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat10", "stllmat1");
    model.component("comp1").material("stlmat1").feature("stllmat10").set("link", "lmat6");
    model.component("comp1").material("stlmat1").feature("stllmat10").label("\u94fa\u5c42 [-45/45]");
    model.component("comp1").material("stlmat1").set("transform", "symmetry");

    model.view().create("view10", 2);
    model.view("view10").set("showgrid", false);
    model.view("view10").axis().set("viewscaletype", "manual");
    model.view("view10").axis().set("yscale", 12.499999999999995);

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("view", "view10");
    model.result("pg1").set("ispendingzoom", true);
    model.result("pg1").set("edges", false);
    model.result("pg1").label("\u5c42\u6a2a\u622a\u9762\u9884\u89c8");
    model.result("pg1").create("arrow1", "ArrowData");
    model.result("pg1").feature("arrow1").set("pointdata", new double[][]{{-0.02}, {0}});
    model.result("pg1").feature("arrow1").set("vectordata", new double[][]{{0}, {0.004000000000000002}});
    model.result("pg1").feature("arrow1").set("coloring", "uniform");
    model.result("pg1").feature("arrow1").set("color", "custom");
    model.result("pg1").feature("arrow1").set("customcolor", new double[]{1, 0, 0});
    model.result("pg1").feature("arrow1").set("autoscale", 0.08000000000000003);
    model.result("pg1").create("surf1", "SurfaceData");
    model.result("pg1").feature("surf1")
         .set("pointdata", new double[][]{{0, 0.14833333333333332, 0, 0.14833333333333332}, {-0.008000000000000004, -0.008000000000000004, -0.007000000000000004, -0.007000000000000004}});
    model.result("pg1").feature("surf1").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf1").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", 1);
    model.result("pg1").feature("surf1").set("rangecolormax", 12);
    model.result("pg1").feature("surf1").set("rangedataactive", true);
    model.result("pg1").feature("surf1").set("rangedatamin", 1);
    model.result("pg1").feature("surf1").set("rangedatamax", 12);
    model.result("pg1").feature("surf1").set("coloring", "colortable");
    model.result("pg1").feature("surf1").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf1").set("colortablerev", true);
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").create("surf2", "SurfaceData");
    model.result("pg1").feature("surf2")
         .set("pointdata", new double[][]{{0, 0.14833333333333332, 0, 0.14833333333333332}, {-0.007000000000000004, -0.007000000000000004, -0.006000000000000004, -0.006000000000000004}});
    model.result("pg1").feature("surf2").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf2").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf2").set("rangecoloractive", true);
    model.result("pg1").feature("surf2").set("rangecolormin", 1);
    model.result("pg1").feature("surf2").set("rangecolormax", 12);
    model.result("pg1").feature("surf2").set("rangedataactive", true);
    model.result("pg1").feature("surf2").set("rangedatamin", 1);
    model.result("pg1").feature("surf2").set("rangedatamax", 12);
    model.result("pg1").feature("surf2").set("coloring", "colortable");
    model.result("pg1").feature("surf2").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf2").set("colortablerev", true);
    model.result("pg1").feature("surf2").set("colorlegend", false);
    model.result("pg1").create("surf3", "SurfaceData");
    model.result("pg1").feature("surf3")
         .set("pointdata", new double[][]{{0.01, 0.15833333333333333, 0.01, 0.15833333333333333}, {-0.006000000000000004, -0.006000000000000004, -0.005000000000000004, -0.005000000000000004}});
    model.result("pg1").feature("surf3").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf3").set("colordata", new double[]{3, 3, 3, 3});
    model.result("pg1").feature("surf3").set("rangecoloractive", true);
    model.result("pg1").feature("surf3").set("rangecolormin", 1);
    model.result("pg1").feature("surf3").set("rangecolormax", 12);
    model.result("pg1").feature("surf3").set("rangedataactive", true);
    model.result("pg1").feature("surf3").set("rangedatamin", 1);
    model.result("pg1").feature("surf3").set("rangedatamax", 12);
    model.result("pg1").feature("surf3").set("coloring", "colortable");
    model.result("pg1").feature("surf3").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf3").set("colortablerev", true);
    model.result("pg1").feature("surf3").set("colorlegend", false);
    model.result("pg1").create("surf4", "SurfaceData");
    model.result("pg1").feature("surf4")
         .set("pointdata", new double[][]{{0, 0.14833333333333332, 0, 0.14833333333333332}, {-0.005000000000000004, -0.005000000000000004, -0.0040000000000000036, -0.0040000000000000036}});
    model.result("pg1").feature("surf4").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf4").set("colordata", new double[]{5, 5, 5, 5});
    model.result("pg1").feature("surf4").set("rangecoloractive", true);
    model.result("pg1").feature("surf4").set("rangecolormin", 1);
    model.result("pg1").feature("surf4").set("rangecolormax", 12);
    model.result("pg1").feature("surf4").set("rangedataactive", true);
    model.result("pg1").feature("surf4").set("rangedatamin", 1);
    model.result("pg1").feature("surf4").set("rangedatamax", 12);
    model.result("pg1").feature("surf4").set("coloring", "colortable");
    model.result("pg1").feature("surf4").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf4").set("colortablerev", true);
    model.result("pg1").feature("surf4").set("colorlegend", false);
    model.result("pg1").create("surf5", "SurfaceData");
    model.result("pg1").feature("surf5")
         .set("pointdata", new double[][]{{0.01, 0.15833333333333333, 0.01, 0.15833333333333333}, {-0.0040000000000000036, -0.0040000000000000036, -0.0030000000000000035, -0.0030000000000000035}});
    model.result("pg1").feature("surf5").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf5").set("colordata", new double[]{7, 7, 7, 7});
    model.result("pg1").feature("surf5").set("rangecoloractive", true);
    model.result("pg1").feature("surf5").set("rangecolormin", 1);
    model.result("pg1").feature("surf5").set("rangecolormax", 12);
    model.result("pg1").feature("surf5").set("rangedataactive", true);
    model.result("pg1").feature("surf5").set("rangedatamin", 1);
    model.result("pg1").feature("surf5").set("rangedatamax", 12);
    model.result("pg1").feature("surf5").set("coloring", "colortable");
    model.result("pg1").feature("surf5").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf5").set("colortablerev", true);
    model.result("pg1").feature("surf5").set("colorlegend", false);
    model.result("pg1").create("surf6", "SurfaceData");
    model.result("pg1").feature("surf6")
         .set("pointdata", new double[][]{{0, 0.14833333333333332, 0, 0.14833333333333332}, {-0.0030000000000000035, -0.0030000000000000035, -0.0020000000000000035, -0.0020000000000000035}});
    model.result("pg1").feature("surf6").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf6").set("colordata", new double[]{9, 9, 9, 9});
    model.result("pg1").feature("surf6").set("rangecoloractive", true);
    model.result("pg1").feature("surf6").set("rangecolormin", 1);
    model.result("pg1").feature("surf6").set("rangecolormax", 12);
    model.result("pg1").feature("surf6").set("rangedataactive", true);
    model.result("pg1").feature("surf6").set("rangedatamin", 1);
    model.result("pg1").feature("surf6").set("rangedatamax", 12);
    model.result("pg1").feature("surf6").set("coloring", "colortable");
    model.result("pg1").feature("surf6").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf6").set("colortablerev", true);
    model.result("pg1").feature("surf6").set("colorlegend", false);
    model.result("pg1").create("surf7", "SurfaceData");
    model.result("pg1").feature("surf7")
         .set("pointdata", new double[][]{{0.01, 0.15833333333333333, 0.01, 0.15833333333333333}, {-0.0020000000000000035, -0.0020000000000000035, -0.0010000000000000035, -0.0010000000000000035}});
    model.result("pg1").feature("surf7").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf7").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf7").set("rangecoloractive", true);
    model.result("pg1").feature("surf7").set("rangecolormin", 1);
    model.result("pg1").feature("surf7").set("rangecolormax", 12);
    model.result("pg1").feature("surf7").set("rangedataactive", true);
    model.result("pg1").feature("surf7").set("rangedatamin", 1);
    model.result("pg1").feature("surf7").set("rangedatamax", 12);
    model.result("pg1").feature("surf7").set("coloring", "colortable");
    model.result("pg1").feature("surf7").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf7").set("colortablerev", true);
    model.result("pg1").feature("surf7").set("colorlegend", false);
    model.result("pg1").create("surf8", "SurfaceData");
    model.result("pg1").feature("surf8")
         .set("pointdata", new double[][]{{0.01, 0.15833333333333333, 0.01, 0.15833333333333333}, {-0.0010000000000000035, -0.0010000000000000035, -3.469446951953614E-18, -3.469446951953614E-18}});
    model.result("pg1").feature("surf8").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf8").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf8").set("rangecoloractive", true);
    model.result("pg1").feature("surf8").set("rangecolormin", 1);
    model.result("pg1").feature("surf8").set("rangecolormax", 12);
    model.result("pg1").feature("surf8").set("rangedataactive", true);
    model.result("pg1").feature("surf8").set("rangedatamin", 1);
    model.result("pg1").feature("surf8").set("rangedatamax", 12);
    model.result("pg1").feature("surf8").set("coloring", "colortable");
    model.result("pg1").feature("surf8").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf8").set("colortablerev", true);
    model.result("pg1").feature("surf8").set("colorlegend", false);
    model.result("pg1").create("surf9", "SurfaceData");
    model.result("pg1").feature("surf9")
         .set("pointdata", new double[][]{{0.01, 0.15833333333333333, 0.01, 0.15833333333333333}, {-3.469446951953614E-18, -3.469446951953614E-18, 9.999999999999966E-4, 9.999999999999966E-4}});
    model.result("pg1").feature("surf9").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf9").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf9").set("rangecoloractive", true);
    model.result("pg1").feature("surf9").set("rangecolormin", 1);
    model.result("pg1").feature("surf9").set("rangecolormax", 12);
    model.result("pg1").feature("surf9").set("rangedataactive", true);
    model.result("pg1").feature("surf9").set("rangedatamin", 1);
    model.result("pg1").feature("surf9").set("rangedatamax", 12);
    model.result("pg1").feature("surf9").set("coloring", "colortable");
    model.result("pg1").feature("surf9").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf9").set("colortablerev", true);
    model.result("pg1").feature("surf9").set("colorlegend", false);
    model.result("pg1").create("surf10", "SurfaceData");
    model.result("pg1").feature("surf10")
         .set("pointdata", new double[][]{{0.01, 0.15833333333333333, 0.01, 0.15833333333333333}, {9.999999999999966E-4, 9.999999999999966E-4, 0.0019999999999999966, 0.0019999999999999966}});
    model.result("pg1").feature("surf10").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf10").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf10").set("rangecoloractive", true);
    model.result("pg1").feature("surf10").set("rangecolormin", 1);
    model.result("pg1").feature("surf10").set("rangecolormax", 12);
    model.result("pg1").feature("surf10").set("rangedataactive", true);
    model.result("pg1").feature("surf10").set("rangedatamin", 1);
    model.result("pg1").feature("surf10").set("rangedatamax", 12);
    model.result("pg1").feature("surf10").set("coloring", "colortable");
    model.result("pg1").feature("surf10").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf10").set("colortablerev", true);
    model.result("pg1").feature("surf10").set("colorlegend", false);
    model.result("pg1").create("surf11", "SurfaceData");
    model.result("pg1").feature("surf11")
         .set("pointdata", new double[][]{{0, 0.14833333333333332, 0, 0.14833333333333332}, {0.0019999999999999966, 0.0019999999999999966, 0.0029999999999999966, 0.0029999999999999966}});
    model.result("pg1").feature("surf11").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf11").set("colordata", new double[]{9, 9, 9, 9});
    model.result("pg1").feature("surf11").set("rangecoloractive", true);
    model.result("pg1").feature("surf11").set("rangecolormin", 1);
    model.result("pg1").feature("surf11").set("rangecolormax", 12);
    model.result("pg1").feature("surf11").set("rangedataactive", true);
    model.result("pg1").feature("surf11").set("rangedatamin", 1);
    model.result("pg1").feature("surf11").set("rangedatamax", 12);
    model.result("pg1").feature("surf11").set("coloring", "colortable");
    model.result("pg1").feature("surf11").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf11").set("colortablerev", true);
    model.result("pg1").feature("surf11").set("colorlegend", false);
    model.result("pg1").create("surf12", "SurfaceData");
    model.result("pg1").feature("surf12")
         .set("pointdata", new double[][]{{0.01, 0.15833333333333333, 0.01, 0.15833333333333333}, {0.0029999999999999966, 0.0029999999999999966, 0.003999999999999997, 0.003999999999999997}});
    model.result("pg1").feature("surf12").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf12").set("colordata", new double[]{7, 7, 7, 7});
    model.result("pg1").feature("surf12").set("rangecoloractive", true);
    model.result("pg1").feature("surf12").set("rangecolormin", 1);
    model.result("pg1").feature("surf12").set("rangecolormax", 12);
    model.result("pg1").feature("surf12").set("rangedataactive", true);
    model.result("pg1").feature("surf12").set("rangedatamin", 1);
    model.result("pg1").feature("surf12").set("rangedatamax", 12);
    model.result("pg1").feature("surf12").set("coloring", "colortable");
    model.result("pg1").feature("surf12").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf12").set("colortablerev", true);
    model.result("pg1").feature("surf12").set("colorlegend", false);
    model.result("pg1").create("surf13", "SurfaceData");
    model.result("pg1").feature("surf13")
         .set("pointdata", new double[][]{{0, 0.14833333333333332, 0, 0.14833333333333332}, {0.003999999999999997, 0.003999999999999997, 0.004999999999999997, 0.004999999999999997}});
    model.result("pg1").feature("surf13").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf13").set("colordata", new double[]{5, 5, 5, 5});
    model.result("pg1").feature("surf13").set("rangecoloractive", true);
    model.result("pg1").feature("surf13").set("rangecolormin", 1);
    model.result("pg1").feature("surf13").set("rangecolormax", 12);
    model.result("pg1").feature("surf13").set("rangedataactive", true);
    model.result("pg1").feature("surf13").set("rangedatamin", 1);
    model.result("pg1").feature("surf13").set("rangedatamax", 12);
    model.result("pg1").feature("surf13").set("coloring", "colortable");
    model.result("pg1").feature("surf13").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf13").set("colortablerev", true);
    model.result("pg1").feature("surf13").set("colorlegend", false);
    model.result("pg1").create("surf14", "SurfaceData");
    model.result("pg1").feature("surf14")
         .set("pointdata", new double[][]{{0.01, 0.15833333333333333, 0.01, 0.15833333333333333}, {0.004999999999999997, 0.004999999999999997, 0.005999999999999997, 0.005999999999999997}});
    model.result("pg1").feature("surf14").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf14").set("colordata", new double[]{3, 3, 3, 3});
    model.result("pg1").feature("surf14").set("rangecoloractive", true);
    model.result("pg1").feature("surf14").set("rangecolormin", 1);
    model.result("pg1").feature("surf14").set("rangecolormax", 12);
    model.result("pg1").feature("surf14").set("rangedataactive", true);
    model.result("pg1").feature("surf14").set("rangedatamin", 1);
    model.result("pg1").feature("surf14").set("rangedatamax", 12);
    model.result("pg1").feature("surf14").set("coloring", "colortable");
    model.result("pg1").feature("surf14").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf14").set("colortablerev", true);
    model.result("pg1").feature("surf14").set("colorlegend", false);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").create("surf15", "SurfaceData");
    model.result("pg1").feature("surf15")
         .set("pointdata", new double[][]{{0, 0.14833333333333332, 0, 0.14833333333333332}, {0.005999999999999997, 0.005999999999999997, 0.006999999999999997, 0.006999999999999997}});
    model.result("pg1").feature("surf15").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf15").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf15").set("rangecoloractive", true);
    model.result("pg1").feature("surf15").set("rangecolormin", 1);
    model.result("pg1").feature("surf15").set("rangecolormax", 12);
    model.result("pg1").feature("surf15").set("rangedataactive", true);
    model.result("pg1").feature("surf15").set("rangedatamin", 1);
    model.result("pg1").feature("surf15").set("rangedatamax", 12);
    model.result("pg1").feature("surf15").set("coloring", "colortable");
    model.result("pg1").feature("surf15").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf15").set("colortablerev", true);
    model.result("pg1").feature("surf15").set("colorlegend", false);
    model.result("pg1").create("surf16", "SurfaceData");
    model.result("pg1").feature("surf16")
         .set("pointdata", new double[][]{{0, 0.14833333333333332, 0, 0.14833333333333332}, {0.006999999999999997, 0.006999999999999997, 0.007999999999999997, 0.007999999999999997}});
    model.result("pg1").feature("surf16").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf16").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf16").set("rangecoloractive", true);
    model.result("pg1").feature("surf16").set("rangecolormin", 1);
    model.result("pg1").feature("surf16").set("rangecolormax", 12);
    model.result("pg1").feature("surf16").set("rangedataactive", true);
    model.result("pg1").feature("surf16").set("rangedatamin", 1);
    model.result("pg1").feature("surf16").set("rangedatamax", 12);
    model.result("pg1").feature("surf16").set("coloring", "colortable");
    model.result("pg1").feature("surf16").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf16").set("colortablerev", true);
    model.result("pg1").feature("surf16").set("colorlegend", false);
    model.result("pg1").create("line1", "LineData");
    model.result("pg1").feature("line1")
         .set("pointdata", new double[][]{{0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333, 0, 0.01, 0.14833333333333332, 0.15833333333333333}, {-0.008000000000000004, -0.008000000000000004, -0.008000000000000004, -0.008000000000000004, -0.007000000000000004, -0.007000000000000004, -0.007000000000000004, -0.007000000000000004, -0.006000000000000004, -0.006000000000000004, -0.006000000000000004, -0.006000000000000004, -0.005000000000000004, -0.005000000000000004, -0.005000000000000004, -0.005000000000000004, -0.0040000000000000036, -0.0040000000000000036, -0.0040000000000000036, -0.0040000000000000036, -0.0030000000000000035, -0.0030000000000000035, -0.0030000000000000035, -0.0030000000000000035, -0.0020000000000000035, -0.0020000000000000035, -0.0020000000000000035, -0.0020000000000000035, -0.0010000000000000035, -0.0010000000000000035, -0.0010000000000000035, -0.0010000000000000035, -3.469446951953614E-18, -3.469446951953614E-18, -3.469446951953614E-18, -3.469446951953614E-18, 9.999999999999966E-4, 9.999999999999966E-4, 9.999999999999966E-4, 9.999999999999966E-4, 0.0019999999999999966, 0.0019999999999999966, 0.0019999999999999966, 0.0019999999999999966, 0.0029999999999999966, 0.0029999999999999966, 0.0029999999999999966, 0.0029999999999999966, 0.003999999999999997, 0.003999999999999997, 0.003999999999999997, 0.003999999999999997, 0.004999999999999997, 0.004999999999999997, 0.004999999999999997, 0.004999999999999997, 0.005999999999999997, 0.005999999999999997, 0.005999999999999997, 0.005999999999999997, 0.006999999999999997, 0.006999999999999997, 0.006999999999999997, 0.006999999999999997, 0.007999999999999997, 0.007999999999999997, 0.007999999999999997, 0.007999999999999997}});
    model.result("pg1").feature("line1")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 8, 8, 10, 12, 13, 15, 16, 16, 18, 20, 21, 23, 24, 24, 26, 29, 29, 31, 33, 33, 35, 37, 37, 39, 40, 41, 43, 44, 44, 46, 48, 49, 51, 52, 52, 54, 56, 57, 59, 60, 60, 62, 64, 64, 66}, {2, 6, 0, 2, 11, 4, 6, 15, 9, 11, 19, 12, 14, 23, 17, 19, 27, 20, 22, 31, 25, 27, 35, 29, 31, 39, 33, 35, 43, 37, 39, 47, 40, 42, 51, 45, 47, 55, 48, 50, 59, 53, 55, 62, 56, 58, 66, 60, 62}});
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "custom");
    model.result("pg1").feature("line1").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").create("tann_uppermiddle", "TableAnnotation");
    model.result("pg1").feature("tann_uppermiddle").set("source", "localtable");
    model.result("pg1").feature("tann_uppermiddle").set("showpoint", false);
    model.result("pg1").feature("tann_uppermiddle").set("showframe", false);
    model.result("pg1").feature("tann_uppermiddle").set("anchorpoint", "uppermiddle");
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.07916666666666666, 0, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -0.008800000000000004, 0, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c1\u201d:}}\\newline \\unicode{\u94fa\u5c42 [0/90]}\\newline \\unicode{\u94fa\u5c42 [-30]}\\newline \\unicode{\u94fa\u5c42 [30]}\\newline \\unicode{\u94fa\u5c42 [-60]}\\newline \\unicode{\u94fa\u5c42 [60]}\\newline \\unicode{\u94fa\u5c42 [-45/45]}", 0, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf17", "SurfaceData");
    model.result("pg1").feature("surf17")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.31499999999999995, 0.16666666666666666, 0.31499999999999995}, {-0.008000000000000004, -0.008000000000000004, -0.007000000000000004, -0.007000000000000004}});
    model.result("pg1").feature("surf17").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf17").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf17").set("rangecoloractive", true);
    model.result("pg1").feature("surf17").set("rangecolormin", 1);
    model.result("pg1").feature("surf17").set("rangecolormax", 12);
    model.result("pg1").feature("surf17").set("rangedataactive", true);
    model.result("pg1").feature("surf17").set("rangedatamin", 1);
    model.result("pg1").feature("surf17").set("rangedatamax", 12);
    model.result("pg1").feature("surf17").set("coloring", "colortable");
    model.result("pg1").feature("surf17").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf17").set("colortablerev", true);
    model.result("pg1").feature("surf17").set("colorlegend", false);
    model.result("pg1").create("surf18", "SurfaceData");
    model.result("pg1").feature("surf18")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.31499999999999995, 0.16666666666666666, 0.31499999999999995}, {-0.007000000000000004, -0.007000000000000004, -0.006000000000000004, -0.006000000000000004}});
    model.result("pg1").feature("surf18").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf18").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf18").set("rangecoloractive", true);
    model.result("pg1").feature("surf18").set("rangecolormin", 1);
    model.result("pg1").feature("surf18").set("rangecolormax", 12);
    model.result("pg1").feature("surf18").set("rangedataactive", true);
    model.result("pg1").feature("surf18").set("rangedatamin", 1);
    model.result("pg1").feature("surf18").set("rangedatamax", 12);
    model.result("pg1").feature("surf18").set("coloring", "colortable");
    model.result("pg1").feature("surf18").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf18").set("colortablerev", true);
    model.result("pg1").feature("surf18").set("colorlegend", false);
    model.result("pg1").create("surf19", "SurfaceData");
    model.result("pg1").feature("surf19")
         .set("pointdata", new double[][]{{0.17666666666666667, 0.32499999999999996, 0.17666666666666667, 0.32499999999999996}, {-0.006000000000000004, -0.006000000000000004, -0.005000000000000004, -0.005000000000000004}});
    model.result("pg1").feature("surf19").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf19").set("colordata", new double[]{4, 4, 4, 4});
    model.result("pg1").feature("surf19").set("rangecoloractive", true);
    model.result("pg1").feature("surf19").set("rangecolormin", 1);
    model.result("pg1").feature("surf19").set("rangecolormax", 12);
    model.result("pg1").feature("surf19").set("rangedataactive", true);
    model.result("pg1").feature("surf19").set("rangedatamin", 1);
    model.result("pg1").feature("surf19").set("rangedatamax", 12);
    model.result("pg1").feature("surf19").set("coloring", "colortable");
    model.result("pg1").feature("surf19").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf19").set("colortablerev", true);
    model.result("pg1").feature("surf19").set("colorlegend", false);
    model.result("pg1").create("surf20", "SurfaceData");
    model.result("pg1").feature("surf20")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.31499999999999995, 0.16666666666666666, 0.31499999999999995}, {-0.005000000000000004, -0.005000000000000004, -0.0040000000000000036, -0.0040000000000000036}});
    model.result("pg1").feature("surf20").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf20").set("colordata", new double[]{5, 5, 5, 5});
    model.result("pg1").feature("surf20").set("rangecoloractive", true);
    model.result("pg1").feature("surf20").set("rangecolormin", 1);
    model.result("pg1").feature("surf20").set("rangecolormax", 12);
    model.result("pg1").feature("surf20").set("rangedataactive", true);
    model.result("pg1").feature("surf20").set("rangedatamin", 1);
    model.result("pg1").feature("surf20").set("rangedatamax", 12);
    model.result("pg1").feature("surf20").set("coloring", "colortable");
    model.result("pg1").feature("surf20").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf20").set("colortablerev", true);
    model.result("pg1").feature("surf20").set("colorlegend", false);
    model.result("pg1").create("surf21", "SurfaceData");
    model.result("pg1").feature("surf21")
         .set("pointdata", new double[][]{{0.17666666666666667, 0.32499999999999996, 0.17666666666666667, 0.32499999999999996}, {-0.0040000000000000036, -0.0040000000000000036, -0.0030000000000000035, -0.0030000000000000035}});
    model.result("pg1").feature("surf21").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf21").set("colordata", new double[]{7, 7, 7, 7});
    model.result("pg1").feature("surf21").set("rangecoloractive", true);
    model.result("pg1").feature("surf21").set("rangecolormin", 1);
    model.result("pg1").feature("surf21").set("rangecolormax", 12);
    model.result("pg1").feature("surf21").set("rangedataactive", true);
    model.result("pg1").feature("surf21").set("rangedatamin", 1);
    model.result("pg1").feature("surf21").set("rangedatamax", 12);
    model.result("pg1").feature("surf21").set("coloring", "colortable");
    model.result("pg1").feature("surf21").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf21").set("colortablerev", true);
    model.result("pg1").feature("surf21").set("colorlegend", false);
    model.result("pg1").create("surf22", "SurfaceData");
    model.result("pg1").feature("surf22")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.31499999999999995, 0.16666666666666666, 0.31499999999999995}, {-0.0030000000000000035, -0.0030000000000000035, -0.0020000000000000035, -0.0020000000000000035}});
    model.result("pg1").feature("surf22").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf22").set("colordata", new double[]{9, 9, 9, 9});
    model.result("pg1").feature("surf22").set("rangecoloractive", true);
    model.result("pg1").feature("surf22").set("rangecolormin", 1);
    model.result("pg1").feature("surf22").set("rangecolormax", 12);
    model.result("pg1").feature("surf22").set("rangedataactive", true);
    model.result("pg1").feature("surf22").set("rangedatamin", 1);
    model.result("pg1").feature("surf22").set("rangedatamax", 12);
    model.result("pg1").feature("surf22").set("coloring", "colortable");
    model.result("pg1").feature("surf22").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf22").set("colortablerev", true);
    model.result("pg1").feature("surf22").set("colorlegend", false);
    model.result("pg1").create("surf23", "SurfaceData");
    model.result("pg1").feature("surf23")
         .set("pointdata", new double[][]{{0.17666666666666667, 0.32499999999999996, 0.17666666666666667, 0.32499999999999996}, {-0.0020000000000000035, -0.0020000000000000035, -0.0010000000000000035, -0.0010000000000000035}});
    model.result("pg1").feature("surf23").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf23").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf23").set("rangecoloractive", true);
    model.result("pg1").feature("surf23").set("rangecolormin", 1);
    model.result("pg1").feature("surf23").set("rangecolormax", 12);
    model.result("pg1").feature("surf23").set("rangedataactive", true);
    model.result("pg1").feature("surf23").set("rangedatamin", 1);
    model.result("pg1").feature("surf23").set("rangedatamax", 12);
    model.result("pg1").feature("surf23").set("coloring", "colortable");
    model.result("pg1").feature("surf23").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf23").set("colortablerev", true);
    model.result("pg1").feature("surf23").set("colorlegend", false);
    model.result("pg1").create("surf24", "SurfaceData");
    model.result("pg1").feature("surf24")
         .set("pointdata", new double[][]{{0.17666666666666667, 0.32499999999999996, 0.17666666666666667, 0.32499999999999996}, {-0.0010000000000000035, -0.0010000000000000035, -3.469446951953614E-18, -3.469446951953614E-18}});
    model.result("pg1").feature("surf24").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf24").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf24").set("rangecoloractive", true);
    model.result("pg1").feature("surf24").set("rangecolormin", 1);
    model.result("pg1").feature("surf24").set("rangecolormax", 12);
    model.result("pg1").feature("surf24").set("rangedataactive", true);
    model.result("pg1").feature("surf24").set("rangedatamin", 1);
    model.result("pg1").feature("surf24").set("rangedatamax", 12);
    model.result("pg1").feature("surf24").set("coloring", "colortable");
    model.result("pg1").feature("surf24").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf24").set("colortablerev", true);
    model.result("pg1").feature("surf24").set("colorlegend", false);
    model.result("pg1").create("surf25", "SurfaceData");
    model.result("pg1").feature("surf25")
         .set("pointdata", new double[][]{{0.17666666666666667, 0.32499999999999996, 0.17666666666666667, 0.32499999999999996}, {-3.469446951953614E-18, -3.469446951953614E-18, 9.999999999999966E-4, 9.999999999999966E-4}});
    model.result("pg1").feature("surf25").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf25").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf25").set("rangecoloractive", true);
    model.result("pg1").feature("surf25").set("rangecolormin", 1);
    model.result("pg1").feature("surf25").set("rangecolormax", 12);
    model.result("pg1").feature("surf25").set("rangedataactive", true);
    model.result("pg1").feature("surf25").set("rangedatamin", 1);
    model.result("pg1").feature("surf25").set("rangedatamax", 12);
    model.result("pg1").feature("surf25").set("coloring", "colortable");
    model.result("pg1").feature("surf25").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf25").set("colortablerev", true);
    model.result("pg1").feature("surf25").set("colorlegend", false);
    model.result("pg1").create("surf26", "SurfaceData");
    model.result("pg1").feature("surf26")
         .set("pointdata", new double[][]{{0.17666666666666667, 0.32499999999999996, 0.17666666666666667, 0.32499999999999996}, {9.999999999999966E-4, 9.999999999999966E-4, 0.0019999999999999966, 0.0019999999999999966}});
    model.result("pg1").feature("surf26").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf26").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf26").set("rangecoloractive", true);
    model.result("pg1").feature("surf26").set("rangecolormin", 1);
    model.result("pg1").feature("surf26").set("rangecolormax", 12);
    model.result("pg1").feature("surf26").set("rangedataactive", true);
    model.result("pg1").feature("surf26").set("rangedatamin", 1);
    model.result("pg1").feature("surf26").set("rangedatamax", 12);
    model.result("pg1").feature("surf26").set("coloring", "colortable");
    model.result("pg1").feature("surf26").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf26").set("colortablerev", true);
    model.result("pg1").feature("surf26").set("colorlegend", false);
    model.result("pg1").create("surf27", "SurfaceData");
    model.result("pg1").feature("surf27")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.31499999999999995, 0.16666666666666666, 0.31499999999999995}, {0.0019999999999999966, 0.0019999999999999966, 0.0029999999999999966, 0.0029999999999999966}});
    model.result("pg1").feature("surf27").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf27").set("colordata", new double[]{9, 9, 9, 9});
    model.result("pg1").feature("surf27").set("rangecoloractive", true);
    model.result("pg1").feature("surf27").set("rangecolormin", 1);
    model.result("pg1").feature("surf27").set("rangecolormax", 12);
    model.result("pg1").feature("surf27").set("rangedataactive", true);
    model.result("pg1").feature("surf27").set("rangedatamin", 1);
    model.result("pg1").feature("surf27").set("rangedatamax", 12);
    model.result("pg1").feature("surf27").set("coloring", "colortable");
    model.result("pg1").feature("surf27").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf27").set("colortablerev", true);
    model.result("pg1").feature("surf27").set("colorlegend", false);
    model.result("pg1").create("surf28", "SurfaceData");
    model.result("pg1").feature("surf28")
         .set("pointdata", new double[][]{{0.17666666666666667, 0.32499999999999996, 0.17666666666666667, 0.32499999999999996}, {0.0029999999999999966, 0.0029999999999999966, 0.003999999999999997, 0.003999999999999997}});
    model.result("pg1").feature("surf28").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf28").set("colordata", new double[]{7, 7, 7, 7});
    model.result("pg1").feature("surf28").set("rangecoloractive", true);
    model.result("pg1").feature("surf28").set("rangecolormin", 1);
    model.result("pg1").feature("surf28").set("rangecolormax", 12);
    model.result("pg1").feature("surf28").set("rangedataactive", true);
    model.result("pg1").feature("surf28").set("rangedatamin", 1);
    model.result("pg1").feature("surf28").set("rangedatamax", 12);
    model.result("pg1").feature("surf28").set("coloring", "colortable");
    model.result("pg1").feature("surf28").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf28").set("colortablerev", true);
    model.result("pg1").feature("surf28").set("colorlegend", false);
    model.result("pg1").create("surf29", "SurfaceData");
    model.result("pg1").feature("surf29")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.31499999999999995, 0.16666666666666666, 0.31499999999999995}, {0.003999999999999997, 0.003999999999999997, 0.004999999999999997, 0.004999999999999997}});
    model.result("pg1").feature("surf29").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf29").set("colordata", new double[]{5, 5, 5, 5});
    model.result("pg1").feature("surf29").set("rangecoloractive", true);
    model.result("pg1").feature("surf29").set("rangecolormin", 1);
    model.result("pg1").feature("surf29").set("rangecolormax", 12);
    model.result("pg1").feature("surf29").set("rangedataactive", true);
    model.result("pg1").feature("surf29").set("rangedatamin", 1);
    model.result("pg1").feature("surf29").set("rangedatamax", 12);
    model.result("pg1").feature("surf29").set("coloring", "colortable");
    model.result("pg1").feature("surf29").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf29").set("colortablerev", true);
    model.result("pg1").feature("surf29").set("colorlegend", false);
    model.result("pg1").create("surf30", "SurfaceData");
    model.result("pg1").feature("surf30")
         .set("pointdata", new double[][]{{0.17666666666666667, 0.32499999999999996, 0.17666666666666667, 0.32499999999999996}, {0.004999999999999997, 0.004999999999999997, 0.005999999999999997, 0.005999999999999997}});
    model.result("pg1").feature("surf30").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf30").set("colordata", new double[]{4, 4, 4, 4});
    model.result("pg1").feature("surf30").set("rangecoloractive", true);
    model.result("pg1").feature("surf30").set("rangecolormin", 1);
    model.result("pg1").feature("surf30").set("rangecolormax", 12);
    model.result("pg1").feature("surf30").set("rangedataactive", true);
    model.result("pg1").feature("surf30").set("rangedatamin", 1);
    model.result("pg1").feature("surf30").set("rangedatamax", 12);
    model.result("pg1").feature("surf30").set("coloring", "colortable");
    model.result("pg1").feature("surf30").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf30").set("colortablerev", true);
    model.result("pg1").feature("surf30").set("colorlegend", false);
    model.result("pg1").create("surf31", "SurfaceData");
    model.result("pg1").feature("surf31")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.31499999999999995, 0.16666666666666666, 0.31499999999999995}, {0.005999999999999997, 0.005999999999999997, 0.006999999999999997, 0.006999999999999997}});
    model.result("pg1").feature("surf31").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf31").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf31").set("rangecoloractive", true);
    model.result("pg1").feature("surf31").set("rangecolormin", 1);
    model.result("pg1").feature("surf31").set("rangecolormax", 12);
    model.result("pg1").feature("surf31").set("rangedataactive", true);
    model.result("pg1").feature("surf31").set("rangedatamin", 1);
    model.result("pg1").feature("surf31").set("rangedatamax", 12);
    model.result("pg1").feature("surf31").set("coloring", "colortable");
    model.result("pg1").feature("surf31").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf31").set("colortablerev", true);
    model.result("pg1").feature("surf31").set("colorlegend", false);
    model.result("pg1").create("surf32", "SurfaceData");
    model.result("pg1").feature("surf32")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.31499999999999995, 0.16666666666666666, 0.31499999999999995}, {0.006999999999999997, 0.006999999999999997, 0.007999999999999997, 0.007999999999999997}});
    model.result("pg1").feature("surf32").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf32").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf32").set("rangecoloractive", true);
    model.result("pg1").feature("surf32").set("rangecolormin", 1);
    model.result("pg1").feature("surf32").set("rangecolormax", 12);
    model.result("pg1").feature("surf32").set("rangedataactive", true);
    model.result("pg1").feature("surf32").set("rangedatamin", 1);
    model.result("pg1").feature("surf32").set("rangedatamax", 12);
    model.result("pg1").feature("surf32").set("coloring", "colortable");
    model.result("pg1").feature("surf32").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf32").set("colortablerev", true);
    model.result("pg1").feature("surf32").set("colorlegend", false);
    model.result("pg1").create("line2", "LineData");
    model.result("pg1").feature("line2")
         .set("pointdata", new double[][]{{0.3245833333333333, 0.32499999999999996, 0.31458333333333327, 0.32499999999999996, 0.30458333333333326, 0.3170833333333333, 0.2945833333333333, 0.30708333333333326, 0.2845833333333333, 0.2970833333333333, 0.2745833333333333, 0.28708333333333325, 0.2645833333333333, 0.27708333333333324, 0.25458333333333333, 0.2670833333333333, 0.2445833333333333, 0.2570833333333333, 0.23458333333333328, 0.24708333333333327, 0.22458333333333333, 0.2370833333333333, 0.21458333333333332, 0.2270833333333333, 0.20458333333333328, 0.2170833333333333, 0.19458333333333327, 0.20708333333333329, 0.18458333333333332, 0.19708333333333333, 0.17666666666666667, 0.18708333333333332, 0.17666666666666667, 0.17708333333333331, 0.3245833333333333, 0.32499999999999996, 0.31458333333333327, 0.32499999999999996, 0.30458333333333326, 0.3170833333333333, 0.2945833333333333, 0.3070833333333333, 0.2845833333333333, 0.2970833333333333, 0.2745833333333333, 0.2870833333333333, 0.2645833333333333, 0.2770833333333333, 0.25458333333333333, 0.2670833333333333, 0.24458333333333332, 0.2570833333333333, 0.2345833333333333, 0.2470833333333333, 0.22458333333333333, 0.2370833333333333, 0.21458333333333335, 0.22708333333333333, 0.2045833333333333, 0.2170833333333333, 0.1945833333333333, 0.20708333333333329, 0.18458333333333332, 0.19708333333333333, 0.17666666666666667, 0.18708333333333332, 0.17666666666666667, 0.17708333333333331}, {-0.006000000000000003, -0.005966666666666669, -0.006000000000000004, -0.005166666666666669, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000004, -0.006000000000000004, -0.005000000000000003, -0.006000000000000004, -0.005000000000000004, -0.006000000000000003, -0.005000000000000004, -0.005833333333333336, -0.005000000000000004, -0.005033333333333335, -0.005000000000000004, 0.004999999999999997, 0.00503333333333333, 0.004999999999999997, 0.005833333333333331, 0.004999999999999997, 0.005999999999999996, 0.004999999999999997, 0.005999999999999997, 0.004999999999999996, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.004999999999999997, 0.005999999999999997, 0.005166666666666664, 0.005999999999999997, 0.0059666666666666635, 0.005999999999999996}});
    model.result("pg1").feature("line2")
         .set("elementdata", new int[][]{{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66}, {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67}});
    model.result("pg1").feature("line2").set("coloring", "uniform");
    model.result("pg1").feature("line2").set("color", "custom");
    model.result("pg1").feature("line2").set("customcolor", new double[]{0, 0, 1});
    model.result("pg1").create("line3", "LineData");
    model.result("pg1").feature("line3")
         .set("pointdata", new double[][]{{0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996, 0.16666666666666666, 0.17666666666666667, 0.31499999999999995, 0.32499999999999996}, {-0.008000000000000004, -0.008000000000000004, -0.008000000000000004, -0.008000000000000004, -0.007000000000000004, -0.007000000000000004, -0.007000000000000004, -0.007000000000000004, -0.006000000000000004, -0.006000000000000004, -0.006000000000000004, -0.006000000000000004, -0.005000000000000004, -0.005000000000000004, -0.005000000000000004, -0.005000000000000004, -0.0040000000000000036, -0.0040000000000000036, -0.0040000000000000036, -0.0040000000000000036, -0.0030000000000000035, -0.0030000000000000035, -0.0030000000000000035, -0.0030000000000000035, -0.0020000000000000035, -0.0020000000000000035, -0.0020000000000000035, -0.0020000000000000035, -0.0010000000000000035, -0.0010000000000000035, -0.0010000000000000035, -0.0010000000000000035, -3.469446951953614E-18, -3.469446951953614E-18, -3.469446951953614E-18, -3.469446951953614E-18, 9.999999999999966E-4, 9.999999999999966E-4, 9.999999999999966E-4, 9.999999999999966E-4, 0.0019999999999999966, 0.0019999999999999966, 0.0019999999999999966, 0.0019999999999999966, 0.0029999999999999966, 0.0029999999999999966, 0.0029999999999999966, 0.0029999999999999966, 0.003999999999999997, 0.003999999999999997, 0.003999999999999997, 0.003999999999999997, 0.004999999999999997, 0.004999999999999997, 0.004999999999999997, 0.004999999999999997, 0.005999999999999997, 0.005999999999999997, 0.005999999999999997, 0.005999999999999997, 0.006999999999999997, 0.006999999999999997, 0.006999999999999997, 0.006999999999999997, 0.007999999999999997, 0.007999999999999997, 0.007999999999999997, 0.007999999999999997}});

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg1").feature("line3")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 8, 8, 10, 12, 13, 15, 16, 16, 18, 20, 21, 23, 24, 24, 26, 29, 29, 31, 33, 33, 35, 37, 37, 39, 40, 41, 43, 44, 44, 46, 48, 49, 51, 52, 52, 54, 56, 57, 59, 60, 60, 62, 64, 64, 66}, {2, 6, 0, 2, 11, 4, 6, 15, 9, 11, 19, 12, 14, 23, 17, 19, 27, 20, 22, 31, 25, 27, 35, 29, 31, 39, 33, 35, 43, 37, 39, 47, 40, 42, 51, 45, 47, 55, 48, 50, 59, 53, 55, 62, 56, 58, 66, 60, 62}});
    model.result("pg1").feature("line3").set("coloring", "uniform");
    model.result("pg1").feature("line3").set("color", "custom");
    model.result("pg1").feature("line3").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.24583333333333332, 1, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -0.008800000000000004, 1, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c2\u201d:}}\\newline \\unicode{\u94fa\u5c42 [0/90]}\\newline \\unicode{\u73af\u6c27\u6811\u8102-1}\\newline \\unicode{\u94fa\u5c42 [30]}\\newline \\unicode{\u94fa\u5c42 [-60]}\\newline \\unicode{\u94fa\u5c42 [60]}\\newline \\unicode{\u94fa\u5c42 [-45/45]}", 1, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf33", "SurfaceData");
    model.result("pg1").feature("surf33")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {-0.007000000000000003, -0.007000000000000003, -0.006000000000000003, -0.006000000000000003}});
    model.result("pg1").feature("surf33").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf33").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf33").set("rangecoloractive", true);
    model.result("pg1").feature("surf33").set("rangecolormin", 1);
    model.result("pg1").feature("surf33").set("rangecolormax", 12);
    model.result("pg1").feature("surf33").set("rangedataactive", true);
    model.result("pg1").feature("surf33").set("rangedatamin", 1);
    model.result("pg1").feature("surf33").set("rangedatamax", 12);
    model.result("pg1").feature("surf33").set("coloring", "colortable");
    model.result("pg1").feature("surf33").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf33").set("colortablerev", true);
    model.result("pg1").feature("surf33").set("colorlegend", false);
    model.result("pg1").create("surf34", "SurfaceData");
    model.result("pg1").feature("surf34")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {-0.006000000000000003, -0.006000000000000003, -0.005000000000000003, -0.005000000000000003}});
    model.result("pg1").feature("surf34").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf34").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf34").set("rangecoloractive", true);
    model.result("pg1").feature("surf34").set("rangecolormin", 1);
    model.result("pg1").feature("surf34").set("rangecolormax", 12);
    model.result("pg1").feature("surf34").set("rangedataactive", true);
    model.result("pg1").feature("surf34").set("rangedatamin", 1);
    model.result("pg1").feature("surf34").set("rangedatamax", 12);
    model.result("pg1").feature("surf34").set("coloring", "colortable");
    model.result("pg1").feature("surf34").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf34").set("colortablerev", true);
    model.result("pg1").feature("surf34").set("colorlegend", false);
    model.result("pg1").create("surf35", "SurfaceData");
    model.result("pg1").feature("surf35")
         .set("pointdata", new double[][]{{0.3433333333333333, 0.49166666666666664, 0.3433333333333333, 0.49166666666666664}, {-0.005000000000000003, -0.005000000000000003, -0.004000000000000003, -0.004000000000000003}});
    model.result("pg1").feature("surf35").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf35").set("colordata", new double[]{6, 6, 6, 6});
    model.result("pg1").feature("surf35").set("rangecoloractive", true);
    model.result("pg1").feature("surf35").set("rangecolormin", 1);
    model.result("pg1").feature("surf35").set("rangecolormax", 12);
    model.result("pg1").feature("surf35").set("rangedataactive", true);
    model.result("pg1").feature("surf35").set("rangedatamin", 1);
    model.result("pg1").feature("surf35").set("rangedatamax", 12);
    model.result("pg1").feature("surf35").set("coloring", "colortable");
    model.result("pg1").feature("surf35").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf35").set("colortablerev", true);
    model.result("pg1").feature("surf35").set("colorlegend", false);
    model.result("pg1").create("surf36", "SurfaceData");
    model.result("pg1").feature("surf36")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {-0.004000000000000003, -0.004000000000000003, -0.0030000000000000027, -0.0030000000000000027}});
    model.result("pg1").feature("surf36").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf36").set("colordata", new double[]{7, 7, 7, 7});
    model.result("pg1").feature("surf36").set("rangecoloractive", true);
    model.result("pg1").feature("surf36").set("rangecolormin", 1);
    model.result("pg1").feature("surf36").set("rangecolormax", 12);
    model.result("pg1").feature("surf36").set("rangedataactive", true);
    model.result("pg1").feature("surf36").set("rangedatamin", 1);
    model.result("pg1").feature("surf36").set("rangedatamax", 12);
    model.result("pg1").feature("surf36").set("coloring", "colortable");
    model.result("pg1").feature("surf36").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf36").set("colortablerev", true);
    model.result("pg1").feature("surf36").set("colorlegend", false);
    model.result("pg1").create("surf37", "SurfaceData");
    model.result("pg1").feature("surf37")
         .set("pointdata", new double[][]{{0.3433333333333333, 0.49166666666666664, 0.3433333333333333, 0.49166666666666664}, {-0.0030000000000000027, -0.0030000000000000027, -0.0020000000000000026, -0.0020000000000000026}});
    model.result("pg1").feature("surf37").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf37").set("colordata", new double[]{9, 9, 9, 9});
    model.result("pg1").feature("surf37").set("rangecoloractive", true);
    model.result("pg1").feature("surf37").set("rangecolormin", 1);
    model.result("pg1").feature("surf37").set("rangecolormax", 12);
    model.result("pg1").feature("surf37").set("rangedataactive", true);
    model.result("pg1").feature("surf37").set("rangedatamin", 1);
    model.result("pg1").feature("surf37").set("rangedatamax", 12);
    model.result("pg1").feature("surf37").set("coloring", "colortable");
    model.result("pg1").feature("surf37").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf37").set("colortablerev", true);
    model.result("pg1").feature("surf37").set("colorlegend", false);
    model.result("pg1").create("surf38", "SurfaceData");
    model.result("pg1").feature("surf38")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {-0.0020000000000000026, -0.0020000000000000026, -0.0010000000000000026, -0.0010000000000000026}});
    model.result("pg1").feature("surf38").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf38").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf38").set("rangecoloractive", true);
    model.result("pg1").feature("surf38").set("rangecolormin", 1);
    model.result("pg1").feature("surf38").set("rangecolormax", 12);
    model.result("pg1").feature("surf38").set("rangedataactive", true);
    model.result("pg1").feature("surf38").set("rangedatamin", 1);
    model.result("pg1").feature("surf38").set("rangedatamax", 12);
    model.result("pg1").feature("surf38").set("coloring", "colortable");
    model.result("pg1").feature("surf38").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf38").set("colortablerev", true);
    model.result("pg1").feature("surf38").set("colorlegend", false);
    model.result("pg1").create("surf39", "SurfaceData");
    model.result("pg1").feature("surf39")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {-0.0010000000000000026, -0.0010000000000000026, -2.6020852139652106E-18, -2.6020852139652106E-18}});
    model.result("pg1").feature("surf39").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf39").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf39").set("rangecoloractive", true);
    model.result("pg1").feature("surf39").set("rangecolormin", 1);
    model.result("pg1").feature("surf39").set("rangecolormax", 12);
    model.result("pg1").feature("surf39").set("rangedataactive", true);
    model.result("pg1").feature("surf39").set("rangedatamin", 1);
    model.result("pg1").feature("surf39").set("rangedatamax", 12);
    model.result("pg1").feature("surf39").set("coloring", "colortable");
    model.result("pg1").feature("surf39").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf39").set("colortablerev", true);
    model.result("pg1").feature("surf39").set("colorlegend", false);
    model.result("pg1").create("surf40", "SurfaceData");
    model.result("pg1").feature("surf40")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {-2.6020852139652106E-18, -2.6020852139652106E-18, 9.999999999999974E-4, 9.999999999999974E-4}});
    model.result("pg1").feature("surf40").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf40").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf40").set("rangecoloractive", true);
    model.result("pg1").feature("surf40").set("rangecolormin", 1);
    model.result("pg1").feature("surf40").set("rangecolormax", 12);
    model.result("pg1").feature("surf40").set("rangedataactive", true);
    model.result("pg1").feature("surf40").set("rangedatamin", 1);
    model.result("pg1").feature("surf40").set("rangedatamax", 12);
    model.result("pg1").feature("surf40").set("coloring", "colortable");
    model.result("pg1").feature("surf40").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf40").set("colortablerev", true);
    model.result("pg1").feature("surf40").set("colorlegend", false);
    model.result("pg1").create("surf41", "SurfaceData");
    model.result("pg1").feature("surf41")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {9.999999999999974E-4, 9.999999999999974E-4, 0.0019999999999999974, 0.0019999999999999974}});
    model.result("pg1").feature("surf41").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf41").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf41").set("rangecoloractive", true);
    model.result("pg1").feature("surf41").set("rangecolormin", 1);
    model.result("pg1").feature("surf41").set("rangecolormax", 12);
    model.result("pg1").feature("surf41").set("rangedataactive", true);
    model.result("pg1").feature("surf41").set("rangedatamin", 1);
    model.result("pg1").feature("surf41").set("rangedatamax", 12);
    model.result("pg1").feature("surf41").set("coloring", "colortable");
    model.result("pg1").feature("surf41").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf41").set("colortablerev", true);
    model.result("pg1").feature("surf41").set("colorlegend", false);
    model.result("pg1").create("surf42", "SurfaceData");
    model.result("pg1").feature("surf42")
         .set("pointdata", new double[][]{{0.3433333333333333, 0.49166666666666664, 0.3433333333333333, 0.49166666666666664}, {0.0019999999999999974, 0.0019999999999999974, 0.0029999999999999975, 0.0029999999999999975}});
    model.result("pg1").feature("surf42").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf42").set("colordata", new double[]{9, 9, 9, 9});
    model.result("pg1").feature("surf42").set("rangecoloractive", true);
    model.result("pg1").feature("surf42").set("rangecolormin", 1);
    model.result("pg1").feature("surf42").set("rangecolormax", 12);
    model.result("pg1").feature("surf42").set("rangedataactive", true);
    model.result("pg1").feature("surf42").set("rangedatamin", 1);
    model.result("pg1").feature("surf42").set("rangedatamax", 12);
    model.result("pg1").feature("surf42").set("coloring", "colortable");
    model.result("pg1").feature("surf42").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf42").set("colortablerev", true);
    model.result("pg1").feature("surf42").set("colorlegend", false);
    model.result("pg1").create("surf43", "SurfaceData");
    model.result("pg1").feature("surf43")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {0.0029999999999999975, 0.0029999999999999975, 0.0039999999999999975, 0.0039999999999999975}});
    model.result("pg1").feature("surf43").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf43").set("colordata", new double[]{7, 7, 7, 7});
    model.result("pg1").feature("surf43").set("rangecoloractive", true);
    model.result("pg1").feature("surf43").set("rangecolormin", 1);
    model.result("pg1").feature("surf43").set("rangecolormax", 12);
    model.result("pg1").feature("surf43").set("rangedataactive", true);
    model.result("pg1").feature("surf43").set("rangedatamin", 1);
    model.result("pg1").feature("surf43").set("rangedatamax", 12);
    model.result("pg1").feature("surf43").set("coloring", "colortable");
    model.result("pg1").feature("surf43").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf43").set("colortablerev", true);
    model.result("pg1").feature("surf43").set("colorlegend", false);
    model.result("pg1").create("surf44", "SurfaceData");
    model.result("pg1").feature("surf44")
         .set("pointdata", new double[][]{{0.3433333333333333, 0.49166666666666664, 0.3433333333333333, 0.49166666666666664}, {0.0039999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0049999999999999975}});
    model.result("pg1").feature("surf44").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf44").set("colordata", new double[]{6, 6, 6, 6});
    model.result("pg1").feature("surf44").set("rangecoloractive", true);
    model.result("pg1").feature("surf44").set("rangecolormin", 1);
    model.result("pg1").feature("surf44").set("rangecolormax", 12);
    model.result("pg1").feature("surf44").set("rangedataactive", true);
    model.result("pg1").feature("surf44").set("rangedatamin", 1);
    model.result("pg1").feature("surf44").set("rangedatamax", 12);
    model.result("pg1").feature("surf44").set("coloring", "colortable");
    model.result("pg1").feature("surf44").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf44").set("colortablerev", true);
    model.result("pg1").feature("surf44").set("colorlegend", false);
    model.result("pg1").create("surf45", "SurfaceData");
    model.result("pg1").feature("surf45")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {0.0049999999999999975, 0.0049999999999999975, 0.0059999999999999975, 0.0059999999999999975}});
    model.result("pg1").feature("surf45").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf45").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf45").set("rangecoloractive", true);
    model.result("pg1").feature("surf45").set("rangecolormin", 1);
    model.result("pg1").feature("surf45").set("rangecolormax", 12);
    model.result("pg1").feature("surf45").set("rangedataactive", true);
    model.result("pg1").feature("surf45").set("rangedatamin", 1);
    model.result("pg1").feature("surf45").set("rangedatamax", 12);
    model.result("pg1").feature("surf45").set("coloring", "colortable");
    model.result("pg1").feature("surf45").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf45").set("colortablerev", true);
    model.result("pg1").feature("surf45").set("colorlegend", false);
    model.result("pg1").create("surf46", "SurfaceData");
    model.result("pg1").feature("surf46")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.48166666666666663, 0.3333333333333333, 0.48166666666666663}, {0.0059999999999999975, 0.0059999999999999975, 0.0069999999999999975, 0.0069999999999999975}});
    model.result("pg1").feature("surf46").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf46").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf46").set("rangecoloractive", true);
    model.result("pg1").feature("surf46").set("rangecolormin", 1);
    model.result("pg1").feature("surf46").set("rangecolormax", 12);
    model.result("pg1").feature("surf46").set("rangedataactive", true);
    model.result("pg1").feature("surf46").set("rangedatamin", 1);
    model.result("pg1").feature("surf46").set("rangedatamax", 12);
    model.result("pg1").feature("surf46").set("coloring", "colortable");
    model.result("pg1").feature("surf46").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf46").set("colortablerev", true);
    model.result("pg1").feature("surf46").set("colorlegend", false);
    model.result("pg1").create("line4", "LineData");
    model.result("pg1").feature("line4")
         .set("pointdata", new double[][]{{0.49124999999999996, 0.49166666666666664, 0.48124999999999996, 0.49166666666666664, 0.47124999999999995, 0.48374999999999996, 0.46125, 0.47375, 0.45125, 0.46375, 0.44125, 0.45374999999999993, 0.43124999999999997, 0.4437499999999999, 0.42125, 0.43374999999999997, 0.41125, 0.42374999999999996, 0.40125, 0.41374999999999995, 0.39125, 0.40375, 0.38125, 0.39375, 0.37124999999999997, 0.38375, 0.36124999999999996, 0.37374999999999997, 0.35125, 0.36375, 0.3433333333333333, 0.35375, 0.3433333333333333, 0.34375, 0.49124999999999996, 0.49166666666666664, 0.48124999999999996, 0.49166666666666664, 0.47124999999999995, 0.48374999999999996, 0.46125, 0.47375, 0.45125, 0.46375, 0.44125, 0.45375, 0.43124999999999997, 0.44375, 0.42125, 0.43374999999999997, 0.41125, 0.42374999999999996, 0.40125, 0.41374999999999995, 0.39125000000000004, 0.40375, 0.38125000000000003, 0.39375, 0.37124999999999997, 0.38375, 0.36124999999999996, 0.37374999999999997, 0.35125, 0.36375, 0.3433333333333333, 0.35375, 0.3433333333333333, 0.34375}, {-0.005000000000000002, -0.004966666666666668, -0.005000000000000003, -0.004166666666666668, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000003, -0.005000000000000003, -0.004000000000000002, -0.005000000000000003, -0.004000000000000003, -0.005000000000000002, -0.004000000000000003, -0.004833333333333337, -0.004000000000000003, -0.004033333333333336, -0.004000000000000003, 0.0039999999999999975, 0.004033333333333331, 0.0039999999999999975, 0.004833333333333332, 0.0039999999999999975, 0.004999999999999997, 0.0039999999999999975, 0.0049999999999999975, 0.003999999999999997, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.004166666666666661, 0.004999999999999998, 0.004966666666666664, 0.0049999999999999975}});
    model.result("pg1").feature("line4")
         .set("elementdata", new int[][]{{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66}, {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67}});
    model.result("pg1").feature("line4").set("coloring", "uniform");
    model.result("pg1").feature("line4").set("color", "custom");
    model.result("pg1").feature("line4").set("customcolor", new double[]{0, 0, 1});
    model.result("pg1").create("line5", "LineData");
    model.result("pg1").feature("line5")
         .set("pointdata", new double[][]{{0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664, 0.3333333333333333, 0.3433333333333333, 0.48166666666666663, 0.49166666666666664}, {-0.007000000000000003, -0.007000000000000003, -0.007000000000000003, -0.007000000000000003, -0.006000000000000003, -0.006000000000000003, -0.006000000000000003, -0.006000000000000003, -0.005000000000000003, -0.005000000000000003, -0.005000000000000003, -0.005000000000000003, -0.004000000000000003, -0.004000000000000003, -0.004000000000000003, -0.004000000000000003, -0.0030000000000000027, -0.0030000000000000027, -0.0030000000000000027, -0.0030000000000000027, -0.0020000000000000026, -0.0020000000000000026, -0.0020000000000000026, -0.0020000000000000026, -0.0010000000000000026, -0.0010000000000000026, -0.0010000000000000026, -0.0010000000000000026, -2.6020852139652106E-18, -2.6020852139652106E-18, -2.6020852139652106E-18, -2.6020852139652106E-18, 9.999999999999974E-4, 9.999999999999974E-4, 9.999999999999974E-4, 9.999999999999974E-4, 0.0019999999999999974, 0.0019999999999999974, 0.0019999999999999974, 0.0019999999999999974, 0.0029999999999999975, 0.0029999999999999975, 0.0029999999999999975, 0.0029999999999999975, 0.0039999999999999975, 0.0039999999999999975, 0.0039999999999999975, 0.0039999999999999975, 0.0049999999999999975, 0.0049999999999999975, 0.0049999999999999975, 0.0049999999999999975, 0.0059999999999999975, 0.0059999999999999975, 0.0059999999999999975, 0.0059999999999999975, 0.0069999999999999975, 0.0069999999999999975, 0.0069999999999999975, 0.0069999999999999975}});
    model.result("pg1").feature("line5")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 8, 8, 10, 12, 13, 15, 16, 16, 18, 20, 21, 23, 24, 24, 26, 28, 28, 30, 32, 32, 34, 36, 36, 38, 40, 41, 43, 44, 44, 46, 48, 49, 51, 52, 52, 54, 56, 56, 58}, {2, 6, 0, 2, 11, 4, 6, 15, 9, 11, 19, 12, 14, 23, 17, 19, 26, 20, 22, 30, 24, 26, 34, 28, 30, 39, 32, 34, 43, 37, 39, 47, 40, 42, 51, 45, 47, 54, 48, 50, 58, 52, 54}});
    model.result("pg1").feature("line5").set("coloring", "uniform");
    model.result("pg1").feature("line5").set("color", "custom");
    model.result("pg1").feature("line5").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.4125, 2, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -0.007800000000000003, 2, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c3\u201d:}}\\newline \\unicode{\u94fa\u5c42 [0/90]}\\newline \\unicode{\u73af\u6c27\u6811\u8102-2}\\newline \\unicode{\u94fa\u5c42 [-60]}\\newline \\unicode{\u94fa\u5c42 [60]}\\newline \\unicode{\u94fa\u5c42 [-45/45]}", 2, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf47", "SurfaceData");
    model.result("pg1").feature("surf47")
         .set("pointdata", new double[][]{{0.5, 0.6483333333333333, 0.5, 0.6483333333333333}, {-0.006000000000000002, -0.006000000000000002, -0.005000000000000002, -0.005000000000000002}});
    model.result("pg1").feature("surf47").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf47").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf47").set("rangecoloractive", true);
    model.result("pg1").feature("surf47").set("rangecolormin", 1);
    model.result("pg1").feature("surf47").set("rangecolormax", 12);
    model.result("pg1").feature("surf47").set("rangedataactive", true);
    model.result("pg1").feature("surf47").set("rangedatamin", 1);
    model.result("pg1").feature("surf47").set("rangedatamax", 12);
    model.result("pg1").feature("surf47").set("coloring", "colortable");
    model.result("pg1").feature("surf47").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf47").set("colortablerev", true);
    model.result("pg1").feature("surf47").set("colorlegend", false);
    model.result("pg1").create("surf48", "SurfaceData");
    model.result("pg1").feature("surf48")
         .set("pointdata", new double[][]{{0.5, 0.6483333333333333, 0.5, 0.6483333333333333}, {-0.005000000000000002, -0.005000000000000002, -0.004000000000000002, -0.004000000000000002}});
    model.result("pg1").feature("surf48").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf48").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf48").set("rangecoloractive", true);
    model.result("pg1").feature("surf48").set("rangecolormin", 1);
    model.result("pg1").feature("surf48").set("rangecolormax", 12);
    model.result("pg1").feature("surf48").set("rangedataactive", true);
    model.result("pg1").feature("surf48").set("rangedatamin", 1);
    model.result("pg1").feature("surf48").set("rangedatamax", 12);
    model.result("pg1").feature("surf48").set("coloring", "colortable");
    model.result("pg1").feature("surf48").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf48").set("colortablerev", true);
    model.result("pg1").feature("surf48").set("colorlegend", false);
    model.result("pg1").create("surf49", "SurfaceData");
    model.result("pg1").feature("surf49")
         .set("pointdata", new double[][]{{0.51, 0.6583333333333333, 0.51, 0.6583333333333333}, {-0.004000000000000002, -0.004000000000000002, -0.003000000000000002, -0.003000000000000002}});
    model.result("pg1").feature("surf49").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf49").set("colordata", new double[]{8, 8, 8, 8});
    model.result("pg1").feature("surf49").set("rangecoloractive", true);
    model.result("pg1").feature("surf49").set("rangecolormin", 1);
    model.result("pg1").feature("surf49").set("rangecolormax", 12);
    model.result("pg1").feature("surf49").set("rangedataactive", true);
    model.result("pg1").feature("surf49").set("rangedatamin", 1);
    model.result("pg1").feature("surf49").set("rangedatamax", 12);
    model.result("pg1").feature("surf49").set("coloring", "colortable");
    model.result("pg1").feature("surf49").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf49").set("colortablerev", true);
    model.result("pg1").feature("surf49").set("colorlegend", false);
    model.result("pg1").create("surf50", "SurfaceData");
    model.result("pg1").feature("surf50")
         .set("pointdata", new double[][]{{0.5, 0.6483333333333333, 0.5, 0.6483333333333333}, {-0.003000000000000002, -0.003000000000000002, -0.0020000000000000018, -0.0020000000000000018}});
    model.result("pg1").feature("surf50").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf50").set("colordata", new double[]{9, 9, 9, 9});
    model.result("pg1").feature("surf50").set("rangecoloractive", true);
    model.result("pg1").feature("surf50").set("rangecolormin", 1);
    model.result("pg1").feature("surf50").set("rangecolormax", 12);
    model.result("pg1").feature("surf50").set("rangedataactive", true);
    model.result("pg1").feature("surf50").set("rangedatamin", 1);
    model.result("pg1").feature("surf50").set("rangedatamax", 12);
    model.result("pg1").feature("surf50").set("coloring", "colortable");
    model.result("pg1").feature("surf50").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf50").set("colortablerev", true);
    model.result("pg1").feature("surf50").set("colorlegend", false);
    model.result("pg1").create("surf51", "SurfaceData");
    model.result("pg1").feature("surf51")
         .set("pointdata", new double[][]{{0.51, 0.6583333333333333, 0.51, 0.6583333333333333}, {-0.0020000000000000018, -0.0020000000000000018, -0.0010000000000000018, -0.0010000000000000018}});
    model.result("pg1").feature("surf51").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf51").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf51").set("rangecoloractive", true);
    model.result("pg1").feature("surf51").set("rangecolormin", 1);
    model.result("pg1").feature("surf51").set("rangecolormax", 12);
    model.result("pg1").feature("surf51").set("rangedataactive", true);
    model.result("pg1").feature("surf51").set("rangedatamin", 1);
    model.result("pg1").feature("surf51").set("rangedatamax", 12);
    model.result("pg1").feature("surf51").set("coloring", "colortable");
    model.result("pg1").feature("surf51").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf51").set("colortablerev", true);
    model.result("pg1").feature("surf51").set("colorlegend", false);
    model.result("pg1").create("surf52", "SurfaceData");
    model.result("pg1").feature("surf52")
         .set("pointdata", new double[][]{{0.51, 0.6583333333333333, 0.51, 0.6583333333333333}, {-0.0010000000000000018, -0.0010000000000000018, -1.734723475976807E-18, -1.734723475976807E-18}});
    model.result("pg1").feature("surf52").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf52").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf52").set("rangecoloractive", true);

    return model;
  }

  public static Model run4(Model model) {
    model.result("pg1").feature("surf52").set("rangecolormin", 1);
    model.result("pg1").feature("surf52").set("rangecolormax", 12);
    model.result("pg1").feature("surf52").set("rangedataactive", true);
    model.result("pg1").feature("surf52").set("rangedatamin", 1);
    model.result("pg1").feature("surf52").set("rangedatamax", 12);
    model.result("pg1").feature("surf52").set("coloring", "colortable");
    model.result("pg1").feature("surf52").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf52").set("colortablerev", true);
    model.result("pg1").feature("surf52").set("colorlegend", false);
    model.result("pg1").create("surf53", "SurfaceData");
    model.result("pg1").feature("surf53")
         .set("pointdata", new double[][]{{0.51, 0.6583333333333333, 0.51, 0.6583333333333333}, {-1.734723475976807E-18, -1.734723475976807E-18, 9.999999999999983E-4, 9.999999999999983E-4}});
    model.result("pg1").feature("surf53").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf53").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf53").set("rangecoloractive", true);
    model.result("pg1").feature("surf53").set("rangecolormin", 1);
    model.result("pg1").feature("surf53").set("rangecolormax", 12);
    model.result("pg1").feature("surf53").set("rangedataactive", true);
    model.result("pg1").feature("surf53").set("rangedatamin", 1);
    model.result("pg1").feature("surf53").set("rangedatamax", 12);
    model.result("pg1").feature("surf53").set("coloring", "colortable");
    model.result("pg1").feature("surf53").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf53").set("colortablerev", true);
    model.result("pg1").feature("surf53").set("colorlegend", false);
    model.result("pg1").create("surf54", "SurfaceData");
    model.result("pg1").feature("surf54")
         .set("pointdata", new double[][]{{0.51, 0.6583333333333333, 0.51, 0.6583333333333333}, {9.999999999999983E-4, 9.999999999999983E-4, 0.0019999999999999983, 0.0019999999999999983}});
    model.result("pg1").feature("surf54").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf54").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf54").set("rangecoloractive", true);
    model.result("pg1").feature("surf54").set("rangecolormin", 1);
    model.result("pg1").feature("surf54").set("rangecolormax", 12);
    model.result("pg1").feature("surf54").set("rangedataactive", true);
    model.result("pg1").feature("surf54").set("rangedatamin", 1);
    model.result("pg1").feature("surf54").set("rangedatamax", 12);
    model.result("pg1").feature("surf54").set("coloring", "colortable");
    model.result("pg1").feature("surf54").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf54").set("colortablerev", true);
    model.result("pg1").feature("surf54").set("colorlegend", false);
    model.result("pg1").create("surf55", "SurfaceData");
    model.result("pg1").feature("surf55")
         .set("pointdata", new double[][]{{0.5, 0.6483333333333333, 0.5, 0.6483333333333333}, {0.0019999999999999983, 0.0019999999999999983, 0.0029999999999999983, 0.0029999999999999983}});
    model.result("pg1").feature("surf55").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf55").set("colordata", new double[]{9, 9, 9, 9});
    model.result("pg1").feature("surf55").set("rangecoloractive", true);
    model.result("pg1").feature("surf55").set("rangecolormin", 1);
    model.result("pg1").feature("surf55").set("rangecolormax", 12);
    model.result("pg1").feature("surf55").set("rangedataactive", true);
    model.result("pg1").feature("surf55").set("rangedatamin", 1);
    model.result("pg1").feature("surf55").set("rangedatamax", 12);
    model.result("pg1").feature("surf55").set("coloring", "colortable");
    model.result("pg1").feature("surf55").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf55").set("colortablerev", true);
    model.result("pg1").feature("surf55").set("colorlegend", false);
    model.result("pg1").create("surf56", "SurfaceData");
    model.result("pg1").feature("surf56")
         .set("pointdata", new double[][]{{0.51, 0.6583333333333333, 0.51, 0.6583333333333333}, {0.0029999999999999983, 0.0029999999999999983, 0.003999999999999998, 0.003999999999999998}});
    model.result("pg1").feature("surf56").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf56").set("colordata", new double[]{8, 8, 8, 8});
    model.result("pg1").feature("surf56").set("rangecoloractive", true);
    model.result("pg1").feature("surf56").set("rangecolormin", 1);
    model.result("pg1").feature("surf56").set("rangecolormax", 12);
    model.result("pg1").feature("surf56").set("rangedataactive", true);
    model.result("pg1").feature("surf56").set("rangedatamin", 1);
    model.result("pg1").feature("surf56").set("rangedatamax", 12);
    model.result("pg1").feature("surf56").set("coloring", "colortable");
    model.result("pg1").feature("surf56").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf56").set("colortablerev", true);
    model.result("pg1").feature("surf56").set("colorlegend", false);
    model.result("pg1").create("surf57", "SurfaceData");
    model.result("pg1").feature("surf57")
         .set("pointdata", new double[][]{{0.5, 0.6483333333333333, 0.5, 0.6483333333333333}, {0.003999999999999998, 0.003999999999999998, 0.004999999999999998, 0.004999999999999998}});
    model.result("pg1").feature("surf57").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf57").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf57").set("rangecoloractive", true);
    model.result("pg1").feature("surf57").set("rangecolormin", 1);
    model.result("pg1").feature("surf57").set("rangecolormax", 12);
    model.result("pg1").feature("surf57").set("rangedataactive", true);
    model.result("pg1").feature("surf57").set("rangedatamin", 1);
    model.result("pg1").feature("surf57").set("rangedatamax", 12);
    model.result("pg1").feature("surf57").set("coloring", "colortable");
    model.result("pg1").feature("surf57").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf57").set("colortablerev", true);
    model.result("pg1").feature("surf57").set("colorlegend", false);
    model.result("pg1").create("surf58", "SurfaceData");
    model.result("pg1").feature("surf58")
         .set("pointdata", new double[][]{{0.5, 0.6483333333333333, 0.5, 0.6483333333333333}, {0.004999999999999998, 0.004999999999999998, 0.005999999999999998, 0.005999999999999998}});
    model.result("pg1").feature("surf58").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf58").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf58").set("rangecoloractive", true);
    model.result("pg1").feature("surf58").set("rangecolormin", 1);
    model.result("pg1").feature("surf58").set("rangecolormax", 12);
    model.result("pg1").feature("surf58").set("rangedataactive", true);
    model.result("pg1").feature("surf58").set("rangedatamin", 1);
    model.result("pg1").feature("surf58").set("rangedatamax", 12);
    model.result("pg1").feature("surf58").set("coloring", "colortable");
    model.result("pg1").feature("surf58").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf58").set("colortablerev", true);
    model.result("pg1").feature("surf58").set("colorlegend", false);
    model.result("pg1").create("line6", "LineData");
    model.result("pg1").feature("line6")
         .set("pointdata", new double[][]{{0.6579166666666667, 0.6583333333333333, 0.6479166666666668, 0.6583333333333333, 0.6379166666666667, 0.6504166666666666, 0.6279166666666667, 0.6404166666666667, 0.6179166666666667, 0.6304166666666666, 0.6079166666666667, 0.6204166666666667, 0.5979166666666668, 0.6104166666666667, 0.5879166666666666, 0.6004166666666667, 0.5779166666666667, 0.5904166666666667, 0.5679166666666667, 0.5804166666666668, 0.5579166666666667, 0.5704166666666667, 0.5479166666666667, 0.5604166666666668, 0.5379166666666668, 0.5504166666666668, 0.5279166666666667, 0.5404166666666667, 0.5179166666666668, 0.5304166666666668, 0.51, 0.5204166666666666, 0.51, 0.5104166666666667, 0.6579166666666667, 0.6583333333333333, 0.6479166666666668, 0.6583333333333333, 0.6379166666666667, 0.6504166666666666, 0.6279166666666668, 0.6404166666666667, 0.6179166666666667, 0.6304166666666666, 0.6079166666666667, 0.6204166666666667, 0.5979166666666668, 0.6104166666666667, 0.5879166666666666, 0.6004166666666667, 0.5779166666666667, 0.5904166666666667, 0.5679166666666667, 0.5804166666666668, 0.5579166666666667, 0.5704166666666667, 0.5479166666666667, 0.5604166666666668, 0.5379166666666668, 0.5504166666666668, 0.5279166666666667, 0.5404166666666668, 0.5179166666666668, 0.5304166666666668, 0.51, 0.5204166666666666, 0.51, 0.5104166666666667}, {-0.004000000000000002, -0.003966666666666673, -0.004000000000000002, -0.003166666666666676, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.003000000000000002, -0.004000000000000002, -0.0030000000000000022, -0.004000000000000002, -0.0030000000000000014, -0.0038333333333333366, -0.0030000000000000022, -0.0030333333333333397, -0.0030000000000000022, 0.0029999999999999988, 0.0030333333333333276, 0.0029999999999999988, 0.0038333333333333245, 0.002999999999999998, 0.003999999999999998, 0.0029999999999999988, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.0029999999999999983, 0.003999999999999998, 0.003166666666666664, 0.003999999999999998, 0.003966666666666661, 0.003999999999999998}});
    model.result("pg1").feature("line6")
         .set("elementdata", new int[][]{{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66}, {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67}});
    model.result("pg1").feature("line6").set("coloring", "uniform");
    model.result("pg1").feature("line6").set("color", "custom");
    model.result("pg1").feature("line6").set("customcolor", new double[]{0, 0, 1});
    model.result("pg1").create("line7", "LineData");
    model.result("pg1").feature("line7")
         .set("pointdata", new double[][]{{0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333, 0.5, 0.51, 0.6483333333333333, 0.6583333333333333}, {-0.006000000000000002, -0.006000000000000002, -0.006000000000000002, -0.006000000000000002, -0.005000000000000002, -0.005000000000000002, -0.005000000000000002, -0.005000000000000002, -0.004000000000000002, -0.004000000000000002, -0.004000000000000002, -0.004000000000000002, -0.003000000000000002, -0.003000000000000002, -0.003000000000000002, -0.003000000000000002, -0.0020000000000000018, -0.0020000000000000018, -0.0020000000000000018, -0.0020000000000000018, -0.0010000000000000018, -0.0010000000000000018, -0.0010000000000000018, -0.0010000000000000018, -1.734723475976807E-18, -1.734723475976807E-18, -1.734723475976807E-18, -1.734723475976807E-18, 9.999999999999983E-4, 9.999999999999983E-4, 9.999999999999983E-4, 9.999999999999983E-4, 0.0019999999999999983, 0.0019999999999999983, 0.0019999999999999983, 0.0019999999999999983, 0.0029999999999999983, 0.0029999999999999983, 0.0029999999999999983, 0.0029999999999999983, 0.003999999999999998, 0.003999999999999998, 0.003999999999999998, 0.003999999999999998, 0.004999999999999998, 0.004999999999999998, 0.004999999999999998, 0.004999999999999998, 0.005999999999999998, 0.005999999999999998, 0.005999999999999998, 0.005999999999999998}});
    model.result("pg1").feature("line7")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 8, 8, 10, 12, 13, 15, 16, 16, 18, 21, 21, 23, 25, 25, 27, 29, 29, 31, 32, 33, 35, 36, 36, 38, 40, 41, 43, 44, 44, 46, 48, 48, 50}, {2, 6, 0, 2, 11, 4, 6, 15, 9, 11, 19, 12, 14, 23, 17, 19, 27, 21, 23, 31, 25, 27, 35, 29, 31, 39, 32, 34, 43, 37, 39, 46, 40, 42, 50, 44, 46}});
    model.result("pg1").feature("line7").set("coloring", "uniform");
    model.result("pg1").feature("line7").set("color", "custom");
    model.result("pg1").feature("line7").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.5791666666666666, 3, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -0.006800000000000002, 3, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c4\u201d:}}\\newline \\unicode{\u94fa\u5c42 [0/90]}\\newline \\unicode{\u73af\u6c27\u6811\u8102-3}\\newline \\unicode{\u94fa\u5c42 [60]}\\newline \\unicode{\u94fa\u5c42 [-45/45]}", 3, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf59", "SurfaceData");
    model.result("pg1").feature("surf59")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.815, 0.6666666666666666, 0.815}, {-0.005000000000000001, -0.005000000000000001, -0.004000000000000001, -0.004000000000000001}});
    model.result("pg1").feature("surf59").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf59").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf59").set("rangecoloractive", true);
    model.result("pg1").feature("surf59").set("rangecolormin", 1);
    model.result("pg1").feature("surf59").set("rangecolormax", 12);
    model.result("pg1").feature("surf59").set("rangedataactive", true);
    model.result("pg1").feature("surf59").set("rangedatamin", 1);
    model.result("pg1").feature("surf59").set("rangedatamax", 12);
    model.result("pg1").feature("surf59").set("coloring", "colortable");
    model.result("pg1").feature("surf59").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf59").set("colortablerev", true);
    model.result("pg1").feature("surf59").set("colorlegend", false);
    model.result("pg1").create("surf60", "SurfaceData");
    model.result("pg1").feature("surf60")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.815, 0.6666666666666666, 0.815}, {-0.004000000000000001, -0.004000000000000001, -0.003000000000000001, -0.003000000000000001}});
    model.result("pg1").feature("surf60").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf60").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf60").set("rangecoloractive", true);
    model.result("pg1").feature("surf60").set("rangecolormin", 1);
    model.result("pg1").feature("surf60").set("rangecolormax", 12);
    model.result("pg1").feature("surf60").set("rangedataactive", true);
    model.result("pg1").feature("surf60").set("rangedatamin", 1);
    model.result("pg1").feature("surf60").set("rangedatamax", 12);
    model.result("pg1").feature("surf60").set("coloring", "colortable");
    model.result("pg1").feature("surf60").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf60").set("colortablerev", true);
    model.result("pg1").feature("surf60").set("colorlegend", false);
    model.result("pg1").create("surf61", "SurfaceData");
    model.result("pg1").feature("surf61")
         .set("pointdata", new double[][]{{0.6766666666666666, 0.825, 0.6766666666666666, 0.825}, {-0.003000000000000001, -0.003000000000000001, -0.002000000000000001, -0.002000000000000001}});
    model.result("pg1").feature("surf61").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf61").set("colordata", new double[]{10, 10, 10, 10});
    model.result("pg1").feature("surf61").set("rangecoloractive", true);
    model.result("pg1").feature("surf61").set("rangecolormin", 1);
    model.result("pg1").feature("surf61").set("rangecolormax", 12);
    model.result("pg1").feature("surf61").set("rangedataactive", true);
    model.result("pg1").feature("surf61").set("rangedatamin", 1);
    model.result("pg1").feature("surf61").set("rangedatamax", 12);
    model.result("pg1").feature("surf61").set("coloring", "colortable");
    model.result("pg1").feature("surf61").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf61").set("colortablerev", true);
    model.result("pg1").feature("surf61").set("colorlegend", false);
    model.result("pg1").create("surf62", "SurfaceData");
    model.result("pg1").feature("surf62")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.815, 0.6666666666666666, 0.815}, {-0.002000000000000001, -0.002000000000000001, -0.0010000000000000009, -0.0010000000000000009}});
    model.result("pg1").feature("surf62").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf62").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf62").set("rangecoloractive", true);
    model.result("pg1").feature("surf62").set("rangecolormin", 1);
    model.result("pg1").feature("surf62").set("rangecolormax", 12);
    model.result("pg1").feature("surf62").set("rangedataactive", true);
    model.result("pg1").feature("surf62").set("rangedatamin", 1);
    model.result("pg1").feature("surf62").set("rangedatamax", 12);
    model.result("pg1").feature("surf62").set("coloring", "colortable");
    model.result("pg1").feature("surf62").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf62").set("colortablerev", true);
    model.result("pg1").feature("surf62").set("colorlegend", false);
    model.result("pg1").create("surf63", "SurfaceData");
    model.result("pg1").feature("surf63")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.815, 0.6666666666666666, 0.815}, {-0.0010000000000000009, -0.0010000000000000009, -8.673617379884035E-19, -8.673617379884035E-19}});
    model.result("pg1").feature("surf63").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf63").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf63").set("rangecoloractive", true);
    model.result("pg1").feature("surf63").set("rangecolormin", 1);
    model.result("pg1").feature("surf63").set("rangecolormax", 12);
    model.result("pg1").feature("surf63").set("rangedataactive", true);
    model.result("pg1").feature("surf63").set("rangedatamin", 1);
    model.result("pg1").feature("surf63").set("rangedatamax", 12);
    model.result("pg1").feature("surf63").set("coloring", "colortable");
    model.result("pg1").feature("surf63").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf63").set("colortablerev", true);
    model.result("pg1").feature("surf63").set("colorlegend", false);
    model.result("pg1").create("surf64", "SurfaceData");
    model.result("pg1").feature("surf64")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.815, 0.6666666666666666, 0.815}, {-8.673617379884035E-19, -8.673617379884035E-19, 9.999999999999992E-4, 9.999999999999992E-4}});
    model.result("pg1").feature("surf64").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf64").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf64").set("rangecoloractive", true);
    model.result("pg1").feature("surf64").set("rangecolormin", 1);
    model.result("pg1").feature("surf64").set("rangecolormax", 12);
    model.result("pg1").feature("surf64").set("rangedataactive", true);
    model.result("pg1").feature("surf64").set("rangedatamin", 1);
    model.result("pg1").feature("surf64").set("rangedatamax", 12);
    model.result("pg1").feature("surf64").set("coloring", "colortable");
    model.result("pg1").feature("surf64").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf64").set("colortablerev", true);
    model.result("pg1").feature("surf64").set("colorlegend", false);
    model.result("pg1").create("surf65", "SurfaceData");
    model.result("pg1").feature("surf65")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.815, 0.6666666666666666, 0.815}, {9.999999999999992E-4, 9.999999999999992E-4, 0.001999999999999999, 0.001999999999999999}});
    model.result("pg1").feature("surf65").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf65").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf65").set("rangecoloractive", true);
    model.result("pg1").feature("surf65").set("rangecolormin", 1);
    model.result("pg1").feature("surf65").set("rangecolormax", 12);
    model.result("pg1").feature("surf65").set("rangedataactive", true);
    model.result("pg1").feature("surf65").set("rangedatamin", 1);
    model.result("pg1").feature("surf65").set("rangedatamax", 12);
    model.result("pg1").feature("surf65").set("coloring", "colortable");
    model.result("pg1").feature("surf65").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf65").set("colortablerev", true);
    model.result("pg1").feature("surf65").set("colorlegend", false);
    model.result("pg1").create("surf66", "SurfaceData");
    model.result("pg1").feature("surf66")
         .set("pointdata", new double[][]{{0.6766666666666666, 0.825, 0.6766666666666666, 0.825}, {0.001999999999999999, 0.001999999999999999, 0.002999999999999999, 0.002999999999999999}});
    model.result("pg1").feature("surf66").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf66").set("colordata", new double[]{10, 10, 10, 10});
    model.result("pg1").feature("surf66").set("rangecoloractive", true);
    model.result("pg1").feature("surf66").set("rangecolormin", 1);
    model.result("pg1").feature("surf66").set("rangecolormax", 12);
    model.result("pg1").feature("surf66").set("rangedataactive", true);
    model.result("pg1").feature("surf66").set("rangedatamin", 1);
    model.result("pg1").feature("surf66").set("rangedatamax", 12);
    model.result("pg1").feature("surf66").set("coloring", "colortable");
    model.result("pg1").feature("surf66").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf66").set("colortablerev", true);
    model.result("pg1").feature("surf66").set("colorlegend", false);
    model.result("pg1").create("surf67", "SurfaceData");
    model.result("pg1").feature("surf67")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.815, 0.6666666666666666, 0.815}, {0.002999999999999999, 0.002999999999999999, 0.003999999999999999, 0.003999999999999999}});
    model.result("pg1").feature("surf67").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf67").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf67").set("rangecoloractive", true);
    model.result("pg1").feature("surf67").set("rangecolormin", 1);
    model.result("pg1").feature("surf67").set("rangecolormax", 12);
    model.result("pg1").feature("surf67").set("rangedataactive", true);
    model.result("pg1").feature("surf67").set("rangedatamin", 1);
    model.result("pg1").feature("surf67").set("rangedatamax", 12);
    model.result("pg1").feature("surf67").set("coloring", "colortable");
    model.result("pg1").feature("surf67").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf67").set("colortablerev", true);
    model.result("pg1").feature("surf67").set("colorlegend", false);
    model.result("pg1").create("surf68", "SurfaceData");
    model.result("pg1").feature("surf68")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.815, 0.6666666666666666, 0.815}, {0.003999999999999999, 0.003999999999999999, 0.004999999999999999, 0.004999999999999999}});
    model.result("pg1").feature("surf68").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf68").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf68").set("rangecoloractive", true);
    model.result("pg1").feature("surf68").set("rangecolormin", 1);
    model.result("pg1").feature("surf68").set("rangecolormax", 12);
    model.result("pg1").feature("surf68").set("rangedataactive", true);
    model.result("pg1").feature("surf68").set("rangedatamin", 1);
    model.result("pg1").feature("surf68").set("rangedatamax", 12);
    model.result("pg1").feature("surf68").set("coloring", "colortable");
    model.result("pg1").feature("surf68").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf68").set("colortablerev", true);
    model.result("pg1").feature("surf68").set("colorlegend", false);
    model.result("pg1").create("line8", "LineData");
    model.result("pg1").feature("line8")
         .set("pointdata", new double[][]{{0.8245833333333332, 0.825, 0.8145833333333333, 0.825, 0.8045833333333332, 0.8170833333333332, 0.7945833333333332, 0.8070833333333333, 0.7845833333333332, 0.7970833333333331, 0.7745833333333332, 0.7870833333333332, 0.7645833333333333, 0.7770833333333332, 0.7545833333333332, 0.7670833333333332, 0.7445833333333333, 0.7570833333333332, 0.7345833333333333, 0.7470833333333333, 0.7245833333333332, 0.7370833333333332, 0.7145833333333332, 0.7270833333333333, 0.7045833333333333, 0.7170833333333333, 0.6945833333333332, 0.7070833333333332, 0.6845833333333333, 0.6970833333333333, 0.6766666666666666, 0.6870833333333332, 0.6766666666666666, 0.6770833333333333, 0.8245833333333332, 0.825, 0.8145833333333333, 0.825, 0.8045833333333332, 0.8170833333333332, 0.7945833333333333, 0.8070833333333333, 0.7845833333333332, 0.7970833333333331, 0.7745833333333332, 0.7870833333333332, 0.7645833333333333, 0.7770833333333332, 0.7545833333333332, 0.7670833333333332, 0.7445833333333333, 0.7570833333333332, 0.7345833333333333, 0.7470833333333333, 0.7245833333333332, 0.7370833333333332, 0.7145833333333332, 0.7270833333333333, 0.7045833333333333, 0.7170833333333333, 0.6945833333333332, 0.7070833333333333, 0.6845833333333333, 0.6970833333333333, 0.6766666666666666, 0.6870833333333332, 0.6766666666666666, 0.6770833333333333}, {-0.003000000000000001, -0.0029666666666666635, -0.003000000000000001, -0.0021666666666666666, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.003000000000000001, -0.002000000000000001, -0.0030000000000000014, -0.0020000000000000013, -0.0030000000000000005, -0.0020000000000000005, -0.002833333333333327, -0.0020000000000000013, -0.00203333333333333, -0.0020000000000000013, 0.0019999999999999996, 0.002033333333333337, 0.0019999999999999996, 0.002833333333333334, 0.0019999999999999987, 0.0029999999999999988, 0.0019999999999999996, 0.0029999999999999996, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.001999999999999999, 0.002999999999999999, 0.0021666666666666735, 0.002999999999999999, 0.0029666666666666704, 0.002999999999999999}});
    model.result("pg1").feature("line8")
         .set("elementdata", new int[][]{{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66}, {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67}});
    model.result("pg1").feature("line8").set("coloring", "uniform");
    model.result("pg1").feature("line8").set("color", "custom");
    model.result("pg1").feature("line8").set("customcolor", new double[]{0, 0, 1});
    model.result("pg1").create("line9", "LineData");
    model.result("pg1").feature("line9")
         .set("pointdata", new double[][]{{0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825, 0.6666666666666666, 0.6766666666666666, 0.815, 0.825}, {-0.005000000000000001, -0.005000000000000001, -0.005000000000000001, -0.005000000000000001, -0.004000000000000001, -0.004000000000000001, -0.004000000000000001, -0.004000000000000001, -0.003000000000000001, -0.003000000000000001, -0.003000000000000001, -0.003000000000000001, -0.002000000000000001, -0.002000000000000001, -0.002000000000000001, -0.002000000000000001, -0.0010000000000000009, -0.0010000000000000009, -0.0010000000000000009, -0.0010000000000000009, -8.673617379884035E-19, -8.673617379884035E-19, -8.673617379884035E-19, -8.673617379884035E-19, 9.999999999999992E-4, 9.999999999999992E-4, 9.999999999999992E-4, 9.999999999999992E-4, 0.001999999999999999, 0.001999999999999999, 0.001999999999999999, 0.001999999999999999, 0.002999999999999999, 0.002999999999999999, 0.002999999999999999, 0.002999999999999999, 0.003999999999999999, 0.003999999999999999, 0.003999999999999999, 0.003999999999999999, 0.004999999999999999, 0.004999999999999999, 0.004999999999999999, 0.004999999999999999}});
    model.result("pg1").feature("line9")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 8, 8, 10, 12, 13, 15, 16, 16, 18, 20, 20, 22, 24, 24, 26, 28, 28, 30, 32, 33, 35, 36, 36, 38, 40, 40, 42}, {2, 6, 0, 2, 11, 4, 6, 15, 9, 11, 18, 12, 14, 22, 16, 18, 26, 20, 22, 31, 24, 26, 35, 29, 31, 38, 32, 34, 42, 36, 38}});
    model.result("pg1").feature("line9").set("coloring", "uniform");
    model.result("pg1").feature("line9").set("color", "custom");
    model.result("pg1").feature("line9").set("customcolor", new double[]{0, 0, 0});

    return model;
  }

  public static Model run5(Model model) {
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.7458333333333333, 4, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -0.005800000000000001, 4, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c5\u201d:}}\\newline \\unicode{\u94fa\u5c42 [0/90]}\\newline \\unicode{\u73af\u6c27\u6811\u8102-4}\\newline \\unicode{\u94fa\u5c42 [-45/45]}", 4, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf69", "SurfaceData");
    model.result("pg1").feature("surf69")
         .set("pointdata", new double[][]{{0.8333333333333333, 0.9816666666666666, 0.8333333333333333, 0.9816666666666666}, {-0.004, -0.004, -0.003, -0.003}});
    model.result("pg1").feature("surf69").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf69").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf69").set("rangecoloractive", true);
    model.result("pg1").feature("surf69").set("rangecolormin", 1);
    model.result("pg1").feature("surf69").set("rangecolormax", 12);
    model.result("pg1").feature("surf69").set("rangedataactive", true);
    model.result("pg1").feature("surf69").set("rangedatamin", 1);
    model.result("pg1").feature("surf69").set("rangedatamax", 12);
    model.result("pg1").feature("surf69").set("coloring", "colortable");
    model.result("pg1").feature("surf69").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf69").set("colortablerev", true);
    model.result("pg1").feature("surf69").set("colorlegend", false);
    model.result("pg1").create("surf70", "SurfaceData");
    model.result("pg1").feature("surf70")
         .set("pointdata", new double[][]{{0.8333333333333333, 0.9816666666666666, 0.8333333333333333, 0.9816666666666666}, {-0.003, -0.003, -0.002, -0.002}});
    model.result("pg1").feature("surf70").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf70").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf70").set("rangecoloractive", true);
    model.result("pg1").feature("surf70").set("rangecolormin", 1);
    model.result("pg1").feature("surf70").set("rangecolormax", 12);
    model.result("pg1").feature("surf70").set("rangedataactive", true);
    model.result("pg1").feature("surf70").set("rangedatamin", 1);
    model.result("pg1").feature("surf70").set("rangedatamax", 12);
    model.result("pg1").feature("surf70").set("coloring", "colortable");
    model.result("pg1").feature("surf70").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf70").set("colortablerev", true);
    model.result("pg1").feature("surf70").set("colorlegend", false);
    model.result("pg1").create("surf71", "SurfaceData");
    model.result("pg1").feature("surf71")
         .set("pointdata", new double[][]{{0.8433333333333333, 0.9916666666666666, 0.8433333333333333, 0.9916666666666666}, {-0.002, -0.002, -0.001, -0.001}});
    model.result("pg1").feature("surf71").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf71").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf71").set("rangecoloractive", true);
    model.result("pg1").feature("surf71").set("rangecolormin", 1);
    model.result("pg1").feature("surf71").set("rangecolormax", 12);
    model.result("pg1").feature("surf71").set("rangedataactive", true);
    model.result("pg1").feature("surf71").set("rangedatamin", 1);
    model.result("pg1").feature("surf71").set("rangedatamax", 12);
    model.result("pg1").feature("surf71").set("coloring", "colortable");
    model.result("pg1").feature("surf71").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf71").set("colortablerev", true);
    model.result("pg1").feature("surf71").set("colorlegend", false);
    model.result("pg1").create("surf72", "SurfaceData");
    model.result("pg1").feature("surf72")
         .set("pointdata", new double[][]{{0.8433333333333333, 0.9916666666666666, 0.8433333333333333, 0.9916666666666666}, {-0.001, -0.001, 0, 0}});
    model.result("pg1").feature("surf72").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf72").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf72").set("rangecoloractive", true);
    model.result("pg1").feature("surf72").set("rangecolormin", 1);
    model.result("pg1").feature("surf72").set("rangecolormax", 12);
    model.result("pg1").feature("surf72").set("rangedataactive", true);
    model.result("pg1").feature("surf72").set("rangedatamin", 1);
    model.result("pg1").feature("surf72").set("rangedatamax", 12);
    model.result("pg1").feature("surf72").set("coloring", "colortable");
    model.result("pg1").feature("surf72").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf72").set("colortablerev", true);
    model.result("pg1").feature("surf72").set("colorlegend", false);
    model.result("pg1").create("surf73", "SurfaceData");
    model.result("pg1").feature("surf73")
         .set("pointdata", new double[][]{{0.8433333333333333, 0.9916666666666666, 0.8433333333333333, 0.9916666666666666}, {0, 0, 0.001, 0.001}});
    model.result("pg1").feature("surf73").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf73").set("colordata", new double[]{12, 12, 12, 12});
    model.result("pg1").feature("surf73").set("rangecoloractive", true);
    model.result("pg1").feature("surf73").set("rangecolormin", 1);
    model.result("pg1").feature("surf73").set("rangecolormax", 12);
    model.result("pg1").feature("surf73").set("rangedataactive", true);
    model.result("pg1").feature("surf73").set("rangedatamin", 1);
    model.result("pg1").feature("surf73").set("rangedatamax", 12);
    model.result("pg1").feature("surf73").set("coloring", "colortable");
    model.result("pg1").feature("surf73").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf73").set("colortablerev", true);
    model.result("pg1").feature("surf73").set("colorlegend", false);
    model.result("pg1").create("surf74", "SurfaceData");
    model.result("pg1").feature("surf74")
         .set("pointdata", new double[][]{{0.8433333333333333, 0.9916666666666666, 0.8433333333333333, 0.9916666666666666}, {0.001, 0.001, 0.002, 0.002}});
    model.result("pg1").feature("surf74").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf74").set("colordata", new double[]{11, 11, 11, 11});
    model.result("pg1").feature("surf74").set("rangecoloractive", true);
    model.result("pg1").feature("surf74").set("rangecolormin", 1);
    model.result("pg1").feature("surf74").set("rangecolormax", 12);
    model.result("pg1").feature("surf74").set("rangedataactive", true);
    model.result("pg1").feature("surf74").set("rangedatamin", 1);
    model.result("pg1").feature("surf74").set("rangedatamax", 12);
    model.result("pg1").feature("surf74").set("coloring", "colortable");
    model.result("pg1").feature("surf74").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf74").set("colortablerev", true);
    model.result("pg1").feature("surf74").set("colorlegend", false);
    model.result("pg1").create("surf75", "SurfaceData");
    model.result("pg1").feature("surf75")
         .set("pointdata", new double[][]{{0.8333333333333333, 0.9816666666666666, 0.8333333333333333, 0.9816666666666666}, {0.002, 0.002, 0.003, 0.003}});
    model.result("pg1").feature("surf75").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf75").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf75").set("rangecoloractive", true);
    model.result("pg1").feature("surf75").set("rangecolormin", 1);
    model.result("pg1").feature("surf75").set("rangecolormax", 12);
    model.result("pg1").feature("surf75").set("rangedataactive", true);
    model.result("pg1").feature("surf75").set("rangedatamin", 1);
    model.result("pg1").feature("surf75").set("rangedatamax", 12);
    model.result("pg1").feature("surf75").set("coloring", "colortable");
    model.result("pg1").feature("surf75").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf75").set("colortablerev", true);
    model.result("pg1").feature("surf75").set("colorlegend", false);
    model.result("pg1").create("surf76", "SurfaceData");
    model.result("pg1").feature("surf76")
         .set("pointdata", new double[][]{{0.8333333333333333, 0.9816666666666666, 0.8333333333333333, 0.9816666666666666}, {0.003, 0.003, 0.004, 0.004}});
    model.result("pg1").feature("surf76").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf76").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf76").set("rangecoloractive", true);
    model.result("pg1").feature("surf76").set("rangecolormin", 1);
    model.result("pg1").feature("surf76").set("rangecolormax", 12);
    model.result("pg1").feature("surf76").set("rangedataactive", true);
    model.result("pg1").feature("surf76").set("rangedatamin", 1);
    model.result("pg1").feature("surf76").set("rangedatamax", 12);
    model.result("pg1").feature("surf76").set("coloring", "colortable");
    model.result("pg1").feature("surf76").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf76").set("colortablerev", true);
    model.result("pg1").feature("surf76").set("colorlegend", false);
    model.result("pg1").create("line10", "LineData");
    model.result("pg1").feature("line10")
         .set("pointdata", new double[][]{{0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666, 0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666, 0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666, 0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666, 0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666, 0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666, 0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666, 0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666, 0.8333333333333333, 0.8433333333333333, 0.9816666666666666, 0.9916666666666666}, {-0.004, -0.004, -0.004, -0.004, -0.003, -0.003, -0.003, -0.003, -0.002, -0.002, -0.002, -0.002, -0.001, -0.001, -0.001, -0.001, 0, 0, 0, 0, 0.001, 0.001, 0.001, 0.001, 0.002, 0.002, 0.002, 0.002, 0.003, 0.003, 0.003, 0.003, 0.004, 0.004, 0.004, 0.004}});
    model.result("pg1").feature("line10")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 8, 8, 10, 13, 13, 15, 17, 17, 19, 21, 21, 23, 24, 25, 27, 28, 28, 30, 32, 32, 34}, {2, 6, 0, 2, 11, 4, 6, 15, 9, 11, 19, 13, 15, 23, 17, 19, 27, 21, 23, 30, 24, 26, 34, 28, 30}});
    model.result("pg1").feature("line10").set("coloring", "uniform");
    model.result("pg1").feature("line10").set("color", "custom");
    model.result("pg1").feature("line10").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.9124999999999999, 5, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -0.0048000000000000004, 5, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c6\u201d:}}\\newline \\unicode{\u94fa\u5c42 [0/90]}\\newline \\unicode{\u94fa\u5c42 [-45/45]}", 5, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("line11", "LineData");
    model.result("pg1").feature("line11")
         .set("pointdata", new double[][]{{-0.01, 0.030466666666666663, -0.01, 0.030466666666666663, 0.04058333333333333, 0.08105, 0.04058333333333333, 0.08105, 0.09116666666666667, 0.13163333333333332, 0.09116666666666667, 0.13163333333333332, 0.14175, 0.18221666666666664, 0.14175, 0.18221666666666664, 0.19233333333333333, 0.2328, 0.19233333333333333, 0.2328, 0.24291666666666667, 0.2833833333333333, 0.24291666666666667, 0.2833833333333333, 0.2935, 0.33396666666666663, 0.2935, 0.33396666666666663, 0.34408333333333335, 0.38455, 0.34408333333333335, 0.38455, 0.39466666666666667, 0.4351333333333333, 0.39466666666666667, 0.4351333333333333, 0.44525, 0.48571666666666663, 0.44525, 0.48571666666666663, 0.49583333333333335, 0.5363, 0.49583333333333335, 0.5363, 0.5464166666666667, 0.5868833333333333, 0.5464166666666667, 0.5868833333333333, 0.597, 0.6374666666666666, 0.597, 0.6374666666666666, 0.6475833333333333, 0.6880499999999999, 0.6475833333333333, 0.6880499999999999, 0.6981666666666667, 0.7386333333333334, 0.6981666666666667, 0.7386333333333334, 0.74875, 0.7892166666666667, 0.74875, 0.7892166666666667, 0.7993333333333333, 0.8398, 0.7993333333333333, 0.8398, 0.8499166666666667, 0.8903833333333333, 0.8499166666666667, 0.8903833333333333, 0.9005, 0.9409666666666666, 0.9005, 0.9409666666666666, 0.9510833333333334, 0.99155, 0.9510833333333334, 0.99155}, {-8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5, -8.000000000000003E-5, -8.000000000000003E-5, 8.000000000000003E-5, 8.000000000000003E-5}});
    model.result("pg1").feature("line11")
         .set("elementdata", new int[][]{{0, 1, 3, 2, 4, 5, 7, 6, 8, 9, 11, 10, 12, 13, 15, 14, 16, 17, 19, 18, 20, 21, 23, 22, 24, 25, 27, 26, 28, 29, 31, 30, 32, 33, 35, 34, 36, 37, 39, 38, 40, 41, 43, 42, 44, 45, 47, 46, 48, 49, 51, 50, 52, 53, 55, 54, 56, 57, 59, 58, 60, 61, 63, 62, 64, 65, 67, 66, 68, 69, 71, 70, 72, 73, 75, 74, 76, 77, 79, 78}, {1, 3, 2, 0, 5, 7, 6, 4, 9, 11, 10, 8, 13, 15, 14, 12, 17, 19, 18, 16, 21, 23, 22, 20, 25, 27, 26, 24, 29, 31, 30, 28, 33, 35, 34, 32, 37, 39, 38, 36, 41, 43, 42, 40, 45, 47, 46, 44, 49, 51, 50, 48, 53, 55, 54, 52, 57, 59, 58, 56, 61, 63, 62, 60, 65, 67, 66, 64, 69, 71, 70, 68, 73, 75, 74, 72, 77, 79, 78, 76}});
    model.result("pg1").feature("line11").label("\u5c42\u4e2d\u9762");
    model.result("pg1").feature("line11").set("coloring", "uniform");
    model.result("pg1").feature("line11").set("color", "custom");
    model.result("pg1").feature("line11").set("customcolor", new double[]{1, 0, 0});
    model.result("pg1").set("ylabel", "\u539a\u5ea6\u5750\u6807");
    model.result("pg1").set("defaultaxisunits", new String[]{"", "m"});
    model.result("pg1").set("windowtitle", "\u56fe\u5f62");
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").physics("lshell").create("contls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist_src", "stlmat1_z1");
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist", "stlmat1_z2");
    model.component("comp1").physics("lshell").feature("contls1").setIndex("shelllist_lCheck", 1, 1, 0);
    model.component("comp1").physics("lshell").feature("contls1").setIndex("shelllist_lCheck", 1, 10, 0);
    model.component("comp1").physics("lshell").feature("contls1").setIndex("shelllist_lName", 0, 10, 0);
    model.component("comp1").physics("lshell").create("contls2", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls2").set("shelllist_src", "stlmat1_z2");
    model.component("comp1").physics("lshell").feature("contls2").set("shelllist", "stlmat1_z3");
    model.component("comp1").physics("lshell").feature("contls2").setIndex("shelllist_lName", 0, 1, 0);
    model.component("comp1").physics("lshell").feature("contls2").setIndex("shelllist_lName", 0, 8, 0);
    model.component("comp1").physics("lshell").create("contls3", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls3").set("shelllist_src", "stlmat1_z3");
    model.component("comp1").physics("lshell").feature("contls3").set("shelllist", "stlmat1_z4");
    model.component("comp1").physics("lshell").feature("contls3").setIndex("shelllist_lName", 0, 1, 0);
    model.component("comp1").physics("lshell").feature("contls3").setIndex("shelllist_lName", 0, 6, 0);
    model.component("comp1").physics("lshell").create("contls4", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls4").set("shelllist_src", "stlmat1_z4");
    model.component("comp1").physics("lshell").feature("contls4").set("shelllist", "stlmat1_z5");
    model.component("comp1").physics("lshell").feature("contls4").setIndex("shelllist_lName", 0, 1, 0);
    model.component("comp1").physics("lshell").feature("contls4").setIndex("shelllist_lName", 0, 4, 0);
    model.component("comp1").physics("lshell").create("contls5", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls5").set("shelllist_src", "stlmat1_z5");
    model.component("comp1").physics("lshell").feature("contls5").set("shelllist", "stlmat1_z6");
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("lshell").feature("fix1").selection().set(1);
    model.component("comp1").physics("lshell").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("lshell").feature("bndl1").selection().set(19);
    model.component("comp1").physics("lshell").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("lshell").feature("bndl1").set("force", new String[]{"100[kN]", "0", "0"});

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
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
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormax", "2e8");
    model.result("pg2").run();
    model.result().dataset("dset1lshelllshl").set("scale", 5);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").create("lss1", "LayeredMaterialSlice");
    model.result("pg3").feature("lss1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg3").feature("lss1").set("threshold", "manual");
    model.result("pg3").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("lss1").set("colortable", "Prism");
    model.result("pg3").feature("lss1").set("colortabletrans", "none");
    model.result("pg3").feature("lss1").set("colorscalemode", "linear");
    model.result("pg3").feature("lss1").set("locdef", "relative");
    model.result("pg3").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg3").feature("lss1").create("def", "Deform");
    model.result("pg3").feature("lss1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg3").run();
    model.result("pg3").selection().geom("geom1", 2);
    model.result("pg3").selection().geom("geom1", 2);
    model.result("pg3").selection().set(1);
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("lss1").set("locdef", "layermidplanes");
    model.result("pg3").feature("lss1").set("slicedisplacement", "rectangular");
    model.result("pg3").feature("lss1").set("xseparation", "0.15*3");
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result().dataset().create("dset1lshelllshlge", "LayeredMaterial");
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().dataset("dset1lshelllshlge").set("data", "dset1");
    model.result().dataset("dset1lshelllshlge").set("scale", "50*0.012244276946653468");
    model.result().dataset("dset1lshelllshlge")
         .set("defaultPlotIDs", new String[]{"shellGeometry|lshell", "plyAngle|lshell"});
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1lshelllshlge");
    model.result("pg4").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");

    model.nodeGroup().create("dset1lshellggrp", "Results");
    model.nodeGroup("dset1lshellggrp").label("\u51e0\u4f55\u4e0e\u94fa\u5c42 (lshell)");
    model.nodeGroup("dset1lshellggrp").set("type", "plotgroup");
    model.nodeGroup("dset1lshellggrp").label("\u51e0\u4f55\u4e0e\u94fa\u5c42 (lshell)");
    model.nodeGroup("dset1lshellggrp").placeAfter("plotgroup", "pg4");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u539a\u5ea6\u548c\u65b9\u5411 (lshell)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"lshell.d"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").label("\u539a\u5ea6");
    model.result("pg5").create("syss1", "CoordSysSurface");
    model.result("pg5").feature("syss1").set("mode", "matrix");
    model.result("pg5").feature("syss1").set("expr", "lshell.dsdX");
    model.result("pg5").label("\u539a\u5ea6\u548c\u65b9\u5411 (lshell)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1lshelllshlge");
    model.result("pg6").label("\u94fa\u5c42\u89d2\u5ea6 (lshell)");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").set("edgecolor", "white");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"lshell.rot"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("unit", "deg");
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormin", -90);
    model.result("pg6").feature("surf1").set("rangecolormax", 90);
    model.result("pg6").label("\u94fa\u5c42\u89d2\u5ea6 (lshell)");
    model.result().dataset().create("dset1lshelllshlfi", "LayeredMaterial");
    model.result().dataset("dset1lshelllshlfi").label("\u591a\u5c42\u6750\u6599 2 (\u6750\u6599\u65b9\u5411)");
    model.result().dataset("dset1lshelllshlfi").set("data", "dset1");
    model.result().dataset("dset1lshelllshlfi").set("evaluatein", "layermidplanes");
    model.result().dataset("dset1lshelllshlfi").set("scale", "200*0.012244276946653468");
    model.result().dataset("dset1lshelllshlfi")
         .set("defaultPlotIDs", new String[]{"firstPrincipalMaterialDirection|lshell"});
    model.result().dataset("dset1lshelllshlfi").label("\u591a\u5c42\u6750\u6599 2 (\u6750\u6599\u65b9\u5411)");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1lshelllshlfi");
    model.result("pg7").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411 (lshell)");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("edgecolor", "white");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"lshell.rot"});
    model.result("pg7").feature("surf1").set("threshold", "manual");
    model.result("pg7").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").set("unit", "deg");
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("rangecoloractive", true);
    model.result("pg7").feature("surf1").set("rangecolormin", -90);
    model.result("pg7").feature("surf1").set("rangecolormax", 90);
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"lshell.tm11", "lshell.tm12", "lshell.tm13"});
    model.result("pg7").feature("arws1").set("placement", "elements");
    model.result("pg7").feature("arws1").set("arrowtype", "cone");
    model.result("pg7").feature("arws1").set("color", "white");
    model.result("pg7").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411 (lshell)");

    model.nodeGroup("dset1lshellggrp").add("plotgroup", "pg4");
    model.nodeGroup("dset1lshellggrp").add("plotgroup", "pg5");
    model.nodeGroup("dset1lshellggrp").add("plotgroup", "pg6");
    model.nodeGroup("dset1lshellggrp").add("plotgroup", "pg7");

    model.result("pg4").run();
    model.result().dataset("dset1lshelllshlge").set("scale", 5);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg7").run();
    model.result().dataset("dset1lshelllshlfi").set("scale", 5);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1lshelllshl");
    model.result("pg8").label("\u8fb9\u754c\u8f7d\u8377 (lshell)");
    model.result("pg8").set("showlegends", true);
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg8").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg8").feature("surf1").set("coloring", "uniform");
    model.result("pg8").feature("surf1").set("color", "gray");
    model.result("pg8").feature("surf1").create("def", "Deform");
    model.result("pg8").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg8").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg8").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg8").feature("surf1").feature("def").set("scale", 0);
    model.result("pg8").feature("surf1").create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg8").feature("surf1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6);
    model.result("pg8").feature("surf1").create("tran1", "Transparency");
    model.result("pg8").feature("surf1").feature("tran1").set("transparency", 0.8);

    return model;
  }

  public static Model run6(Model model) {
    model.result("pg8").create("arwl1", "ArrowLine");
    model.result("pg8").feature("arwl1")
         .set("expr", new String[]{"lshell.bndl1.fax", "lshell.bndl1.fay", "lshell.bndl1.faz"});
    model.result("pg8").feature("arwl1").set("placement", "elements");
    model.result("pg8").feature("arwl1").set("arrowbase", "tail");
    model.result("pg8").feature("arwl1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg8").feature("arwl1").set("inheritplot", "none");
    model.result("pg8").feature("arwl1").create("col", "Color");
    model.result("pg8").feature("arwl1").feature("col").set("colortable", "Rainbow");
    model.result("pg8").feature("arwl1").feature("col").set("colortabletrans", "none");
    model.result("pg8").feature("arwl1").feature("col").set("colorscalemode", "linear");
    model.result("pg8").feature("arwl1").feature("col").set("colordata", "arrowlength");
    model.result("pg8").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg8").feature("arwl1").feature("col").set("topcolor", "red");
    model.result("pg8").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg8").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg8").feature("arwl1").set("color", "red");
    model.result("pg8").feature("arwl1").create("def", "Deform");
    model.result("pg8").feature("arwl1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg8").feature("arwl1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg8").feature("arwl1").feature("def").set("scaleactive", true);
    model.result("pg8").feature("arwl1").feature("def").set("scale", 0);
    model.result("pg8").label("\u8fb9\u754c\u8f7d\u8377 (lshell)");

    model.nodeGroup().create("dset1lshelllgrp", "Results");
    model.nodeGroup("dset1lshelllgrp").label("\u5916\u52a0\u8f7d\u8377 (lshell)");
    model.nodeGroup("dset1lshelllgrp").set("type", "plotgroup");
    model.nodeGroup("dset1lshelllgrp").label("\u5916\u52a0\u8f7d\u8377 (lshell)");
    model.nodeGroup("dset1lshelllgrp").placeAfter("plotgroup", "pg8");
    model.nodeGroup("dset1lshelllgrp").add("plotgroup", "pg8");

    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg2").run();

    model.title("\u590d\u5408\u677f\u7684\u524a\u5c42");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u590d\u5408\u677f\u7684\u524a\u5c42\u8fdb\u884c\u5efa\u6a21\u3002\u8fd9\u79cd\u590d\u5408\u677f\u7531\u4e09\u4e2a\u4e3b\u8981\u6bb5\u7ec4\u6210\uff1a\u539a\u6bb5\u3001\u9525\u5f62\u6bb5\u548c\u8584\u6bb5\u3002\u539a\u6bb5\u94fa\u5c42\u53c8\u5206\u4e3a\u82af\u5c42\u3001\u4e0a\u4e0b\u5e26\u5c42\u548c\u8131\u5c42\u3002\u677f\u7684\u539a\u6bb5\u5305\u542b 16 \u4e2a\u94fa\u5c42\uff08\u5177\u6709\u5bf9\u79f0\u89d2\u94fa\u8bbe\u5c42\uff09\uff0c\u5176\u4e2d 8 \u4e2a\u94fa\u5c42\u4ee5\u6307\u5b9a\u7684\u4ea4\u9519\u8ddd\u79bb\u9010\u6e10\u843d\u5165\u9525\u5f62\u6bb5\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u5177\u6709\u6a2a\u5411\u5404\u5411\u540c\u6027\u6750\u6599\u5c5e\u6027\u7684\u78b3-\u73af\u6c27\u6811\u8102\u6750\u6599\u4f5c\u4e3a\u94fa\u5c42\u6750\u6599\uff0c\u5e76\u5728\u8131\u5c42\u9644\u8fd1\u7684\u51f9\u69fd\u4e2d\u4f7f\u7528\u5177\u6709\u5404\u5411\u540c\u6027\u6750\u6599\u5c5e\u6027\u7684\u73af\u6c27\u6811\u8102\u6750\u6599\u3002\u5176\u4e2d\u7ed3\u5408\u4f7f\u7528\u57fa\u4e8e\u5206\u5c42\u7406\u8bba\u7684\u65b9\u6cd5\u4e0e\u5806\u53e0\u533a\u57df\u5efa\u6a21\u6765\u8be6\u7ec6\u8868\u793a\u524a\u5c42\u573a\u666f\uff0c\u5e76\u901a\u8fc7\u6267\u884c\u7a33\u6001\u5206\u6790\u6765\u8ba1\u7b97\u5404\u4e2a\u590d\u5408\u677f\u6bb5\u5728\u53d7\u5230\u5916\u52a0\u8f7d\u8377\u65f6\uff0c\u4e0d\u540c\u94fa\u5c42\u4e2d\u7684\u5e94\u529b\u5206\u5e03\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("ply_drop_off_in_a_composite_panel.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    run6(model);
  }

}
