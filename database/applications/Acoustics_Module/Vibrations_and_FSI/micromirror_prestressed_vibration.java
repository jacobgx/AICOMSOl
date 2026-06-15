/*
 * micromirror_prestressed_vibration.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class micromirror_prestressed_vibration {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Vibrations_and_FSI");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "IncludeInertia");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ta", true);
    model.study("std1").feature("stat").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("shift", "1[Hz]");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("geometricNonlinearity", true);
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std1").feature("eig").setSolveFor("/physics/ht", true);
    model.study("std1").feature("eig").setSolveFor("/physics/ta", true);
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/te1", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").multiphysics().create("atb1", "AcousticThermoacousticBoundary", 2);
    model.component("comp1").multiphysics().create("tatb1", "ThermoviscousAcousticThermoelasticityBoundary", 2);

    model.param().set("sigma_pre", "3[GPa]");
    model.param().descr("sigma_pre", "\u521d\u59cb\u6b63\u5e94\u529b");
    model.param().set("fc", "13400[Hz]");
    model.param().descr("fc", "\u5178\u578b\u9891\u7387");
    model.param().set("dvisc", "0.22[mm]*sqrt(100[Hz]/fc)");
    model.param().descr("dvisc", "fc \u7684\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", 0.2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("pos", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cha1").selection("pointinsketch")
         .set("sq2", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cha1").set("dist", 0.1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cha1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{0.9, 0.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{0.2, 1.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq3", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq3").set("size", 0.1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq3").set("pos", new double[]{1, 1.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input")
         .set("cha1", "r1", "sq3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", "range(90,90,360)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("pos", new double[]{0.5, 0.5});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.02, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.04, 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("ext1", 120, 17, 26, 111);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.02, 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1.45, 1.45, 0.06});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.225, -0.225, 0});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", -0.02, 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.5);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.5, 0.5, -0.52});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 0.06);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{0.5, 0.5, -0.02});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"1.65", "1.65", ".5"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{-0.325, -0.325, 0});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", -0.02, 2);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 2);
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new double[]{0.5, 0.5, 0.04});
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", 0.04);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("sph1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u7a7a\u6c14 TA");
    model.component("comp1").selection("sel1").set(5, 6);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7a7a\u6c14 ACPR");
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u57fa\u677f");
    model.component("comp1").selection("sel3").set(1, 2, 4);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u56fa\u4f53");
    model.component("comp1").selection("sel4")
         .set(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u56fa\u4f53\u94dd");
    model.component("comp1").selection("sel5")
         .set(9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 23, 24, 29, 30, 31, 32, 33, 34, 35, 36);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u8131\u6c27\u94a2");
    model.component("comp1").selection("sel6").set(7, 8, 19, 20, 21, 22, 25, 26, 27, 28);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u56fa\u4f53-TA");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7")
         .set(24, 25, 26, 27, 28, 30, 31, 33, 34, 37, 38, 39, 41, 44, 47, 48, 49, 50, 51, 52, 54, 55, 57, 58, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 75, 76, 78, 80, 81, 82, 84, 85, 87, 89, 90, 91, 92, 94, 95, 98, 99, 100, 101, 102, 103, 104, 105, 106, 108, 109, 111, 113, 114, 115, 123, 124, 126, 128, 129, 130, 133, 134, 135, 136, 137, 138, 140, 141, 143, 145, 146, 147, 148, 149, 152, 155, 157, 158, 159, 160, 161, 162, 164, 165, 167, 168, 170, 171, 172, 174, 175, 178, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7a7a\u6c14");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat2").label("Structural steel");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Air");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").materialType("nonSolid");
    model.component("comp1").material("mat1").selection().named("sel5");
    model.component("comp1").material("mat2").selection().named("sel6");
    model.component("comp1").material("mat3").selection().named("uni1");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").selection().set(5, 6);
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");

    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1").selection().set(10, 18, 24, 36);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("Sil", new String[]{"sigma_pre", "0", "0", "0", "sigma_pre", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("lemm1").create("iss2", "InitialStressandStrain", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss2").selection().set(9, 17, 23, 35);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss2")
         .set("Sil", new String[]{"-sigma_pre", "0", "0", "0", "-sigma_pre", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(40, 53, 150, 163);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(85);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "linper(1)"});
    model.component("comp1").physics("solid").selection().named("sel4");
    model.component("comp1").physics("ht").selection().named("sel4");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(40, 53, 150, 163);
    model.component("comp1").physics("ta").selection().set(5, 6);
    model.component("comp1").physics("ta").create("wall2", "Wall", 2);
    model.component("comp1").physics("ta").feature("wall2").selection().set(15);
    model.component("comp1").physics("ta").feature("wall2").set("MechanicalCondition", "Slip");
    model.component("comp1").physics("ta").feature("wall2").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("acpr").selection().named("sel2");
    model.component("comp1").physics("acpr").create("pmb1", "PerfectlyMatchedBoundary", 2);
    model.component("comp1").physics("acpr").feature("pmb1").selection().set(7, 9, 118, 119);
    model.component("comp1").physics("acpr").feature("pmb1").set("directionType", "normal");

    model.component("comp1").multiphysics("te1").set("IncludeMechanicalLosses", true);

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").multiphysics("atb1").selection().all();
    model.component("comp1").multiphysics("tatb1").selection().named("sel7");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(30, 37, 47, 60, 80, 89, 98, 113, 128, 145, 157, 170, 178);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(45, 115, 129, 134, 160, 173, 219, 304);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 12);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection()
         .set(56, 64, 74, 75, 95, 159, 199, 227, 243, 261, 288, 298);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(37, 152, 194, 309);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 6);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set();
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(19, 20, 23, 193);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.04);
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("hmax", 0.8);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(5, 6);
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "none");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel7");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhtot");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhtot", "dvisc");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.01);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7ed3\u6784\u6a21\u5f0f\uff08\u65e0\u635f\uff09");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/frame/spatial1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", false);
    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st1", "su1");

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 4);
    model.study("std1").feature("eig").set("shift", "8000[Hz]");
    model.study("std1").feature("eig").set("eigwhich", "lr");
    model.study("std1").feature("eig").setSolveFor("/physics/ht", false);
    model.study("std1").feature("eig").setSolveFor("/physics/ta", false);
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", false);
    model.study("std1").feature("eig").setSolveFor("/frame/spatial1", false);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/te1", false);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/atb1", false);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/tatb1", false);
    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st2", "e1");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ta", true);
    model.study("std2").feature("stat").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/atb1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/tatb1", true);
    model.study("std2").create("frlin", "Frequencylinearized");
    model.study("std2").feature("frlin").set("outputmap", new String[]{});
    model.study("std2").feature("frlin").set("ngenAUX", "1");
    model.study("std2").feature("frlin").set("goalngenAUX", "1");
    model.study("std2").feature("frlin").set("ngenAUX", "1");
    model.study("std2").feature("frlin").set("goalngenAUX", "1");
    model.study("std2").feature("frlin").setSolveFor("/physics/solid", true);
    model.study("std2").feature("frlin").setSolveFor("/physics/ht", true);
    model.study("std2").feature("frlin").setSolveFor("/physics/ta", true);
    model.study("std2").feature("frlin").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("frlin").setSolveFor("/multiphysics/te1", true);
    model.study("std2").feature("frlin").setSolveFor("/multiphysics/atb1", true);
    model.study("std2").feature("frlin").setSolveFor("/multiphysics/tatb1", true);
    model.study("std2")
         .label("\u7814\u7a76 2 - \u9891\u7387\u54cd\u5e94\uff1a\u5b8c\u6574\u6a21\u578b (ta-ht-solid)");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "sigma_pre", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "sigma_pre", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(1,1,3)", 0);
    model.study("std2").feature("stat").setIndex("punit", "GPa", 0);
    model.study("std2").createAutoSequences("sol");

    model.sol("sol3").runFromTo("st1", "su1");

    model.study("std2").feature("frlin").set("plist", "range(50,50,600) range(13150,25,13500)");
    model.study("std2").feature("frlin").set("uselinpsol", true);
    model.study("std2").feature("frlin").set("linpsolnum", 3);
    model.study("std2").feature("frlin").setSolveFor("/frame/spatial1", false);
    model.study("std2").feature("frlin").set("usesol", true);
    model.study("std2").feature("frlin").set("notsoluse", "sol4");
    model.study("std2").feature("frlin").set("notsolnum", 3);
    model.study("std2").createAutoSequences("sol");

    model.sol("sol3").runFromTo("st2", "s2");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").set("outputmap", new String[]{});
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ta", true);
    model.study("std3").feature("stat").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/atb1", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/tatb1", true);
    model.study("std3").create("frlin", "Frequencylinearized");
    model.study("std3").feature("frlin").set("outputmap", new String[]{});
    model.study("std3").feature("frlin").set("ngenAUX", "1");
    model.study("std3").feature("frlin").set("goalngenAUX", "1");
    model.study("std3").feature("frlin").set("ngenAUX", "1");
    model.study("std3").feature("frlin").set("goalngenAUX", "1");
    model.study("std3").feature("frlin").setSolveFor("/physics/solid", true);
    model.study("std3").feature("frlin").setSolveFor("/physics/ht", true);
    model.study("std3").feature("frlin").setSolveFor("/physics/ta", true);
    model.study("std3").feature("frlin").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("frlin").setSolveFor("/multiphysics/te1", true);
    model.study("std3").feature("frlin").setSolveFor("/multiphysics/atb1", true);
    model.study("std3").feature("frlin").setSolveFor("/multiphysics/tatb1", true);
    model.study("std3").label("\u7814\u7a76 3 - \u9891\u7387\u54cd\u5e94\uff1a\u56fa\u4f53\u635f\u8017 (ht-solid)");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").setSolveFor("/frame/spatial1", false);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "sigma_pre", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std3").feature("stat").setIndex("pname", "sigma_pre", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(1,1,3)", 0);
    model.study("std3").feature("stat").setIndex("punit", "GPa", 0);
    model.study("std3").createAutoSequences("sol");

    model.sol("sol5").runFromTo("st1", "su1");

    model.study("std3").feature("frlin").set("plist", "range(50,50,600) range(13150,25,13500)");
    model.study("std3").feature("frlin").set("uselinpsol", true);
    model.study("std3").feature("frlin").set("linpsolnum", 3);
    model.study("std3").feature("frlin").setSolveFor("/physics/ta", false);
    model.study("std3").feature("frlin").setSolveFor("/physics/acpr", false);
    model.study("std3").feature("frlin").setSolveFor("/frame/spatial1", false);
    model.study("std3").feature("frlin").setSolveFor("/multiphysics/atb1", false);
    model.study("std3").feature("frlin").setSolveFor("/multiphysics/tatb1", false);
    model.study("std3").createAutoSequences("sol");

    model.sol("sol5").runFromTo("st2", "s2");

    model.result().dataset().create("mesh1", "Mesh");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7f51\u683c\u56fe");
    model.result("pg1").set("data", "mesh1");
    model.result("pg1").set("inherithide", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("meshdomain", "volume");
    model.result("pg1").feature("mesh1").set("filteractive", true);
    model.result("pg1").feature("mesh1").set("logfilterexpr", "x<0.5[mm]");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u632f\u578b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff08\u7a33\u6001\uff09");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb\uff08\u7a33\u6001\uff09");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u6e29\u5ea6\uff08\u7a33\u6001\uff09");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u4f4d\u79fb\uff08\u6270\u52a8\uff09");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6\uff08\u6270\u52a8\uff09");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("edges", false);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "T");
    model.result("pg7").feature("surf1").set("unit", "mK");
    model.result("pg7").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg7").run();
    model.result("pg7").feature("surf1").create("filt1", "Filter");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("filt1").set("expr", "x<0.5[mm]");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().named("sel7");
    model.result("pg7").run();
    model.result("pg7").create("slc1", "Slice");
    model.result("pg7").feature("slc1").set("expr", "ta.T_t");
    model.result("pg7").feature("slc1").set("unit", "mK");
    model.result("pg7").feature("slc1").set("quickxnumber", 1);
    model.result("pg7").feature("slc1").set("colorlegend", false);
    model.result("pg7").feature("slc1").set("inheritplot", "surf1");
    model.result("pg7").run();
    model.result("pg7").feature("slc1").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("slc1").feature("tran1").set("transparency", 0.05);
    model.result("pg7").feature("slc1").feature("tran1").set("uniformblending", 0.1);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("slc2", "Slice");
    model.result("pg7").feature("slc2").set("expr", "T");
    model.result("pg7").feature("slc2").set("unit", "mK");
    model.result("pg7").feature("slc2").set("colorlegend", false);
    model.result("pg7").feature("slc2").set("inheritplot", "surf1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", "1");
    model.result("pg7").feature("vol1").set("coloring", "uniform");
    model.result("pg7").feature("vol1").set("color", "custom");
    model.result("pg7").feature("vol1").set("customcolor", new double[]{0.8784313797950745, 1, 1});
    model.result("pg7").feature("vol1").create("sel1", "Selection");
    model.result("pg7").feature("vol1").feature("sel1").selection().set(1, 2, 4);
    model.result("pg7").run();
    model.result("pg7").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("vol1").feature("mtrl1").set("family", "aluminumanodized");
    model.result("pg7").feature("vol1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("coloring", "uniform");
    model.result("pg7").feature("line1").set("color", "black");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u58f0\u901f\uff08\u6270\u52a8\uff09");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("slc1", "Slice");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg8").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg8").feature("slc1").set("unit", "mm/s");
    model.result("pg8").feature("slc1").set("quickxnumber", 1);
    model.result("pg8").run();
    model.result("pg8").feature("slc1").create("tran1", "Transparency");
    model.result("pg8").run();
    model.result("pg8").feature("slc1").feature("tran1").set("transparency", 0.1);
    model.result("pg8").feature("slc1").feature("tran1").set("uniformblending", 0.1);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u58f0\u538b\uff08\u6270\u52a8\uff09");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").create("slc1", "Slice");
    model.result("pg9").feature("slc1").set("expr", "ta.p_t");
    model.result("pg9").feature("slc1").set("quickxnumber", 1);
    model.result("pg9").feature("slc1").set("colortable", "Wave");
    model.result("pg9").feature("slc1").set("colorscalemode", "linearsymmetric");
    model.result("pg9").run();
    model.result("pg9").feature("slc1").create("tran1", "Transparency");
    model.result("pg9").run();
    model.result("pg9").feature("slc1").feature("tran1").set("transparency", 0.1);
    model.result("pg9").feature("slc1").feature("tran1").set("uniformblending", 0.1);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").create("slc2", "Slice");
    model.result("pg9").feature("slc2").set("expr", "acpr.p_t");
    model.result("pg9").feature("slc2").set("quickxnumber", 1);
    model.result("pg9").feature("slc2").set("inheritplot", "slc1");
    model.result("pg9").run();
    model.result("pg9").feature("slc2").create("tran1", "Transparency");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u54cd\u5e94\u6bd4\u8f83\uff08\u5171\u632f\u65f6\uff09");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg10").setIndex("looplevelindices", "range(13,1,27)", 0);
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").selection().set(97);
    model.result("pg10").feature("ptgr1").set("expr", "abs(w)");
    model.result("pg10").feature("ptgr1").set("linewidth", 1);
    model.result("pg10").feature("ptgr1").set("legend", true);
    model.result("pg10").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr1").setIndex("legends", "\u5b8c\u6574\u6a21\u578b (ta-ht-solid)", 0);
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").set("data", "dset5");
    model.result("pg10").feature("ptgr2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg10").feature("ptgr2").setIndex("looplevelindices", "range(13,1,27)", 0);
    model.result("pg10").feature("ptgr2").setIndex("legends", "\u56fa\u4f53\u635f\u8017 (ht-solid)", 0);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u54cd\u5e94\u6bd4\u8f83\uff08\u5178\u578b\u64cd\u4f5c\uff09");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg11").setIndex("looplevelindices", "range(1,1,12)", 0);
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").selection().set(97);
    model.result("pg11").feature("ptgr1").set("expr", "abs(w)");
    model.result("pg11").feature("ptgr1").set("linewidth", 1);
    model.result("pg11").feature("ptgr1").set("legend", true);
    model.result("pg11").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg11").feature("ptgr1").setIndex("legends", "\u5b8c\u6574\u6a21\u578b (ta-ht-solid)", 0);
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg11").run();
    model.result("pg11").feature("ptgr2").set("data", "dset5");
    model.result("pg11").feature("ptgr2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg11").feature("ptgr2").setIndex("looplevelindices", "range(1,1,12)", 0);
    model.result("pg11").feature("ptgr2").setIndex("legends", "\u56fa\u4f53\u635f\u8017 (ht-solid)", 0);
    model.result("pg11").run();

    model
         .title("\u9884\u5e94\u529b\u5fae\u955c\u632f\u52a8\uff1a\u70ed\u9ecf\u6027-\u70ed\u5f39\u6027\u529b\u5b66\u8026\u5408");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u5fae\u955c\u5728\u7a7a\u6c14\u4e2d\u7684\u8fd0\u884c\u60c5\u51b5\u4ee5\u53ca\u70ed\u9ecf\u6027\u963b\u5c3c\u7684\u5f71\u54cd\uff0c\u5176\u4e2d\u5305\u542b\u70ed\u81a8\u80c0\u548c\u70ed\u9ecf\u6027\u58f0\u5b66\u73b0\u8c61\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("micromirror_prestressed_vibration.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
