/*
 * fuel_tank_vibration.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:08 by COMSOL 6.3.0.290. */
public class fuel_tank_vibration {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho0", "830[kg/m^3]", "\u71c3\u6599\u5bc6\u5ea6");
    model.param().set("c0", "1360[m/s]", "\u71c3\u6599\u4e2d\u7684\u58f0\u901f");
    model.param().set("lam0", "c0/fmax", "\u6700\u5927\u9891\u7387\u5904\u7684\u6ce2\u957f");
    model.param().set("mesh_size", "15[mm]", "\u58f3\u8fb9\u754c\u7684\u6700\u5927\u5927\u5c0f");
    model.param()
         .set("min_mesh_factor", "1.5", "\u6700\u5c0f\u4e0e\u6700\u5927\u7f51\u683c\u957f\u5ea6\u4e4b\u6bd4");
    model.param().set("strap_th", "2[mm]", "\u6cb9\u7bb1\u5e26\u7684\u539a\u5ea6");
    model.param().set("wall_th", "1.6[mm]", "\u6cb9\u7bb1\u58c1\u7684\u539a\u5ea6");
    model.param().set("a0", "1[m/s^2]", "\u7528\u4e8e\u6fc0\u52b1\u7cfb\u7edf\u7684\u52a0\u901f\u5ea6\u53c2\u8003");
    model.param().set("d0", "a0/(1000[Hz])^2", "\u7528\u4e8e\u6fc0\u52b1\u7cfb\u7edf\u7684\u4f4d\u79fb\u53c2\u8003");
    model.param().set("fmin", "50[Hz]", "\u5206\u6790\u7684\u6700\u5c0f\u9891\u7387");
    model.param().set("fmax", "250[Hz]", "\u5206\u6790\u7684\u6700\u5927\u9891\u7387");
    model.param().set("deltaf", "2.5[Hz]", "\u9891\u7387\u626b\u63cf\u7684\u6b65\u9aa4");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "fuel_tank_vibration.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7ed1\u5e26");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 83, 84, 85, 93, 97, 101, 173, 174, 175, 176, 177, 178, 203, 204, 205, 206, 207, 208);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6cb9\u7bb1");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2")
         .set(10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 86, 87, 88, 89, 90, 91, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 116, 122, 123, 124, 125, 126, 129, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, 156, 158, 159, 160, 161, 162, 164, 165, 166, 167, 168, 170, 171, 172, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6ce1\u6cab");
    model.component("comp1").selection("sel3").set(3, 4, 5, 6);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u58f3");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u71c3\u6599");
    model.component("comp1").selection("sel4").set(2);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u6e7f\u6da6\u8868\u9762");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5")
         .set(12, 14, 16, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 90, 91, 104, 105, 106, 107, 108, 109, 110, 111, 112, 125, 126, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 155, 156, 158, 159, 160, 161, 162, 164, 165, 166, 167, 168, 170, 171, 172, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u9644\u52a0\u7684\u8fb9\u754c");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(1, 2, 3, 117, 206, 207, 208);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u6d41\u4f53\u81ea\u7531\u8868\u9762");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(13);

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").label("\u71c3\u6599\u8d28\u91cf");
    model.component("comp1").massProp("mass1").selection().named("sel4");
    model.component("comp1").massProp("mass1").set("expr", "rho0");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Aluminum 6063-T83");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Steel AISI 4340");
    model.component("comp1").material("mat2").set("family", "steel");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").selection().geom("geom1", 2);
    model.component("comp1").material("mat1").selection().named("sel2");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", new String[]{"0.01"});
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", new String[]{"0.01"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u71c3\u6599");
    model.component("comp1").material("mat3").selection().named("sel4");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", new String[]{"c0"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u6ce1\u6cab\u6750\u6599");
    model.component("comp1").material("mat4").selection().named("sel3");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", new String[]{"50[MPa]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", new String[]{"0.45"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"500[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("lossfactor", new String[]{"0.1"});

    model.component("comp1").physics("shell").selection().named("uni1");
    model.component("comp1").physics("shell").feature("emm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("shell").feature("emm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("shell").feature("to1").set("d", "wall_th");
    model.component("comp1").physics("shell").create("to2", "ThicknessOffset", 2);
    model.component("comp1").physics("shell").feature("to2").selection().named("sel1");
    model.component("comp1").physics("shell").feature("to2").set("d", "strap_th");
    model.component("comp1").physics("shell").create("adm1", "AddedMass2", 2);
    model.component("comp1").physics("shell").feature("adm1").selection().named("sel5");
    model.component("comp1").physics("shell").feature("adm1").set("MassType", "mTot");
    model.component("comp1").physics("shell").feature("adm1")
         .set("mTot", new String[]{"mass1.mass", "0", "0", "0", "mass1.mass", "0", "0", "0", "mass1.mass"});
    model.component("comp1").physics("shell").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("shell").feature("disp1").selection().named("sel6");
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp1").setIndex("U0", "d0", 2);
    model.component("comp1").physics("solid").selection().named("sel3");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().named("sel6");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "d0", 2);
    model.component("comp1").physics("acpr").selection().named("sel4");
    model.component("comp1").physics("acpr").create("ssb1", "SoundSoft", 2);
    model.component("comp1").physics("acpr").feature("ssb1").selection().named("sel7");

    model.component("comp1").multiphysics().create("sshc1", "SolidShellConnection", -1);
    model.component("comp1").multiphysics("sshc1").set("connectionSettings", "sharedBnd");
    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").selection().named("sel5");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(7, 8, 9, 11, 14, 15, 16, 17, 18, 19, 20, 21, 40, 43, 46, 47, 48, 49, 50, 51, 52, 53, 55, 58, 61, 63, 71, 72, 73, 74, 75, 76, 77, 78, 83, 84, 85, 86, 87, 88, 89, 91, 101, 104, 105, 106, 107, 108, 109, 110, 112, 113, 122, 123, 124, 125, 126, 129, 131, 132, 133, 134, 135, 136, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 150, 152, 154, 173, 174, 175, 176, 177, 178, 179, 181, 183, 184, 186, 188, 189, 191, 193, 194, 202);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "mesh_size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmin", "mesh_size/min_mesh_factor");
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(116);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", "mesh_size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmin", "mesh_size/min_mesh_factor");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "6[mm]");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("optcurved", false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("sel5");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "mesh_size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1")
         .set("hmin", "mesh_size/min_mesh_factor");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().remaining();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u9644\u52a0\u8d28\u91cf\u6cd5\u6a21\u5f0f");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("shift", "0[Hz]");
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", false);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/asb1", false);
    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("ftplistmethod", "manual");
    model.study("std2").feature("eig").set("chkeigregion", true);
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("linpsolnum", "auto");
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("eig").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/sshc1", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/asb1", true);
    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/sshc1", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std4").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std4").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/sshc1", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u58f0\u5b66\u6cd5\u6a21\u5f0f");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("shift", "0[Hz]");
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"shell/adm1"});
    model.study("std3").label("\u7814\u7a76 3 - \u9644\u52a0\u8d28\u91cf\u6cd5\u9891\u7387\u54cd\u5e94");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("freq").set("plist", "range(fmin,deltaf,fmax)");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/asb1", false);
    model.study("std4").setGenPlots(false);
    model.study("std4").label("\u7814\u7a76 4 - \u58f0\u5b66\u6cd5\u9891\u7387\u54cd\u5e94");
    model.study("std4").feature("freq").set("plist", "range(fmin,deltaf,fmax)");
    model.study("std4").feature("freq").set("useadvanceddisable", true);
    model.study("std4").feature("freq").set("disabledphysics", new String[]{"shell/adm1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u9644\u52a0\u8d28\u91cf\u6cd5\u632f\u578b");
    model.result("pg1").set("looplevel", new int[]{4});
    model.result("pg1").set("edges", false);
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "if(isnan(solid.disp),shell.disp,solid.disp)");
    model.result("pg1").feature("surf1").set("descractive", true);
    model.result("pg1").feature("surf1").set("descr", "\u632f\u578b - \u9644\u52a0\u8d28\u91cf\u6cd5");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("expr", new String[]{"if(isnan(u2),u,u2)", "v", "w"});
    model.result("pg1").feature("surf1").feature("def1").setIndex("expr", "if(isnan(v2),v,v2)", 1);
    model.result("pg1").feature("surf1").feature("def1").setIndex("expr", "if(isnan(w2),w,w2)", 2);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "0");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("inheritplot", "surf1");
    model.result("pg1").feature("line1").set("inheritcolor", false);
    model.result("pg1").feature("line1").set("inheritrange", false);
    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature("def1").set("expr", new String[]{"if(isnan(u2),u,u2)", "v", "w"});
    model.result("pg1").feature("line1").feature("def1").setIndex("expr", "if(isnan(v2),v,v2)", 1);
    model.result("pg1").feature("line1").feature("def1").setIndex("expr", "if(isnan(w2),w,w2)", 2);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u58f0\u5b66\u6cd5\u632f\u578b");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("looplevel", new int[]{3});
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("descr", "\u632f\u578b - \u538b\u529b\u58f0\u5b66\u6cd5");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("FRF\uff1a\u6cb9\u7bb1\u7ed1\u5e26\u5904\u7684\u5e94\u529b");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "FRF (MPa/m)");
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("ylog", true);
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").create("oct1", "OctaveBand");
    model.result("pg3").feature("oct1").set("quantity", "bandpower");
    model.result("pg3").feature("oct1").set("markerpos", "datapoints");
    model.result("pg3").feature("oct1").set("linewidth", "preference");
    model.result("pg3").feature("oct1").selection().set(93);
    model.result("pg3").feature("oct1").set("expr", "abs(shell.szz)/1e9");
    model.result("pg3").feature("oct1").set("exprtype", "general");
    model.result("pg3").feature("oct1").set("generalref", "d0");
    model.result("pg3").feature("oct1").set("quantity", "continuous");
    model.result("pg3").feature("oct1").set("legend", true);
    model.result("pg3").feature("oct1").set("legendmethod", "manual");
    model.result("pg3").feature("oct1").setIndex("legends", "\u9644\u52a0\u8d28\u91cf\u6cd5", 0);
    model.result("pg3").feature().duplicate("oct2", "oct1");
    model.result("pg3").run();
    model.result("pg3").feature("oct2").set("data", "dset4");
    model.result("pg3").feature("oct2")
         .setIndex("legends", "\u58f0\u5b66\u6cd5\uff08\u5b8c\u6574\u6a21\u578b\uff09", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("90 Hz \u7684\u7edd\u5bf9\u5e94\u529b\u6bd4\u8f83");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 17, 0);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "if(isnan(solid.SZZ),abs(shell.szz),abs(solid.SZZ))");
    model.result("pg4").feature("surf1").set("unit", "MPa");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormax", 10);
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"if(isnan(u2),u,u2)", "v", "w"});
    model.result("pg4").feature("surf1").feature("def1").setIndex("expr", "if(isnan(v2),v,v2)", 1);
    model.result("pg4").feature("surf1").feature("def1").setIndex("expr", "if(isnan(w2),w,w2)", 2);
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 250);
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "0");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "black");
    model.result("pg4").feature("line1").set("inheritplot", "surf1");
    model.result("pg4").feature("line1").set("inheritcolor", false);
    model.result("pg4").feature("line1").set("inheritrange", false);
    model.result("pg4").feature("line1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("line1").feature("def1").set("expr", new String[]{"if(isnan(u2),u,u2)", "v", "w"});
    model.result("pg4").feature("line1").feature("def1").setIndex("expr", "if(isnan(v2),v,v2)", 1);
    model.result("pg4").feature("line1").feature("def1").setIndex("expr", "if(isnan(w2),w,w2)", 2);
    model.result("pg4").create("ann1", "Annotation");
    model.result("pg4").feature("ann1").set("text", "\u58f0\u5b66\u6cd5");
    model.result("pg4").feature("ann1").set("posyexpr", 100);
    model.result("pg4").feature("ann1").set("poszexpr", -200);
    model.result("pg4").feature("ann1").set("showpoint", false);
    model.result("pg4").feature("ann1").set("anchorpoint", "center");
    model.result("pg4").feature("ann1").set("showframe", true);
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("data", "dset3");
    model.result("pg4").feature("surf2").setIndex("looplevel", 17, 0);
    model.result("pg4").feature("surf2").set("expr", "if(isnan(solid.SZZ),abs(shell.szz),abs(solid.SZZ))");
    model.result("pg4").feature("surf2").set("unit", "MPa");
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").feature("surf2").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("def1").set("expr", new String[]{"if(isnan(u2),u,u2)", "v", "w"});
    model.result("pg4").feature("surf2").feature("def1").setIndex("expr", "if(isnan(v2),v,v2)", 1);
    model.result("pg4").feature("surf2").feature("def1").setIndex("expr", "if(isnan(w2),w,w2)", 2);
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("trn1").set("move", new int[]{-700, 0, 0});
    model.result("pg4").create("line2", "Line");
    model.result("pg4").feature("line2").set("expr", "0");
    model.result("pg4").feature("line2").set("data", "dset3");
    model.result("pg4").feature("line2").setIndex("looplevel", 17, 0);
    model.result("pg4").feature("line2").set("coloring", "uniform");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("line2").set("color", "black");
    model.result("pg4").feature("line2").set("inheritcolor", false);
    model.result("pg4").feature("line2").set("inheritrange", false);
    model.result("pg4").feature("line2").set("inheritplot", "surf2");
    model.result("pg4").feature("line2").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("line2").feature("def1").set("expr", new String[]{"if(isnan(u2),u,u2)", "v", "w"});
    model.result("pg4").feature("line2").feature("def1").setIndex("expr", "if(isnan(v2),v,v2)", 1);
    model.result("pg4").feature("line2").feature("def1").setIndex("expr", "if(isnan(w2),w,w2)", 2);
    model.result("pg4").run();
    model.result("pg4").feature("line2").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("line2").feature("trn1").set("move", new int[]{-700, 0, 0});
    model.result("pg4").create("ann2", "Annotation");
    model.result("pg4").feature("ann2").set("text", "\u9644\u52a0\u8d28\u91cf\u6cd5");
    model.result("pg4").feature("ann2").set("posxexpr", -700);
    model.result("pg4").feature("ann2").set("posyexpr", 100);
    model.result("pg4").feature("ann2").set("poszexpr", -220);
    model.result("pg4").feature("ann2").set("showpoint", false);
    model.result("pg4").feature("ann2").set("showframe", true);
    model.result("pg4").feature("ann2").set("anchorpoint", "center");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("127.5 Hz \u7684\u7edd\u5bf9\u5e94\u529b\u6bd4\u8f83");
    model.result("pg5").setIndex("looplevel", 32, 0);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("rangecolormax", 5);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").setIndex("looplevel", 32, 0);
    model.result("pg5").run();
    model.result("pg5").feature("line2").setIndex("looplevel", 32, 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u9644\u52a0\u8d28\u91cf\u6cd5\u7279\u5f81\u9891\u7387");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "freq*2*pi", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("\u58f0\u5b66\u6cd5\u7279\u5f81\u9891\u7387");
    model.result().evaluationGroup("eg2").set("data", "dset2");
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("\u71c3\u6599\u8d28\u91cf\u8ba1\u7b97");
    model.result().evaluationGroup("eg3").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "mass1.mass", 0);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("descr", "\u71c3\u6599\u8d28\u91cf", 0);
    model.result().evaluationGroup("eg3").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u7f29\u7565\u56fe");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevel", 63, 0);
    model.result("pg6").set("edges", false);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "if(isnan(solid.mises),shell.mises,solid.mises)");
    model.result("pg6").feature("surf1").set("unit", "MPa");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", 2);
    model.result("pg6").feature("surf1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").set("expr", new String[]{"if(isnan(u2),u,u2)", "v", "w"});
    model.result("pg6").feature("surf1").feature("def1").setIndex("expr", "if(isnan(v2),v,v2)", 1);
    model.result("pg6").feature("surf1").feature("def1").setIndex("expr", "if(isnan(w2),w,w2)", 2);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208);
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", "0");
    model.result("pg6").feature("line1").set("coloring", "uniform");
    model.result("pg6").feature("line1").set("color", "black");
    model.result("pg6").feature("line1").set("inheritcolor", false);
    model.result("pg6").feature("line1").set("inheritrange", false);
    model.result("pg6").feature("line1").set("inheritplot", "surf1");
    model.result("pg6").feature("line1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").feature("line1").feature("def1").set("expr", new String[]{"if(isnan(u2),u,u2)", "v", "w"});
    model.result("pg6").feature("line1").feature("def1").setIndex("expr", "if(isnan(v2),v,v2)", 1);
    model.result("pg6").feature("line1").feature("def1").setIndex("expr", "if(isnan(w2),w,w2)", 2);
    model.result("pg6").create("iso1", "Isosurface");
    model.result("pg6").feature("iso1").set("expr", "acpr.p_t");
    model.result("pg6").feature("iso1").set("number", 11);
    model.result("pg6").feature("iso1").set("colortable", "Wave");
    model.result("pg6").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u51e0\u4f55");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", "1");
    model.result("pg7").feature("vol1").create("sel1", "Selection");
    model.result("pg7").feature("vol1").feature("sel1").selection().named("sel3");
    model.result("pg7").run();
    model.result("pg7").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("vol1").feature("mtrl1").set("family", "rubber");
    model.result("pg7").feature("vol1").feature("mtrl1").set("color", "yellow");
    model.result("pg7").run();
    model.result("pg7").create("vol2", "Volume");
    model.result("pg7").feature("vol2").set("expr", "1");
    model.result("pg7").feature("vol2").create("sel1", "Selection");
    model.result("pg7").feature("vol2").feature("sel1").selection().named("sel4");
    model.result("pg7").run();
    model.result("pg7").feature("vol2").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("vol2").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("vol2").feature("mtrl1").set("family", "water");
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().named("sel1");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf1").feature("mtrl1").set("family", "steelscratched");
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("expr", "1");
    model.result("pg7").feature("surf2").create("sel1", "Selection");
    model.result("pg7").feature("surf2").feature("sel1").selection().named("uni1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf2").feature("mtrl1").set("family", "aluminumanodized");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("tran1").set("uniformblending", 0.5);
    model.result("pg7").run();
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").setIndex("expr", 0, 0);
    model.result("pg7").feature("arws1").set("expr", new String[]{"0", "0", "-1"});
    model.result("pg7").feature("arws1").set("arrowcount", 10);
    model.result("pg7").feature("arws1").set("arrowbase", "head");
    model.result("pg7").feature("arws1").create("sel1", "Selection");
    model.result("pg7").feature("arws1").feature("sel1").selection().set(117);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("arws2", "ArrowSurface");
    model.result("pg7").feature("arws2").setIndex("expr", 0, 0);
    model.result("pg7").feature("arws2").set("expr", new String[]{"0", "0", "-1"});
    model.result("pg7").feature("arws2").set("arrowcount", 12);
    model.result("pg7").feature("arws2").set("arrowbase", "head");
    model.result("pg7").feature("arws2").create("sel1", "Selection");
    model.result("pg7").feature("arws2").feature("sel1").selection().set(1, 2, 3, 206, 207, 208);
    model.result("pg7").run();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.result("pg4").run();
    model.result("pg4").set("view", "new");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("view", "view2");
    model.result("pg5").run();
    model.result("pg6").run();

    model.title("\u6cb9\u7bb1\u632f\u52a8");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u90e8\u5206\u5145\u6db2\u6cb9\u7bb1\u7684\u9891\u7387\u54cd\u5e94\uff0c\u5176\u4e2d\u6cb9\u7bb1\u53d7\u5230\u5782\u76f4\u52a0\u901f\u5ea6\u7684\u5f71\u54cd\u3002\n\n\u672c\u4f8b\u8003\u8651\u4f7f\u7528\u4e24\u79cd\u5efa\u6a21\u65b9\u6cd5\u6765\u8868\u793a\u6d41\u4f53\uff1a\u4e00\u79cd\u662f\u4f20\u7edf\u65b9\u6cd5\uff0c\u901a\u8fc7\u6cb9\u7bb1\u7684\u6e7f\u6da6\u8868\u9762\u6d82\u62b9\u6d41\u4f53\u8d28\u91cf\uff0c\u53e6\u4e00\u79cd\u662f\u91c7\u7528\u591a\u7269\u7406\u573a\u65b9\u6cd5\uff0c\u4e13\u95e8\u5bf9\u6d41\u4f53\u5185\u7684\u58f0\u538b\u8fdb\u884c\u5efa\u6a21\u3002\n\n\u8fd9\u4e24\u79cd\u65b9\u6cd5\u8868\u73b0\u51fa\u660e\u663e\u7684\u5dee\u5f02\uff0c\u5f3a\u8c03\u4e86\u5728\u9884\u6d4b\u5145\u6db2\u8154\u7684\u5e94\u529b\u6216\u75b2\u52b3\u5bff\u547d\u65f6\uff0c\u51c6\u786e\u6355\u6349\u632f\u52a8\u58f0\u5b66\u7279\u6027\u7684\u91cd\u8981\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("fuel_tank_vibration.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
