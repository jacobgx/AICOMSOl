/*
 * motherboard_shock_response.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:05 by COMSOL 6.3.0.290. */
public class motherboard_shock_response {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Dynamics_and_Vibration");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("eigmethod", "region");
    model.study("std1").feature("eig").set("shift", "0");
    model.study("std1").feature("eig").set("shiftactive", false);
    model.study("std1").feature("eig").set("appnreigs", "50");
    model.study("std1").feature("eig").set("eigsr", "0.1");
    model.study("std1").feature("eig").set("eiglr", "33");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("rsp1", "ResponseSpectrum");
    model.component("comp1").common("rsp1").set("eigStudy", "std1");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "motherboard_shock_response.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection("sel1").label("PCB");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(17, 21);
    model.component("comp1").selection("sel2").label("\u6563\u70ed\u5668");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(3, 4, 7, 8, 9, 10, 18, 22);
    model.component("comp1").selection("sel3").label("\u82af\u7247");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(2, 5, 6, 11, 12, 13, 14);
    model.component("comp1").selection("sel4").label("\u8fde\u63a5\u5668\u5757");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set(23, 24, 25, 28, 31);
    model.component("comp1").selection("sel5").label("\u7535\u5bb9\u5668\uff08\u8f83\u5927\uff09");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").set(15, 16, 19, 20, 26, 27, 29, 30);
    model.component("comp1").selection("sel6").label("\u7535\u5bb9\u5668\uff08\u8f83\u5c0f\uff09");
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7")
         .set(7, 9, 11, 12, 13, 14, 16, 17, 18, 19, 38, 40, 42, 43, 44, 45, 46, 47, 48, 49, 68, 70, 72, 73, 74, 75, 77, 78, 79, 80);
    model.component("comp1").selection("sel7").label("\u5b89\u88c5\u5b54");

    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat1").set("family", "pcb");
    model.component("comp1").material("mat1").set("color", "custom");
    model.component("comp1").material("mat1").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").label("Silicon");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.7);
    model.component("comp1").material("mat3").set("roughness", 0.5);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.component("comp1").material("mat3").selection().named("sel3");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").label("Acrylic plastic");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("lighting", "phong");
    model.component("comp1").material("mat4").set("shininess", 1000);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat4").selection().named("sel4");
    model.component("comp1").material("mat4").set("family", "plasticshiny");
    model.component("comp1").material("mat4").set("color", "black");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat5").label("Aluminum 1");
    model.component("comp1").material("mat5").set("family", "aluminum");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat5")
         .label("\u7535\u5bb9\u5668\uff08\u8f83\u5927\uff09\u590d\u5408\u6750\u6599");
    model.component("comp1").material("mat5").selection().named("sel5");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"1500[kg/m^3]"});
    model.component("comp1").material("mat5").set("family", "plasticshiny");
    model.component("comp1").material("mat5").set("color", "yellow");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat6").label("Aluminum 1");
    model.component("comp1").material("mat6").set("family", "aluminum");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat6").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat6").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat6").selection().named("sel6");
    model.component("comp1").material("mat6")
         .label("\u7535\u5bb9\u5668\uff08\u8f83\u5c0f\uff09\u590d\u5408\u6750\u6599");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"2000[kg/m^3]"});
    model.component("comp1").material("mat6").set("family", "plasticshiny");
    model.component("comp1").material("mat6").set("color", "cyan");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel7");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel6");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection()
         .set(4, 5, 8, 10, 21, 22, 23, 24, 25, 26, 27, 28, 30, 34, 35, 36, 37, 39, 41, 50, 51, 52, 53, 54, 55, 56, 58, 61, 62, 63, 64, 66, 67, 69, 71);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", "6[mm]");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmin", "0.06[mm]");
    model.component("comp1").mesh("mesh1").run("fq1");
    model.component("comp1").mesh("mesh1").create("fq2", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq2").selection().set(174, 231);
    model.component("comp1").mesh("mesh1").feature("fq2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq2").feature("size1").set("hmax", "5[mm]");
    model.component("comp1").mesh("mesh1").run("fq2");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(86, 92, 97, 102, 109, 114, 119, 128, 133, 140, 146, 151, 158, 215, 280);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").label("\u5206\u5e03\uff1a\u9ed8\u8ba4");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").label("\u5206\u5e03\uff1aPCB");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3")
         .label("\u5206\u5e03\uff1a\u6563\u70ed\u5668");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("numelem", 4);
    model.component("comp1").mesh("mesh1").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u52a0\u901f\u5ea6 (g) vs. \u9891\u7387 (Hz)");
    model.func("int1")
         .set("table", new String[][]{{"", ""}, 
         {"5", "10.221170793435215"}, 
         {"10", "20.26879679745779"}, 
         {"15", "29.973306865496376"}, 
         {"20", "39.16869022232764"}, 
         {"25", "47.700241377321625"}, 
         {"30", "55.44173271375691"}, 
         {"35", "62.26790631941229"}, 
         {"40", "68.2226018837682"}, 
         {"45", "72.25156373117935"}, 
         {"50", "76.84071593485142"}, 
         {"55", "79.34738555413855"}, 
         {"60", "80.8482392265354"}, 
         {"65", "81.88481741900146"}, 
         {"70", "82.73388349564995"}, 
         {"75", "82.81339312855084"}, 
         {"80", "82.53033878691974"}, 
         {"85", "82.12161035440371"}, 
         {"90", "81.25042860023268"}, 
         {"95", "80.42718780153068"}, 
         {"100", "79.62075451679233"}, 
         {"105", "78.5425672184405"}, 
         {"110", "77.4317062689539"}, 
         {"115", "76.22878279044207"}, 
         {"120", "75.04889893574875"}, 
         {"125", "73.82984905846456"}, 
         {"130", "72.59556376060202"}, 
         {"135", "71.38816886436065"}, 
         {"140", "70.17472445496252"}, 
         {"145", "68.92259264141522"}, 
         {"150", "67.73817100986659"}, 
         {"155", "66.57237185032993"}, 
         {"160", "65.42065895455457"}, 
         {"165", "64.25557440276435"}, 
         {"170", "63.160208913606155"}, 
         {"175", "62.06421149880928"}, 
         {"180", "60.96426510310301"}, 
         {"185", "59.98596971506377"}, 
         {"190", "58.87254038128926"}, 
         {"195", "57.874864372406016"}, 
         {"200", "56.97642900020106"}, 
         {"205", "56.084318979720194"}, 
         {"210", "55.12502085742939"}, 
         {"215", "54.19609794896904"}, 
         {"220", "53.333682057489135"}, 
         {"225", "52.46242441302917"}, 
         {"230", "51.67980188311241"}, 
         {"235", "51.940219748215775"}, 
         {"240", "52.63103543335559"}, 
         {"245", "53.148082578976535"}, 
         {"250", "53.61390448939853"}, 
         {"255", "54.029157974307324"}, 
         {"260", "54.404221004194575"}, 
         {"265", "54.7293214577556"}, 
         {"270", "54.956260230626214"}, 
         {"275", "55.13961082277069"}, 
         {"280", "55.27848161396368"}, 
         {"285", "55.37288877771143"}, 
         {"290", "55.45133360069303"}, 
         {"295", "55.48029023176928"}, 
         {"300", "55.486249970663515"}, 
         {"305", "55.43565244709387"}, 
         {"310", "55.39571311136667"}, 
         {"315", "55.305464041629634"}, 
         {"320", "55.200957658726075"}, 
         {"325", "55.07014932176579"}, 
         {"330", "54.918613221547794"}, 
         {"335", "54.78581836433039"}, 
         {"340", "54.59963979660284"}, 
         {"345", "54.41891777279121"}, 
         {"350", "54.21076424650667"}, 
         {"355", "53.98568983729553"}, 
         {"360", "53.78315316910489"}, 
         {"365", "53.53425009581258"}, 
         {"370", "53.3056530389677"}, 
         {"375", "53.08237683268847"}, 
         {"380", "52.81568433866547"}, 
         {"385", "52.54058828031988"}, 
         {"390", "52.284171452598166"}, 
         {"395", "52.05344937096207"}, 
         {"400", "51.748491030293856"}, 
         {"405", "51.47798394800138"}, 
         {"410", "51.2091022269832"}, 
         {"415", "50.91592535132613"}, 
         {"420", "51.110965403803625"}, 
         {"425", "51.33274129522117"}, 
         {"430", "51.50010442793826"}, 
         {"435", "51.68071974285299"}, 
         {"440", "51.820151703046626"}, 
         {"445", "51.96016693327953"}, 
         {"450", "52.04227043949072"}, 
         {"455", "52.15510140956678"}, 
         {"460", "52.20727605726852"}, 
         {"465", "52.29401972298162"}, 
         {"470", "52.30039148909087"}, 
         {"475", "52.366714865333776"}, 
         {"480", "52.367301623729745"}, 
         {"485", "52.38348368533653"}, 
         {"490", "52.38171704170545"}, 
         {"495", "52.356738478805816"}, 
         {"500", "52.34889964505452"}, 
         {"505", "52.283464398893145"}, 
         {"510", "52.26926138134658"}, 
         {"515", "52.205594007530586"}, 
         {"520", "52.14218114343268"}, 
         {"525", "52.091000042536024"}, 
         {"530", "51.99739902385488"}, 
         {"535", "51.92705786674835"}, 
         {"540", "51.843990464114235"}, 
         {"545", "51.72214903770373"}, 
         {"550", "51.65426437019689"}, 
         {"555", "51.5504740423845"}, 
         {"560", "51.41096860446974"}, 
         {"565", "51.30628814933168"}, 
         {"570", "51.20152847439844"}, 
         {"575", "51.055367926505426"}, 
         {"580", "50.957244365619076"}, 
         {"585", "50.82526816718546"}, 
         {"590", "50.66256478862709"}, 
         {"595", "50.56286423180257"}, 
         {"600", "50.520073903870085"}, 
         {"605", "50.6656084295829"}, 
         {"610", "50.74635725502876"}, 
         {"615", "50.823273202611375"}, 
         {"620", "50.91586460927443"}, 
         {"625", "50.96879603292319"}, 
         {"630", "51.022408756141054"}, 
         {"635", "51.09889347209716"}, 
         {"640", "51.12487059740694"}, 
         {"645", "51.164722581127364"}, 
         {"650", "51.2051413810137"}, 
         {"655", "51.21640663972892"}, 
         {"660", "51.23710563906714"}, 
         {"665", "51.26267223604648"}, 
         {"670", "51.253228769399215"}, 
         {"675", "51.244644330351576"}, 
         {"680", "51.25613504449869"}, 
         {"685", "51.23845715758233"}, 
         {"690", "51.19521381905702"}, 
         {"695", "51.20113876427941"}, 
         {"700", "51.17765192802399"}, 
         {"705", "51.1308511687109"}, 
         {"710", "51.096251351081065"}, 
         {"715", "51.06834380931806"}, 
         {"720", "51.01812335614682"}, 
         {"725", "50.94723138923269"}, 
         {"730", "50.92143813645869"}, 
         {"735", "50.877064908467716"}, 
         {"740", "50.79843522593459"}, 
         {"745", "50.72563401665212"}, 
         {"750", "50.6867901857802"}, 
         {"755", "50.61459295248838"}, 
         {"760", "50.53261434565635"}, 
         {"765", "50.44745898745178"}, 
         {"770", "50.39321787863496"}, 
         {"775", "50.32013785221639"}, 
         {"780", "50.2621845333564"}, 
         {"785", "50.33192550169028"}, 
         {"790", "50.379794347590725"}, 
         {"795", "50.408276495741745"}, 
         {"800", "50.48570093053196"}, 
         {"805", "50.53506237060917"}, 
         {"810", "50.55987465951624"}, 
         {"815", "50.59566037402958"}, 
         {"820", "50.64195121555137"}, 
         {"825", "50.662897422474565"}, 
         {"830", "50.66880190881728"}, 
         {"835", "50.6853782779966"}, 
         {"840", "50.716756065066384"}, 
         {"845", "50.72629576168073"}, 
         {"850", "50.71689537345662"}, 
         {"855", "50.716384657669636"}, 
         {"860", "50.73072163138769"}, 
         {"865", "50.72961710135274"}, 
         {"870", "50.70966282361408"}, 
         {"875", "50.685070908931024"}, 
         {"880", "50.69069131027255"}, 
         {"885", "50.64948502161752"}, 
         {"890", "50.65424236086185"}, 
         {"895", "50.616332188420465"}, 
         {"900", "50.597766480960175"}, 
         {"905", "50.58379161690815"}, 
         {"910", "50.55669474286842"}, 
         {"915", "50.50897213927255"}, 
         {"920", "50.456387537642925"}, 
         {"925", "50.439321635244546"}, 
         {"930", "50.40919327141314"}, 
         {"935", "50.36963286539626"}, 
         {"940", "50.317761647093604"}, 
         {"945", "50.24996405875599"}, 
         {"950", "50.221734322413724"}, 
         {"955", "50.1819798879743"}, 
         {"960", "50.132078018860724"}, 
         {"965", "50.15690124134168"}, 
         {"970", "50.20765410418001"}, 
         {"975", "50.23616283503273"}, 
         {"980", "50.25660359996791"}, 
         {"985", "50.2871503222773"}, 
         {"990", "50.328488892460335"}, 
         {"995", "50.357394024381506"}, 
         {"1000", "50.371010299210894"}});
    model.func("int1").setIndex("fununit", "Hz", 0);
    model.func("int1").setIndex("argunit", 1, 0);
    model.func().create("an1", "Analytic");
    model.func("an1").label("\u5782\u76f4\u8c31");
    model.func("an1").set("funcname", "vsp");
    model.func("an1").set("args", "freq");
    model.func("an1").set("expr", "int1(freq)*g_const");
    model.func("an1").setIndex("argunit", "Hz", 0);
    model.func("an1").set("fununit", "m/s^2");
    model.func("an1").setIndex("plotargs", 5, 0, 1);
    model.func("an1").setIndex("plotargs", 600, 0, 2);
    model.func("an1").createPlot("pg1");

    return model;
  }

  public static Model run2(Model model) {

    model.result("pg1").run();
    model.result("pg1").label("\u5782\u76f4\u8c31");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u4f2a\u52a0\u901f\u5ea6 (m/s^2)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 600);
    model.result("pg1").run();

    model.study("std1").feature("eig").set("eigmethod", "manual");
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 15);
    model.study("std1").feature("eig").set("shiftactive", true);
    model.study("std1").feature("eig").set("shift", "65");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u632f\u578b (solid)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup().create("std1rsp1", "EvaluationGroup");
    model.result().evaluationGroup("std1rsp1").set("data", "dset1");
    model.result().evaluationGroup("std1rsp1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1rsp1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("expr", "rsp1.pfLnormX", 0);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1rsp1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("expr", "rsp1.pfLnormY", 1);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1rsp1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("expr", "rsp1.pfLnormZ", 2);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1rsp1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("expr", "rsp1.mEffLX", 3);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("unit", "kg", 3);
    model.result().evaluationGroup("std1rsp1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 3);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("expr", "rsp1.mEffLY", 4);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("unit", "kg", 4);
    model.result().evaluationGroup("std1rsp1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 4);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("expr", "rsp1.mEffLZ", 5);
    model.result().evaluationGroup("std1rsp1").feature("gev1").setIndex("unit", "kg", 5);
    model.result().evaluationGroup("std1rsp1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 5);
    model.result().evaluationGroup("std1rsp1").run();
    model.result("pg2").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg3").feature("tblp1").set("evaluationgroup", "std1rsp1");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg3").feature("tblp1").set("linemarker", "asterisk");
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").run();
    model.result("pg3").label("\u53c2\u4e0e\u56e0\u5b50");
    model.result("pg1").run();
    model.result("pg1").set("xmin", 30);
    model.result("pg1").set("xmax", 400);
    model.result("pg1").set("ymin", 500);
    model.result("pg1").set("ymax", 850);
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("data", "dset1");
    model.result("pg1").feature("glob1").set("expr", new String[]{"vsp(freq)"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u5782\u76f4\u8c31"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"m/s^2"});
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "freq");
    model.result("pg1").feature("glob1").set("linestyle", "none");
    model.result("pg1").feature("glob1").set("linemarker", "cycle");
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u7279\u5f81\u9891\u7387\u503c", 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{3});
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"rsp1.mEffLZ"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb"});
    model.result().numerical("gev1").set("unit", new String[]{"kg"});
    model.result().numerical("gev1").setIndex("expr", "rsp1.mEffLZ/rsp1.mass", 0);
    model.result().numerical("gev1").set("dataseries", "integral");
    model.result().numerical("gev1").set("dataseriesmethod", "summation");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().dataset().create("rs1", "ResponseSpectrum3D");
    model.result().dataset("rs1").set("vertspectrum", "an1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u6700\u5927\u4f4d\u79fb\uff0c\u54cd\u5e94\u8c31");
    model.result("pg4").set("data", "rs1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "w");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").run();

    model.component("comp1").physics("solid").create("bex1", "BaseExcitation", -1);
    model.component("comp1").physics("solid").feature("bex1").set("ab", new String[]{"0", "0", "50*g_const"});

    model.study().create("std2");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("timod", "Transientmodal");
    model.study("std2").feature("timod").set("tunit", "ms");
    model.study("std2").feature("timod").set("tlist", "range(0,0.5,60)");
    model.study("std2").feature("timod").set("usertol", true);
    model.study("std2").feature("timod").set("rtol", "0.0001");
    model.study("std2").feature("timod").set("loadfact", "sin(2*pi/22[ms]*t)*(t<11[ms])");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("mo1").set("eigsol", "sol1");
    model.sol("sol2").feature("mo1").set("dampratio", 0.05);
    model.sol("sol2").feature("mo1").set("storeudot", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u6700\u5927\u4f4d\u79fb\uff0c\u65f6\u95f4\u5386\u7a0b");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "timemax(0,60[ms],abs(w))");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u8868\u9762\uff1atimemax(0, 50[ms], abs(w)) (m)");
    model.result("pg5").set("paramindicator", "");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u4f4d\u79fb\uff0c\u65f6\u95f4");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 23, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "w");
    model.result("pg6").feature("surf1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg6").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
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
    model.result().export("anim1").set("plotgroup", "pg6");
    model.result().export("anim1").set("maxframes", 100);
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u5e94\u529b\uff0c\u54cd\u5e94\u8c31");
    model.result("pg7").set("data", "rs1");
    model.result("pg7").selection().geom("geom1", 3);
    model.result("pg7").selection().geom("geom1", 3);
    model.result("pg7").selection().set(1);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "solid.mises");
    model.result("pg7").feature("surf1").set("unit", "MPa");
    model.result("pg7").feature("surf1").set("rangecoloractive", true);
    model.result("pg7").feature("surf1").set("rangecolormax", 150);
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").run();
    model.result("pg4").run();

    model.title("\u4e3b\u677f\u7684\u51b2\u51fb\u54cd\u5e94");

    model
         .description("\u7535\u5b50\u8bbe\u5907\u5728\u53d7\u5230\u6307\u5b9a\u7684\u51b2\u51fb\u8f7d\u8377\u540e\uff0c\u901a\u5e38\u5fc5\u987b\u7ecf\u8fc7\u9a8c\u8bc1\u4ee5\u786e\u4fdd\u6b63\u5e38\u5de5\u4f5c\u3002\u672c\u4f8b\u4f7f\u7528\u54cd\u5e94\u8c31\u5206\u6790\u6765\u7814\u7a76 50\u00a0g 11\u00a0ms \u534a\u6b63\u5f26\u51b2\u51fb\u5bf9\u7535\u8def\u677f\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("motherboard_shock_response.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
