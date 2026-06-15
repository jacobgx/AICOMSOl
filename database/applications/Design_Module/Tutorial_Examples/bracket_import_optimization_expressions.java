/*
 * bracket_import_optimization_expressions.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:47 by COMSOL 6.3.0.290. */
public class bracket_import_optimization_expressions {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Design_Module\\Tutorial_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("conrad", "1");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("IXCmp", "7.1e-4[kg*m^2]", "Component mass moment of inertia around X axis");
    model.param().set("IYZCmp", "9.3e-4[kg*m^2]", "Component mass moment of inertia around Y and Z axes");
    model.param().set("mCmp", "2.4[kg]", "Component mass");
    model.param().set("maxStressLimit", "80[MPa]", "Maximum allowed equivalent stress under 4g acceleration");
    model.param().set("minFreq", "60[Hz]", "Lowest allowed natural frequency");
    model.param().set("THK", "4[mm]", "Plate thickness");
    model.param().set("LX", "65[mm]", "Bracket length");
    model.param().set("LZ", "70[mm]", "Bracket height");
    model.param().set("dCmp", "40[mm]", "Diameter of component");
    model.param().set("bDia", "8[mm]", "Diameter of mounting bolts");
    model.param().set("LY", "dCmp+4*bDia", "Bracket width");
    model.param().set("ZC", "21[mm]", "Z coordinate of the central hole");
    model.param().set("ZO", "37[mm]", "Z coordinate of the outer hole");
    model.param().set("YO", "LY/2-ROorig-YOOorig", "Y coordinate of the outer hole");
    model.param().set("RCorig", "4[mm]", "Original radius of central hole");
    model.param().set("ZCOorig", "5[mm]", "Original distance from bend to bottom of central hole");
    model.param().set("ROorig", "5[mm]", "Original radius of outer holes");
    model.param().set("ZOOorig", "20[mm]", "Original distance from bend to bottom of outer hole");
    model.param().set("YOOorig", "8[mm]", "Original distance from edge to outer hole");
    model.param().set("RC", "4[mm]", "Radius of central hole");
    model.param().set("ZCO", "5[mm]", "Distance from bend to bottom of central hole");
    model.param().set("RO", "5[mm]", "Radius of outer holes");
    model.param().set("ZOO", "20[mm]", "Distance from bend to bottom of outer hole");
    model.param().set("YOO", "8[mm]", "Distance from edge to outer hole");
    model.param().set("RCoff", "RCorig*(scaleRC-1)", "Offset distance central hole");
    model.param().set("ZCOdisp", "ZCO+RCoff-ZCOorig", "Z displacement central hole");
    model.param().set("ROoff", "RO-ROorig", "Offset distance outer holes");
    model.param().set("ZOOdisp", "ZOO+ROoff-ZOOorig", "Z displacement of outer holes");
    model.param().set("YOOdisp", "YOO+ROoff-YOOorig", "Y displacement of outer hole");
    model.param().set("scaleRC", "RC/RCorig", "Scaling factor for central hole");
    model.param()
         .set("d_C_Cmp_Orig", "LZ-dCmp/2-ZC-RCorig", "Original distance between edges of central hole and component");
    model.param().set("d_O_O_Orig", "LY-2*YOOorig-2*ROorig", "Original Distance between outer holes");
    model.param()
         .set("d_C_Cmp", "d_C_Cmp_Orig-RCoff-ZCOdisp", "Distance between edges of central hole and component");
    model.param().set("d_O_O", "d_O_O_Orig-2*(ROoff+YOOdisp)", "Distance between outer holes");
    model.param()
         .set("d_O_Cmp", "sqrt((YO-YOOdisp)^2+(LZ-ZO-ZOOdisp)^2)-RO-ZOOorig-ROoff", "Distance between edges of outer hole and component");
    model.param()
         .set("d_O_C", "sqrt((YO-YOOdisp)^2+(ZO-ZC-ZCOdisp+ZOOdisp)^2)-RO-RC-RCoff-ROoff", "Distance between edges of outer and central holes");
    model.param("default").paramCase().create("case1");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "bracket_import_optimization_geom.step");
    model.component("comp1").geom("geom1").feature("imp1").set("unit", "source");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);
    model.component("comp1").view("view1").camera().set("projection", "perspective");

    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp1").selection("circedge").set("imp1", 91);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp2").selection("circedge").set("imp1", 99);
    model.component("comp1").geom("geom1").feature("wp2").set("reverse", true);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "circleperpendicular");
    model.component("comp1").geom("geom1").feature("wp3").selection("circedge").set("imp1", 81);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("tf1", "TransformFaces");
    model.component("comp1").geom("geom1").feature("tf1").selection("face").set("imp1", 31, 32);
    model.component("comp1").geom("geom1").feature("tf1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("tf1").set("factor", "scaleRC");
    model.component("comp1").geom("geom1").feature("tf1").set("displ", new String[]{"ZCOdisp", "0", "0"});
    model.component("comp1").geom("geom1").run("tf1");
    model.component("comp1").geom("geom1").run("tf1");
    model.component("comp1").geom("geom1").create("off1", "OffsetFaces");
    model.component("comp1").geom("geom1").feature("off1").selection("face").set("tf1", 28, 29, 34, 35);
    model.component("comp1").geom("geom1").feature("off1").set("distance", "ROoff");
    model.component("comp1").geom("geom1").feature("off1").set("reverse", true);
    model.component("comp1").geom("geom1").run("off1");
    model.component("comp1").geom("geom1").run("off1");
    model.component("comp1").geom("geom1").create("tf2", "TransformFaces");
    model.component("comp1").geom("geom1").feature("tf2").selection("face").set("off1", 34, 35);
    model.component("comp1").geom("geom1").feature("tf2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("tf2").set("displ", new String[]{"ZOOdisp", "0", "YOOdisp"});
    model.component("comp1").geom("geom1").run("tf2");
    model.component("comp1").geom("geom1").run("tf2");
    model.component("comp1").geom("geom1").create("tf3", "TransformFaces");
    model.component("comp1").geom("geom1").feature("tf3").selection("face").set("tf2", 28, 29);
    model.component("comp1").geom("geom1").feature("tf3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("tf3").set("displ", new String[]{"ZOOdisp", "0", "YOOdisp"});
    model.component("comp1").geom("geom1").run("tf3");
    model.component("comp1").geom("geom1").run("tf3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp4").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp4").selection("offsetvertex").set("tf3", 18);
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("tf3");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "LX-THK");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "LX");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "7[mm]");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "22[mm]");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "13[mm]");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "22[mm]");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "LX-THK");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "LX");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", "LY-22[mm]");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", "LY-7[mm]");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "13[mm]");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "22[mm]");
    model.component("comp1").geom("geom1").feature("boxsel2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", "LX-2*THK");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "LX");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymin", "LY/2-8[mm]");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "LY/2+8[mm]");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "0[mm]");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", "12[mm]");
    model.component("comp1").geom("geom1").feature("boxsel3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").named("csel1");

    model.component("comp1").view("view1").camera().set("zoomanglefull", 20);
    model.component("comp1").view("view1").camera().setIndex("position", 555, 0);
    model.component("comp1").view("view1").camera().setIndex("position", -85, 1);
    model.component("comp1").view("view1").camera().set("position", new int[]{555, -85, -290});
    model.component("comp1").view("view1").camera().setIndex("target", 32, 0);
    model.component("comp1").view("view1").camera().set("target", new int[]{32, 36, 35});
    model.component("comp1").view("view1").camera().setIndex("up", 0.5489, 0);
    model.component("comp1").view("view1").camera().setIndex("up", 0.16734, 1);
    model.component("comp1").view("view1").camera().setIndex("up", 0.8189529, 2);
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", 32, 0);
    model.component("comp1").view("view1").camera().set("rotationpoint", new int[]{32, 36, 35});
    model.component("comp1").view("view1").camera().setIndex("viewoffset", -0.0034896, 0);
    model.component("comp1").view("view1").camera().set("viewoffset", new double[]{-0.0034896, 0.020231444});

    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 19);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 100);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{10, 36, 70});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("ige2", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige2").selection("input").set("ige1", 16, 20);
    model.component("comp1").geom("geom1").run("ige2");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection("sel1").set("color", "custom");
    model.component("comp1").selection("sel1")
         .set("customcolor", new double[]{0.5411764979362488, 0.48235294222831726, 0.3960784375667572});

    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.component("comp1").geom("geom1").feature().remove("cyl1");
    model.component("comp1").geom("geom1").feature().remove("ige2");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection().remove("sel1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup().create("Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1")
         .set("expr", "200[GPa]*(1 - 3.34e-4[1/K]*(T - 293.15[K]))");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").set("fununit", "Pa");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1")
         .set("plotargs", new String[][]{{"T", "0", "1500"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2")
         .set("expr", "0.3*(1 + 1e-4[1/K]*(T - 293.15[K]))");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2")
         .set("plotargs", new String[][]{{"T", "0", "1500"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("lighting", "cooktorrance");
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("anisotropy", 0);
    model.component("comp1").material("mat1").set("flipanisotropy", false);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("specular", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noisecolor", "custom");
    model.component("comp1").material("mat1").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp1").material("mat1").set("noisescale", 0);
    model.component("comp1").material("mat1").set("noise", "off");
    model.component("comp1").material("mat1").set("noisefreq", 1);
    model.component("comp1").material("mat1").set("normalnoisebrush", "0");
    model.component("comp1").material("mat1").set("normalnoisetype", "0");
    model.component("comp1").material("mat1").set("alpha", 1);
    model.component("comp1").material("mat1").set("anisotropyaxis", new double[]{0, 0, 1});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(6, 7, 8, 9, 10, 11);
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("solid").feature("rig1").selection().set(28, 29, 38, 39);
    model.component("comp1").physics("solid").feature("rig1").set("CenterOfRotationType", "userDefined");
    model.component("comp1").physics("solid").feature("rig1").set("xc", new String[]{"LX-THK/2", "LY/2", "LZ"});
    model.component("comp1").physics("solid").feature("rig1").create("rmm1", "RigidBodyMassInertia", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rmm1").set("mt", "mCmp");
    model.component("comp1").physics("solid").feature("rig1").feature("rmm1")
         .set("mi", new String[]{"IXCmp", "0", "0", "0", "IYZCmp", "0", "0", "0", "IYZCmp"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 15, 17, 19, 21, 24, 26);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(15, 17, 19, 21, 24, 26);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(15, 17, 19, 21, 24, 26);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1, 2);

    model.study("std1").label("\u7279\u5f81\u9891\u7387\u7814\u7a76");

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("defaultPlotID", "modeShape");
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("defaultPlotID", "eigenFrequenciesTable");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7279\u5f81\u9891\u7387\u7814\u7a76)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "freq*2*pi", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup().create("std1mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std1mpf1").set("data", "dset1");
    model.result().evaluationGroup("std1mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7279\u5f81\u9891\u7387\u7814\u7a76)");
    model.result().evaluationGroup("std1mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);

    model.sol("sol1").runAll();

    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup("std1mpf1").run();
    model.result("pg1").run();

    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 3);
    model.component("comp1").physics("solid").feature("bl1").label("\u652f\u67b6\u4e0a\u7684\u4f53\u8f7d\u8377 4g");
    model.component("comp1").physics("solid").feature("bl1").selection().all();
    model.component("comp1").physics("solid").feature("bl1")
         .set("FperVol", new String[]{"4*g_const*solid.rho", "4*g_const*solid.rho", "4*g_const*solid.rho"});
    model.component("comp1").physics("solid").feature("rig1").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rf1")
         .label("\u5728\u5b89\u88c5\u7684\u7ec4\u4ef6\u4e0a\u65bd\u52a0 4g \u7684\u529b");
    model.component("comp1").physics("solid").feature("rig1").feature("rf1")
         .set("Ft", new String[]{"4*g_const*mCmp", "4*g_const*mCmp", "4*g_const*mCmp"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7a33\u6001\u7814\u7a76");

    model.sol().create("sol2");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "auto");

    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("defaultPlotID", "stress");
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.mises"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.sol("sol2").runAll();

    model.result("pg2").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5e94\u529b\u4f18\u5316\u57df");
    model.component("comp1").selection("sel1").set(2);

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("probename", "mass");
    model.component("comp1").probe("dom1").set("type", "integral");
    model.component("comp1").probe("dom1").set("expr", "solid.rho");
    model.component("comp1").probe("dom1").set("descr", "\u5bc6\u5ea6");
    model.component("comp1").probe().create("dom2", "Domain");
    model.component("comp1").probe("dom2").set("intsurface", true);
    model.component("comp1").probe("dom2").set("intvolume", true);
    model.component("comp1").probe("dom2").set("probename", "maxStress");
    model.component("comp1").probe("dom2").set("type", "maximum");
    model.component("comp1").probe("dom2").selection().named("sel1");
    model.component("comp1").probe("dom2").set("expr", "solid.mises");
    model.component("comp1").probe("dom2").set("descr", "von Mises \u5e94\u529b");

    model.result("pg2").run();
    model.result("pg2").label("\u4f18\u5316\u533a\u4e2d\u7684\u5e94\u529b");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("unit", "MPa");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature().remove("def");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().named("sel1");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("mrkr1", "Marker");
    model.result("pg2").run();

    model.study().create("std3");
    model.study("std3").label("\u4f18\u5316\u7814\u7a76");
    model.study("std3").create("opt", "Optimization");
    model.study("std3").create("ref", "StudyReference");
    model.study("std3").feature("ref").label("\u7279\u5f81\u9891\u7387");
    model.study("std3").feature("ref").set("studyref", "std1");
    model.study("std3").create("ref2", "StudyReference");
    model.study("std3").feature("ref2").label("\u7a33\u6001");
    model.study("std3").feature("ref2").set("studyref", "std2");
    model.study("std3").feature("opt").set("optsolver", "cobyla");
    model.study("std3").feature("opt").set("err", false);
    model.study("std3").feature("opt").set("optobj", new String[]{"comp1.mass"});
    model.study("std3").feature("opt").set("descr", new String[]{"\u57df\u63a2\u9488 1"});
    model.study("std3").feature("opt").setIndex("descr", "\u652f\u67b6\u8d28\u91cf", 0);
    model.study("std3").feature("opt").setIndex("optobjEvaluateFor", "ref2", 0);
    model.study("std3").feature("opt").set("objectivesolution", "first");
    model.study("std3").feature("opt").set("pname", new String[]{"RC", "ZCO", "RO", "YOO", "ZOO"});
    model.study("std3").feature("opt").set("initval", new String[]{"4[mm]", "5[mm]", "5[mm]", "8[mm]", "20[mm]"});
    model.study("std3").feature("opt").set("scale", new String[]{"12[mm]", "22[mm]", "12[mm]", "26[mm]", "22[mm]"});
    model.study("std3").feature("opt").set("lbound", new String[]{"3[mm]", "1[mm]", "3[mm]", "3[mm]", "8[mm]"});
    model.study("std3").feature("opt").set("ubound", new String[]{"15[mm]", "23[mm]", "15[mm]", "29[mm]", "30[mm]"});
    model.study("std3").feature("opt").setIndex("constraintExpression", "real(freq)", 0);
    model.study("std3").feature("opt").setIndex("constraintLbound", "minFreq", 0);
    model.study("std3").feature("opt").setIndex("constraintExpression", "comp1.maxStress/maxStressLimit", 1);
    model.study("std3").feature("opt").setIndex("constraintUbound", 1, 1);
    model.study("std3").feature("opt").setIndex("constraintEvaluateFor", "ref2", 1);
    model.study("std3").feature("opt").setIndex("constraintExpression", "d_O_Cmp", 2);
    model.study("std3").feature("opt").setIndex("constraintLbound", "3[mm]", 2);
    model.study("std3").feature("opt").setIndex("constraintExpression", "d_C_Cmp", 3);
    model.study("std3").feature("opt").setIndex("constraintLbound", "3[mm]", 3);
    model.study("std3").feature("opt").setIndex("constraintExpression", "d_O_C", 4);
    model.study("std3").feature("opt").setIndex("constraintLbound", "3[mm]", 4);
    model.study("std3").feature("opt").setIndex("constraintExpression", "d_O_O", 5);
    model.study("std3").feature("opt").setIndex("constraintLbound", "3[mm]", 5);
    model.study("std3").feature("opt").set("plot", true);
    model.study("std3").feature("opt").set("plotgroup", "pg2");

    model.sol().create("sol3");
    model.sol("sol3").study("std3");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "auto");

    model.sol("sol1").feature().remove("e1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "auto");

    model.sol("sol2").feature().remove("s1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol3").create("copy1", "CopySolution");
    model.sol("sol3").feature("copy1").set("sol", "sol2");
    model.sol("sol3").attach("std3");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "auto");

    model.sol("sol1").feature().remove("e1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "auto");

    model.sol("sol2").feature().remove("s1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");

    return model;
  }

  public static Model run3(Model model) {
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.batch().create("o1", "Optimization");
    model.batch("o1").study("std3");
    model.batch("p1").study("std3");
    model.batch("o1").attach("std3");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").attach("std3");
    model.batch("p1").set("optimization", "o1");
    model.batch("p1").set("err", "on");
    model.batch("p1").set("control", "opt");
    model.batch().create("p2", "Parametric");
    model.batch("p2").study("std3");
    model.batch("p2").create("so1", "Solutionseq");
    model.batch("p2").feature("so1").set("seq", "sol2");
    model.batch("p2").feature("so1").set("store", "on");
    model.batch("p2").feature("so1").set("clear", "on");
    model.batch("p2").feature("so1").set("psol", "none");
    model.batch("p2").attach("std3");
    model.batch("p2").set("optimization", "o1");
    model.batch("p2").set("err", "on");
    model.batch("p2").set("control", "opt");
    model.batch().create("p3", "Parametric");
    model.batch("p3").study("std3");
    model.batch("p3").create("so1", "Solutionseq");
    model.batch("p3").feature("so1").set("seq", "sol3");
    model.batch("p3").feature("so1").set("store", "on");
    model.batch("p3").feature("so1").set("clear", "on");
    model.batch("p3").feature("so1").set("psol", "none");
    model.batch("p3").attach("std3");
    model.batch("p3").set("optimization", "o1");
    model.batch("p3").set("err", "on");
    model.batch("p3").set("control", "opt");
    model.batch("o1").set("parametricjobs", new String[]{"p1", "p2", "p3"});

    model.sol("sol3").study("std3");
    model.sol("sol3").feature().remove("copy1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "auto");

    model.sol("sol1").feature().remove("e1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "auto");

    model.sol("sol2").feature().remove("s1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol3").create("copy1", "CopySolution");
    model.sol("sol3").feature("copy1").set("sol", "sol2");
    model.sol("sol3").attach("std3");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "auto");

    model.sol("sol1").feature().remove("e1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol1").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "auto");

    model.sol("sol2").feature().remove("s1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_disp").set("scaleval", "0.0011967959824919006");
    model.sol("sol2").feature("v1").feature("comp1_solid_rig_rot").set("scaleval", "0.01");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11967959824919006");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.batch("o1").active(true);
    model.batch("o1").attach("std3");
    model.batch("p1").feature().remove("so1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").attach("std3");
    model.batch("p1").set("optimization", "o1");
    model.batch("p1").set("err", "on");
    model.batch("p1").set("control", "opt");
    model.batch("p2").feature().remove("so1");
    model.batch("p2").create("so1", "Solutionseq");
    model.batch("p2").feature("so1").set("seq", "sol2");
    model.batch("p2").feature("so1").set("store", "on");
    model.batch("p2").feature("so1").set("clear", "on");
    model.batch("p2").feature("so1").set("psol", "none");
    model.batch("p2").attach("std3");
    model.batch("p2").set("optimization", "o1");
    model.batch("p2").set("err", "on");
    model.batch("p2").set("control", "opt");
    model.batch("p3").feature().remove("so1");
    model.batch("p3").create("so1", "Solutionseq");
    model.batch("p3").feature("so1").set("seq", "sol3");
    model.batch("p3").feature("so1").set("store", "on");
    model.batch("p3").feature("so1").set("clear", "on");
    model.batch("p3").feature("so1").set("psol", "none");
    model.batch("p3").attach("std3");
    model.batch("p3").set("optimization", "o1");
    model.batch("p3").set("err", "on");
    model.batch("p3").set("control", "opt");
    model.batch("o1").set("parametricjobs", new String[]{"p1", "p2", "p3"});

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p3").feature("so1").set("psol", "sol4");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");

    model.batch("o1").run("compute");

    model.component("comp1").geom("geom1").run();

    model.study("std3").feature("opt").set("probewindow", "");

    model.result("pg2").run();
    model.result("pg2").run();

    model.param("default").paramCase().create("case2");
    model.param("default").paramCase("case2").set("RC", "0.0040557[m]");
    model.param("default").paramCase("case2").set("ZCO", "0.0015301[m]");
    model.param("default").paramCase("case2").set("YOO", "0.0070196[m]");
    model.param("default").paramCase("case2").set("ZOO", "0.010909[m]");
    model.param("default").paramCase("case2").set("RO", "0.011078[m]");
    model.param("default").setFromCase("case2");

    model.component("comp1").geom("geom1").run("ige1");

    model
         .title("\u4f18\u5316\u5bfc\u5165\u7684\u652f\u67b6\u51e0\u4f55 - \u4f7f\u7528\u53c2\u6570\u8868\u8fbe\u5f0f");

    model
         .description("\u5728\u672c\u6559\u7a0b\u4e2d\uff0c\u60a8\u5c06\u4f7f\u7528\u201c\u504f\u79fb\u9762\u201d\u548c\u201c\u53d8\u6362\u9762\u201d\u64cd\u4f5c\u65b9\u6cd5\u6765\u91cd\u65b0\u53c2\u6570\u5316\u4ece STEP \u6587\u4ef6\u5bfc\u5165\u7684\u652f\u67b6\u51e0\u4f55\u4e2d\u5df2\u7ed8\u5236\u7684\u5b54\u3002\u652f\u67b6\u7684\u8d28\u91cf\u5728\u4ee5\u4e0b\u9650\u5236\u4e0b\u5b9e\u73b0\u6700\u5c0f\u5316\uff1a\u6700\u4f4e\u56fa\u6709\u9891\u7387\u548c\u9759\u8f7d\u8377\u5de5\u51b5\u4e0b\u7684\u6700\u5927\u5e94\u529b\u3002\n\n\u5728\u4f18\u5316\u8fc7\u7a0b\u4e2d\uff0c\u901a\u8fc7\u5f3a\u5236\u6267\u884c\u4ee5\u53c2\u6570\u8868\u8fbe\u5f0f\u5f62\u5f0f\u8868\u793a\u7684\u51e0\u4f55\u7ea6\u675f\u6765\u4fdd\u6301\u51e0\u4f55\u7684\u8bbe\u8ba1\u610f\u56fe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("bracket_import_optimization_expressions.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
