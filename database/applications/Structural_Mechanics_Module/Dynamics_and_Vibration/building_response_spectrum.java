/*
 * building_response_spectrum.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:05 by COMSOL 6.3.0.290. */
public class building_response_spectrum {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Dynamics_and_Vibration");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

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
    model.study("std1").feature("eig").setSolveFor("/physics/beam", true);

    model.component("comp1").common().create("rsp1", "ResponseSpectrum");
    model.component("comp1").common("rsp1").set("eigStudy", "std1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("WX", "6[m]", "X \u5411\u67f1\u95f4\u8ddd");
    model.param().set("WY", "5[m]", "Y \u5411\u67f1\u95f4\u8ddd");
    model.param().set("WZ", "3[m]", "\u697c\u5c42\u9ad8\u5ea6");
    model.param().set("colX", "5", "X \u5411\u67f1\u6570");
    model.param().set("colY", "4", "Y \u5411\u67f1\u6570");
    model.param().set("nFloors", "3", "\u697c\u5c42\u6570");
    model.param().set("massTol", "0.01", "\u6a21\u6001\u8d28\u91cf\u5bb9\u5dee");
    model.param().create("par2");
    model.param("par2").label("\u6c34\u5e73\u8c31\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("agH", "1[m/s^2]", "\u8bbe\u8ba1\u7684\u5730\u9762\u52a0\u901f\u5ea6");
    model.param("par2").set("qH", "1.0", "\u884c\u4e3a\u56e0\u5b50");
    model.param("par2").set("SH", "1.5", "\u571f\u58e4\u56e0\u5b50");
    model.param("par2").set("TbH", "0.1[s]", "\u5468\u671f\u65f6\u95f4\uff0c\u7b2c\u4e00\u65ad\u70b9");
    model.param("par2").set("TcH", "0.25[s]", "\u5468\u671f\u65f6\u95f4\uff0c\u7b2c\u4e8c\u65ad\u70b9");
    model.param("par2").set("TdH", "1.2[s]", "\u5468\u671f\u65f6\u95f4\uff0c\u7b2c\u4e09\u65ad\u70b9");
    model.param("par2").set("betaH", "0.2", "\u4e0b\u9650\u56e0\u5b50");
    model.param().create("par3");
    model.param("par3").label("\u5782\u76f4\u8c31\u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("agV", "0.45*agH", "\u5730\u9762\u52a0\u901f\u5ea6");
    model.param("par3").set("qV", "1.0", "\u884c\u4e3a\u56e0\u5b50");
    model.param("par3").set("SV", "1.0", "\u571f\u58e4\u56e0\u5b50");
    model.param("par3").set("TbV", "0.05[s]", "\u5468\u671f\u65f6\u95f4\uff0c\u7b2c\u4e00\u65ad\u70b9");
    model.param("par3").set("TcV", "0.15[s]", "\u5468\u671f\u65f6\u95f4\uff0c\u7b2c\u4e8c\u65ad\u70b9");
    model.param("par3").set("TdV", "1.0[s]", "\u5468\u671f\u65f6\u95f4\uff0c\u7b2c\u4e09\u65ad\u70b9");
    model.param("par3").set("betaV", "0.2", "\u4e0b\u9650\u56e0\u5b50");

    model.func().create("pw1", "Piecewise");
    model.func("pw1").label("\u6c34\u5e73\u8c31");
    model.func("pw1").set("funcname", "hsp");
    model.func("pw1").set("arg", "T");
    model.func("pw1")
         .set("pieces", new String[][]{{"0", "TbH", "agH*SH*(2/3+T/TbH*(2.5/qH-2/3))"}, 
         {"TbH", "TcH", "agH*SH*2.5/qH"}, 
         {"TcH", "TdH", "max(agH*SH*2.5/qH*TcH/T,betaH*agH)"}, 
         {"TdH", "3", "max(agH*SH*2.5/qH*TcH*TdH/T^2,betaH*agH)"}});
    model.func("pw1").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").label("\u6c34\u5e73\u4f2a\u52a0\u901f\u5ea6\u8c31");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5468\u671f (s)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "S (m/s^2)");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("extrapolation", "right");
    model.result("pg1").feature("plot1").set("linewidth", 2);
    model.result("pg1").run();

    model.func().create("pw2", "Piecewise");
    model.func("pw2").label("\u5782\u76f4\u8c31");
    model.func("pw2").set("funcname", "vsp");
    model.func("pw2").set("arg", "T");
    model.func("pw2")
         .set("pieces", new String[][]{{"0", "TbV", "agV*SV*(2/3+T/TbV*(2.5/qV-2/3))"}, 
         {"TbV", "TcV", "agV*SV*2.5/qV"}, 
         {"TcV", "TdV", "max(agV*SV*2.5/qV*TcV/T,betaV*agV)"}, 
         {"TdV", "3", "max(agV*SV*2.5/qV*TcV*TdV/T^2,betaV*agV)"}});
    model.func("pw2").createPlot("pg2");

    model.result("pg2").run();
    model.result("pg2").label("\u5782\u76f4\u4f2a\u52a0\u901f\u5ea6\u8c31");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u5468\u671f (s)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S (m/s^2)");
    model.result("pg2").run();
    model.result("pg2").feature("plot1").set("extrapolation", "right");
    model.result("pg2").feature("plot1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").label("\u8bbe\u8ba1\u54cd\u5e94\u8c31");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").label("\u67f1");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "WZ", 1, 2);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u67f1\u9009\u62e9");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"colX", "colY", "nFloors"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"WX", "WY", "WZ"});
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").label("\u6c34\u5e73 X \u65b9\u5411");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "WZ", 0, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "WX", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "WZ", 1, 2);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u6c34\u5e73 X");
    model.component("comp1").geom("geom1").feature("pol2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature().duplicate("arr2", "arr1");
    model.component("comp1").geom("geom1").runPre("arr2");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("arr2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("arr2").setIndex("fullsize", "colX-1", 0);
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").label("\u6c34\u5e73 Y \u65b9\u5411");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "WZ", 0, 2);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "WY", 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "WZ", 1, 2);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u6c34\u5e73 Y");
    model.component("comp1").geom("geom1").feature("pol3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").feature().duplicate("arr3", "arr2");
    model.component("comp1").geom("geom1").runPre("arr3");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").named("csel3");
    model.component("comp1").geom("geom1").feature("arr3").setIndex("fullsize", "colX", 0);
    model.component("comp1").geom("geom1").feature("arr3").setIndex("fullsize", "colY-1", 1);
    model.component("comp1").geom("geom1").feature("arr3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5730\u57fa");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "WZ/2");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("beam").feature("csd1").label("\u6a2a\u622a\u9762\u6570\u636e - \u67f1");
    model.component("comp1").physics("beam").feature("csd1").set("SectionType", "BoxSection");
    model.component("comp1").physics("beam").feature("csd1").set("hy_box", "300[mm]");
    model.component("comp1").physics("beam").feature("csd1").set("hz_box", "200[mm]");
    model.component("comp1").physics("beam").feature("csd1").set("ty_box", "10[mm]");
    model.component("comp1").physics("beam").feature("csd1").set("tz_box", "10[mm]");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{1, 0, 0});
    model.component("comp1").physics("beam").create("csd2", "CrossSectionBeam", 1);
    model.component("comp1").physics("beam").feature("csd2")
         .label("\u6a2a\u622a\u9762\uff1a\u6c34\u5e73 X (HEA260)");
    model.component("comp1").physics("beam").feature("csd2").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("beam").feature("csd2").set("SectionType", "H_Profile");
    model.component("comp1").physics("beam").feature("csd2").set("hy_H", "250[mm]");
    model.component("comp1").physics("beam").feature("csd2").set("hz_H", "260[mm]");
    model.component("comp1").physics("beam").feature("csd2").set("ty_H", "12.5[mm]");
    model.component("comp1").physics("beam").feature("csd2").set("tz_H", "7.5[mm]");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("vector_beam", new int[]{0, 0, 1});
    model.component("comp1").physics("beam").feature().duplicate("csd3", "csd2");
    model.component("comp1").physics("beam").feature("csd3")
         .label("\u6a2a\u622a\u9762\uff1a\u6c34\u5e73 Y (HEA220)");
    model.component("comp1").physics("beam").feature("csd3").selection().named("geom1_csel3_edg");
    model.component("comp1").physics("beam").feature("csd3").set("hy_H", "210[mm]");
    model.component("comp1").physics("beam").feature("csd3").set("hz_H", "220[mm]");
    model.component("comp1").physics("beam").feature("csd3").set("ty_H", "11[mm]");
    model.component("comp1").physics("beam").feature("csd3").set("tz_H", "7[mm]");
    model.component("comp1").physics("beam").create("adm1", "AddedMass1", 1);
    model.component("comp1").physics("beam").feature("adm1").label("\u9644\u52a0\u8d28\u91cf - \u6c34\u5e73 X");
    model.component("comp1").physics("beam").feature("adm1").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("beam").feature("adm1")
         .set("mPerLine", new int[]{1000, 0, 0, 0, 1000, 0, 0, 0, 1000});
    model.component("comp1").physics("beam").feature().duplicate("adm2", "adm1");
    model.component("comp1").physics("beam").feature("adm2").label("\u9644\u52a0\u8d28\u91cf - \u6c34\u5e73 Y");
    model.component("comp1").physics("beam").feature("adm2").selection().named("geom1_csel3_edg");
    model.component("comp1").physics("beam").create("fix1", "Fixed", 0);
    model.component("comp1").physics("beam").feature("fix1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("beam").create("gacc1", "GravityAcceleration", -1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
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
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
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
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
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
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
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
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
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
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/beam", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u91cd\u529b");
    model.study("std2").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1beam", "Beam");
    model.result().dataset("dset1beam").set("data", "dset1");
    model.result().dataset("dset1beam").set("physicsinterface", "beam");
    model.result().dataset("dset1beam").set("refinement", 2);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1beam");
    model.result("pg3").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (beam)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"beam.misesS"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"beam.uS", "beam.vS", "beam.wS"});
    model.result("pg3").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1beam");
    model.result("pg4").label("\u51e0\u4f55\u548c\u65b9\u5411 (beam)");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"mod(beam.csIndex*3, 10)"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("surf1").set("coloring", "colortable");
    model.result("pg4").feature("surf1").set("colortable", "Cyclic");
    model.result("pg4").feature("surf1").set("smooth", "none");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature("surf1").set("bandcount", 10);
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", -0.5);
    model.result("pg4").feature("surf1").set("rangecolormax", 9.5);
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.5);
    model.result("pg4").feature("surf1").feature("tran1").active(false);
    model.result("pg4").create("arws1", "ArrowLine");
    model.result("pg4").feature("arws1").set("data", "dset1");
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").feature("arws1").label("\u5c40\u90e8 Y \u65b9\u5411\uff08\u7eff\u8272\uff09");
    model.result("pg4").feature("arws1")
         .set("expr", new String[]{"beam.beamsys.e_y1", "beam.beamsys.e_y2", "beam.beamsys.e_y3"});
    model.result("pg4").feature("arws1").set("color", "green");
    model.result("pg4").feature("arws1").set("placement", "gausspoints");
    model.result("pg4").feature("arws1").active(false);
    model.result("pg4").create("arws2", "ArrowLine");
    model.result("pg4").feature("arws2").set("data", "dset1");
    model.result("pg4").feature("arws2").set("solutionparams", "parent");
    model.result("pg4").feature("arws2").label("\u5c40\u90e8 Z \u65b9\u5411\uff08\u84dd\u8272\uff09");
    model.result("pg4").feature("arws2")
         .set("expr", new String[]{"beam.beamsys.e_z1", "beam.beamsys.e_z2", "beam.beamsys.e_z3"});
    model.result("pg4").feature("arws2").set("color", "blue");
    model.result("pg4").feature("arws2").set("placement", "gausspoints");
    model.result("pg4").feature("arws2").set("inheritplot", "arws1");
    model.result("pg4").feature("arws2").set("inheritcolor", false);
    model.result("pg4").feature("arws2").set("inheritrange", false);
    model.result("pg4").feature("arws2").active(false);
    model.result("pg4").label("\u51e0\u4f55\u548c\u65b9\u5411 (beam)");
    model.result("pg4").run();
    model.result("pg3").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").label("\u91cd\u529b\u7ed3\u679c");

    model.study("std1").feature("eig").set("appnreigs", 600);
    model.study("std1").feature("eig").set("maxnreigs", 1000);
    model.study("std1").label("\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387");
    model.study("std1").create("cmbsol", "CombineSolution");
    model.study("std1").feature("cmbsol").label("\u79fb\u9664\u4f4e\u8d28\u91cf\u6a21\u6001");
    model.study("std1").feature("cmbsol").set("soloper", "remsol");
    model.study("std1").feature("cmbsol").set("excmethod", "implicit");
    model.study("std1").feature("cmbsol")
         .set("remsolfromexprexc", "(comp1.rsp1.mEffLX<comp1.rsp1.mass*massTol)&&(comp1.rsp1.mEffLY<comp1.rsp1.mass*massTol)&&(comp1.rsp1.mEffLZ<comp1.rsp1.mass*massTol)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"beam.disp"});
    model.result("pg5").feature("line1").set("threshold", "manual");
    model.result("pg5").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("line1").set("colortable", "Rainbow");
    model.result("pg5").feature("line1").set("colortabletrans", "none");
    model.result("pg5").feature("line1").set("colorscalemode", "linear");
    model.result("pg5").label("\u632f\u578b (beam)");
    model.result("pg5").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg5").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg5").feature("line1").set("tuberadiusscale", 1);
    model.result("pg5").feature("line1").set("tubeendcaps", false);
    model.result("pg5").feature("line1").create("def", "Deform");
    model.result("pg5").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387)");
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
    model.result().evaluationGroup("std1rsp1").set("data", "dset2");
    model.result().evaluationGroup("std1rsp1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387)");
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
    model.result("pg5").run();

    model.study("std1").feature("cmbsol").set("removesol", "sol2");
    model.study("std1").feature("cmbsol").set("removesoluse", "sol3");

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u603b\u6a21\u6001\u8d28\u91cf");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"rsp1.mEffLX"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb"});
    model.result().numerical("gev1").set("unit", new String[]{"kg"});
    model.result().numerical("gev1").setIndex("expr", "rsp1.mEffLY", 1);
    model.result().numerical("gev1").setIndex("expr", "rsp1.mEffLZ", 2);
    model.result().numerical("gev1").set("dataseries", "integral");
    model.result().numerical("gev1").set("dataseriesmethod", "summation");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u603b\u6a21\u6001\u8d28\u91cf");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u771f\u5b9e\u8d28\u91cf");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("looplevelinput", "first", 0);
    model.result().numerical("gev2").set("expr", new String[]{"rsp1.mass"});
    model.result().numerical("gev2").set("descr", new String[]{"\u8d28\u91cf"});
    model.result().numerical("gev2").set("unit", new String[]{"kg"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u771f\u5b9e\u8d28\u91cf");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("\u76f8\u5bf9\u6a21\u6001\u8d28\u91cf\u8d21\u732e");
    model.result().numerical("gev3").setIndex("expr", "rsp1.mEffLX/rsp1.mass", 0);
    model.result().numerical("gev3").setIndex("descr", "X \u65b9\u5411", 0);
    model.result().numerical("gev3").setIndex("expr", "rsp1.mEffLY/rsp1.mass", 1);
    model.result().numerical("gev3").setIndex("descr", "Y \u65b9\u5411", 1);
    model.result().numerical("gev3").setIndex("expr", "rsp1.mEffLZ/rsp1.mass", 2);
    model.result().numerical("gev3").setIndex("descr", "Z \u65b9\u5411", 2);
    model.result().numerical("gev3").set("data", "dset3");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u76f8\u5bf9\u6a21\u6001\u8d28\u91cf\u8d21\u732e");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("source", "table");
    model.result("pg6").feature("tblp1").set("table", "tbl3");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("linestyle", "none");
    model.result("pg6").feature("tblp1").set("linemarker", "cycle");
    model.result("pg6").feature("tblp1").set("legend", true);
    model.result("pg6").run();
    model.result("pg6").label("\u76f8\u5bf9\u6a21\u6001\u8d28\u91cf");
    model.result("pg6").set("legendpos", "middleright");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u76f8\u5bf9\u6a21\u6001\u8d28\u91cf");
    model.result("pg6").run();
    model.result("pg6").set("ylog", true);
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").create("arpt1", "ArrowPoint");
    model.result("pg5").feature("arpt1").set("inheritplot", "line1");
    model.result("pg5").feature("arpt1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{2});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{14});
    model.result("pg5").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg5");
    model.nodeGroup("grp3").add("plotgroup", "pg6");
    model.nodeGroup("grp3").label("\u7279\u5f81\u9891\u7387\u7ed3\u679c");

    model.result().dataset().create("rs1", "ResponseSpectrum3D");
    model.result().dataset("rs1").set("data", "dset2");
    model.result().dataset("rs1").set("spectrumfunof", "periodtime");
    model.result().dataset("rs1").set("primhorizspectrum", "pw1");
    model.result().dataset("rs1").set("sechorizspectrum", "pw1");
    model.result().dataset("rs1").set("vertspectrum", "pw2");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").create("line1", "Line");
    model.result("pg7").run();
    model.result("pg7").label("\u5e94\u529b\uff0cCQC \u65b9\u6cd5");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").set("data", "rs1");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u4f4d\u79fb\uff0cCQC \u65b9\u6cd5");
    model.result("pg8").run();
    model.result("pg8").feature("line1").set("unit", "mm");
    model.result("pg8").feature("line1").set("colortable", "Spectrum");
    model.result("pg8").run();

    model.group().create("lgRspXTot", "LoadGroup");
    model.group("lgRspXTot").label("\u8f7d\u8377\u7ec4\u603b\u8d28\u91cf X");
    model.group().create("lgRspXMode", "LoadGroup");
    model.group("lgRspXMode").label("\u8f7d\u8377\u7ec4\u6a21\u6001\u8d28\u91cf X");
    model.group().create("lgRspYTot", "LoadGroup");
    model.group("lgRspYTot").label("\u8f7d\u8377\u7ec4\u603b\u8d28\u91cf Y");
    model.group().create("lgRspYMode", "LoadGroup");
    model.group("lgRspYMode").label("\u8f7d\u8377\u7ec4\u6a21\u6001\u8d28\u91cf Y");
    model.group().create("lgRspZTot", "LoadGroup");
    model.group("lgRspZTot").label("\u8f7d\u8377\u7ec4\u603b\u8d28\u91cf Z");
    model.group().create("lgRspZMode", "LoadGroup");
    model.group("lgRspZMode").label("\u8f7d\u8377\u7ec4\u6a21\u6001\u8d28\u91cf Z");

    model.nodeGroup().create("lgRspGrp", "GlobalDefinitions");
    model.nodeGroup("lgRspGrp").set("type", "group");
    model.nodeGroup("lgRspGrp").label("\u4e22\u5931\u8d28\u91cf\u4fee\u6b63\u7684\u8f7d\u8377\u7ec4");
    model.nodeGroup("lgRspGrp").add("group", "lgRspXTot");
    model.nodeGroup("lgRspGrp").add("group", "lgRspXMode");
    model.nodeGroup("lgRspGrp").add("group", "lgRspYTot");
    model.nodeGroup("lgRspGrp").add("group", "lgRspYMode");
    model.nodeGroup("lgRspGrp").add("group", "lgRspZTot");
    model.nodeGroup("lgRspGrp").add("group", "lgRspZMode");

    model.study("std1").getSolverSequences("All");
    model.study().create("stdRsp");
    model.study("stdRsp").label("\u7814\u7a76\uff1a\u4e22\u5931\u8d28\u91cf\u8f7d\u8377\u5de5\u51b5");
    model.study("stdRsp").setGenPlots(false);
    model.study("stdRsp").create("cmbRspX", "CombineSolution");
    model.study("stdRsp").feature("cmbRspX").label("\u6a21\u6001\u8d28\u91cf\u603b\u548c X");
    model.study("stdRsp").create("cmbRspY", "CombineSolution");
    model.study("stdRsp").feature("cmbRspY").label("\u6a21\u6001\u8d28\u91cf\u603b\u548c Y");
    model.study("stdRsp").create("cmbRspZ", "CombineSolution");
    model.study("stdRsp").feature("cmbRspZ").label("\u6a21\u6001\u8d28\u91cf\u603b\u548c Z");
    model.study("stdRsp").create("statRsp", "Stationary");
    model.study("stdRsp").feature("statRsp").label("\u4e22\u5931\u8d28\u91cf\u9759\u8f7d\u8377\u5de5\u51b5");
    model.study("stdRsp").feature("cmbRspX").set("soloper", "wgtsum");
    model.study("stdRsp").feature("cmbRspX").set("weightoneexpr", "comp1.rsp1.pfLX");
    model.study("stdRsp").feature("cmbRspY").set("soloper", "wgtsum");
    model.study("stdRsp").feature("cmbRspY").set("weightoneexpr", "comp1.rsp1.pfLY");
    model.study("stdRsp").feature("cmbRspZ").set("soloper", "wgtsum");
    model.study("stdRsp").feature("cmbRspZ").set("weightoneexpr", "comp1.rsp1.pfLZ");
    model.study("stdRsp").feature("statRsp").set("useloadcase", true);
    model.study("stdRsp").feature("statRsp").setIndex("loadcase", "Total mass X", 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadcase", "Modal mass X", 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", true, 0, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 1, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 0, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", true, 1, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 0, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 1, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 0, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 0, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 1, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 1, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 0, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 0, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 1, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 1, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 0, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 0, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 1, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 1, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadcase", "Total mass Y", 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadcase", "Modal mass Y", 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 2, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 3, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 3, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 2, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 3, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 3, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", true, 2, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 2, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 3, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 3, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 2, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 2, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", true, 3, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 3, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 2, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 2, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 3, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 3, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 2, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 2, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 3, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 3, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadcase", "Total mass Z", 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadcase", "Modal mass Z", 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 4, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 4, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 5, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 5, 0);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 4, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 4, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 5, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 5, 1);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 4, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 4, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 5, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 5, 2);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 4, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 4, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 5, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 5, 3);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", true, 4, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 4, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 5, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 5, 4);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", false, 4, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 4, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroup", true, 5, 5);
    model.study("stdRsp").feature("statRsp").setIndex("loadgroupweight", "1.0", 5, 5);
    model.study("stdRsp").getSolverSequences("All");
    model.study("stdRsp").showAutoSequences("sol");
    model.study("stdRsp").getSolverSequences("All");
    model.study("stdRsp").feature("cmbRspX").set("cssolwghtsum", "sol2");
    model.study("stdRsp").feature("cmbRspY").set("cssolwghtsum", "sol2");
    model.study("stdRsp").feature("cmbRspZ").set("cssolwghtsum", "sol2");

    model.component("comp1").physics("beam").create("laftRspX", "LinearlyAcceleratedFrame");
    model.component("comp1").physics("beam").feature("laftRspX").label("\u603b\u8d28\u91cf\u8f7d\u8377 X");
    model.component("comp1").physics("beam").feature("laftRspX").selection().all();
    model.component("comp1").physics("beam").feature("laftRspX").set("af", new String[]{"1", "0", "0"});
    model.component("comp1").physics("beam").feature("laftRspX").set("loadGroup", "lgRspXTot");
    model.component("comp1").physics("beam").create("lafmRspX", "LinearlyAcceleratedFrame");
    model.component("comp1").physics("beam").feature("lafmRspX").label("\u6a21\u6001\u8d28\u91cf\u8f7d\u8377 X");
    model.component("comp1").physics("beam").feature("lafmRspX").selection().all();
    model.component("comp1").physics("beam").feature("lafmRspX")
         .set("af", new String[]{"-(withsol('sol5',u/1[m])-1)*1[m/s^2]", "-(withsol('sol5',v/1[m]))*1[m/s^2]", "-(withsol('sol5',w/1[m]))*1[m/s^2]"});
    model.component("comp1").physics("beam").feature("lafmRspX").set("loadGroup", "lgRspXMode");
    model.component("comp1").physics("beam").create("laftRspY", "LinearlyAcceleratedFrame");
    model.component("comp1").physics("beam").feature("laftRspY").label("\u603b\u8d28\u91cf\u8f7d\u8377 Y");
    model.component("comp1").physics("beam").feature("laftRspY").selection().all();
    model.component("comp1").physics("beam").feature("laftRspY").set("af", new String[]{"0", "1", "0"});
    model.component("comp1").physics("beam").feature("laftRspY").set("loadGroup", "lgRspYTot");
    model.component("comp1").physics("beam").create("lafmRspY", "LinearlyAcceleratedFrame");
    model.component("comp1").physics("beam").feature("lafmRspY").label("\u6a21\u6001\u8d28\u91cf\u8f7d\u8377 Y");
    model.component("comp1").physics("beam").feature("lafmRspY").selection().all();
    model.component("comp1").physics("beam").feature("lafmRspY")
         .set("af", new String[]{"-(withsol('sol6',u/1[m]))*1[m/s^2]", "-(withsol('sol6',v/1[m])-1)*1[m/s^2]", "-(withsol('sol6',w/1[m]))*1[m/s^2]"});
    model.component("comp1").physics("beam").feature("lafmRspY").set("loadGroup", "lgRspYMode");
    model.component("comp1").physics("beam").create("laftRspZ", "LinearlyAcceleratedFrame");
    model.component("comp1").physics("beam").feature("laftRspZ").label("\u603b\u8d28\u91cf\u8f7d\u8377 Z");
    model.component("comp1").physics("beam").feature("laftRspZ").selection().all();
    model.component("comp1").physics("beam").feature("laftRspZ").set("af", new String[]{"0", "0", "1"});
    model.component("comp1").physics("beam").feature("laftRspZ").set("loadGroup", "lgRspZTot");
    model.component("comp1").physics("beam").create("lafmRspZ", "LinearlyAcceleratedFrame");
    model.component("comp1").physics("beam").feature("lafmRspZ").label("\u6a21\u6001\u8d28\u91cf\u8f7d\u8377 Z");
    model.component("comp1").physics("beam").feature("lafmRspZ").selection().all();
    model.component("comp1").physics("beam").feature("lafmRspZ")
         .set("af", new String[]{"-(withsol('sol7',u/1[m]))*1[m/s^2]", "-(withsol('sol7',v/1[m]))*1[m/s^2]", "-(withsol('sol7',w/1[m])-1)*1[m/s^2]"});
    model.component("comp1").physics("beam").feature("lafmRspZ").set("loadGroup", "lgRspZMode");

    model.nodeGroup().create("mmRspGrpbeam", "Physics", "beam");
    model.nodeGroup("mmRspGrpbeam").label("\u4e22\u5931\u8d28\u91cf\u4fee\u6b63\u7684\u8f7d\u8377");
    model.nodeGroup("mmRspGrpbeam").add("lafmRspZ");
    model.nodeGroup("mmRspGrpbeam").add("laftRspZ");
    model.nodeGroup("mmRspGrpbeam").add("lafmRspY");
    model.nodeGroup("mmRspGrpbeam").add("laftRspY");
    model.nodeGroup("mmRspGrpbeam").add("lafmRspX");
    model.nodeGroup("mmRspGrpbeam").add("laftRspX");

    model.study("stdRsp").feature("statRsp").set("useadvanceddisable", true);
    model.study("stdRsp").feature("statRsp").set("disabledphysics", new String[]{"beam/gacc1"});
    model.study("stdRsp").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().duplicate("rs2", "rs1");
    model.result().dataset("rs2").set("rigidmodes", "gupta");
    model.result().dataset("rs2").set("freqlimitperiodic", 10);
    model.result().dataset("rs2").set("freqlimitrigid", 20);
    model.result().dataset("rs2").set("zeroperiodaccfreq", 30);
    model.result().dataset("rs2").set("masscorrectiongupta", "missingmass");
    model.result().dataset("rs2").set("data2", "dset4");
    model.result("pg7").run();
    model.result().duplicate("pg9", "pg7");
    model.result().duplicate("pg10", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u5e94\u529b\uff0cCQC \u65b9\u6cd5\uff0c\u4e22\u5931\u8d28\u91cf\u4fee\u6b63");
    model.result("pg9").set("data", "rs2");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").label("\u4f4d\u79fb\uff0cCQC \u65b9\u6cd5\uff0c\u4e22\u5931\u8d28\u91cf\u4fee\u6b63");
    model.result("pg10").set("data", "rs2");
    model.result("pg10").run();
    model.result("pg8").run();

    model.title("\u5efa\u7b51\u7684\u5730\u9707\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u54cd\u5e94\u8c31\u5206\u6790\u6765\u9a8c\u8bc1\u5730\u9707\u4f5c\u7528\u4e0b\u5efa\u7b51\u7ed3\u6784\u7684\u5b8c\u6574\u6027\uff0c\u5176\u4e2d\u4f7f\u7528\u6881\u5355\u5143\u5c06\u5efa\u7b51\u4f5c\u4e3a\u94a2\u67b6\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u8ba1\u7b97\u4f4d\u79fb\u548c\u5e94\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("building_response_spectrum.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
