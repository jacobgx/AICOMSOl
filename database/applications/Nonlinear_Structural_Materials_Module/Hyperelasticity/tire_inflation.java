/*
 * tire_inflation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:33 by COMSOL 6.3.0.290. */
public class tire_inflation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("\u8f6e\u80ce\u622a\u9762");
    model.geom("part1").create("imp1", "Import");
    model.geom("part1").feature("imp1").set("filename", "tire_inflation.mphbin");
    model.geom("part1").run("imp1");
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").label("\u8f6e\u8f8b");
    model.geom("part2").geomRep("cadps");
    model.geom("part2").create("imp1", "Import");
    model.geom("part2").feature("imp1").set("filename", "wheel_rim.x_b");
    model.geom("part2").run("imp1");

    model.component("comp1").label("\u4e8c\u7ef4\u8f74\u5bf9\u79f0 [\u8f6e\u80ce]");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").run("pi1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").label("\u4e09\u7ef4 [\u8f6e\u8f8b]");

    model.component("comp2").geom("geom2").lengthUnit("cm");
    model.component("comp2").geom("geom2").geomRep("cadps");
    model.component("comp2").geom("geom2").create("pi1", "PartInstance");
    model.component("comp2").geom("geom2").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("pi1").set("part", "part2");
    model.component("comp2").geom("geom2").run("pi1");
    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").set("quickplane", "xz");
    model.component("comp2").geom("geom2").run("wp1");
    model.component("comp1").geom("geom1").create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").run("cro1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("cro1", 1, 2, 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("pi1", 80);
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"20", "-7E-2"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("del1", "pi1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("ls1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("par1(1)", 1);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("par1(2)", 1, 2, 6, 8, 10);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "tire_inflation_meshcontrol.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("imp1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("del2(2)", "imp1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").named("imp1");
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(7, 8, 10);
    model.component("comp1").pair("p1").destination().set(30, 31, 44, 46);

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u94a2 [\u80ce\u5708\u94a2\u4e1d]");
    model.material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.material("mat1").propertyGroup("def").set("density", "");
    model.material("mat1").propertyGroup("def").set("youngsmodulus", new String[]{"210[GPa]"});
    model.material("mat1").propertyGroup("def").set("poissonsratio", new String[]{"0.3"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"7800[kg/m^3]"});
    model.material().duplicate("mat2", "mat1");
    model.material("mat2").set("sys", "none");
    model.material("mat2").label("\u94a2 [\u7ef3\u7d22]");
    model.material("mat2").propertyGroup("def").set("youngsmodulus", new String[]{"170[GPa]"});
    model.material().create("mat3", "Common", "");
    model.material("mat3").label("\u6a61\u80f6 [\u4fa7\u58c1\u548c\u80ce\u9762]");
    model.material("mat3").propertyGroup().create("Yeoh", "Yeoh", "Yeoh");
    model.material("mat3").propertyGroup("Yeoh").set("c1YE", new String[]{"1[MPa]"});
    model.material("mat3").propertyGroup("Yeoh").set("c2YE", new String[]{"-0.3[MPa]"});
    model.material("mat3").propertyGroup("Yeoh").set("c3YE", new String[]{"0.1[MPa]"});
    model.material("mat3").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.material("mat3").propertyGroup("KG").set("K", new String[]{"100[MPa]"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"1100[kg/m^3]"});
    model.material().duplicate("mat4", "mat3");
    model.material("mat4").set("sys", "none");
    model.material("mat4").label("\u6a61\u80f6 [\u80ce\u5708]");
    model.material("mat4").propertyGroup("Yeoh").set("c1YE", new String[]{"1.6[MPa]"});
    model.material("mat4").propertyGroup("Yeoh").set("c2YE", new String[]{"-1.4[MPa]"});
    model.material("mat4").propertyGroup("Yeoh").set("c3YE", new String[]{"0.9[MPa]"});
    model.material().create("mat5", "Common", "");
    model.material("mat5").label("\u5f3a\u5316\u6a61\u80f6 [\u80ce\u4f53]");
    model.material("mat5").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely_isotropic");
    model.material("mat5").propertyGroup("def").set("density", new String[]{"1750[kg/m^3]"});
    model.material("mat5").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"7[GPa]", "25[MPa]"});
    model.material("mat5").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.45", "0.4"});
    model.material("mat5").propertyGroup("TransverseIsotropic").set("Gvect1", new String[]{"4[MPa]"});
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1")
         .label("\u6750\u6599\u94fe\u63a5\uff1a\u94a2 [\u80ce\u5708\u94a2\u4e1d]");
    model.component("comp1").material("matlnk1").selection().set(7);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u80ce\u5708\u94a2\u4e1d");
    model.component("comp1").selection("sel1").set(7);

    model.component("comp1").material("matlnk1").selection().named("sel1");
    model.component("comp1").material("matlnk1").set("family", "steel");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").set("link", "mat3");
    model.component("comp1").material("matlnk2")
         .label("\u6750\u6599\u94fe\u63a5\uff1a\u6a61\u80f6 [\u4fa7\u58c1\u548c\u80ce\u9762]");
    model.component("comp1").material("matlnk2").selection().set(2, 3, 5);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u4fa7\u58c1\u548c\u80ce\u9762");
    model.component("comp1").selection("sel2").set(2, 3, 5);

    model.component("comp1").material("matlnk2").selection().named("sel2");
    model.component("comp1").material("matlnk2").set("family", "rubber");
    model.component("comp1").material("matlnk2").set("color", "black");
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").label("\u6750\u6599\u94fe\u63a5\uff1a\u6a61\u80f6 [\u80ce\u5708]");
    model.component("comp1").material("matlnk3").selection().set(6);
    model.component("comp1").material("matlnk3").set("link", "mat4");

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u80ce\u5708\u6a61\u80f6");
    model.component("comp1").selection("sel3").set(6);

    model.component("comp1").material("matlnk3").selection().named("sel3");
    model.component("comp1").material("matlnk3").set("family", "rubber");
    model.component("comp1").material("matlnk3").set("color", "black");
    model.component("comp1").material().create("matlnk4", "Link");
    model.component("comp1").material("matlnk4")
         .label("\u6750\u6599\u94fe\u63a5\uff1a\u5f3a\u5316\u6a61\u80f6 [\u80ce\u4f53]");
    model.component("comp1").material("matlnk4").selection().set(4);
    model.component("comp1").material("matlnk4").set("link", "mat5");

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u80ce\u4f53");
    model.component("comp1").selection("sel4").set(4);

    model.component("comp1").material("matlnk4").selection().named("sel4");
    model.component("comp1").material("matlnk4").set("color", "gray");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u8d85\u5f39\u6027\u57df");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set("angletol", 5);
    model.component("comp1").selection("sel5").label("\u5c01\u95ed\u8fb9\u754c");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(3, 5, 6, 7, 13, 30, 45, 48);

    model.component("comp1").physics("solid").selection().set(2, 3, 4, 5, 6, 7);
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").selection().named("uni1");
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "Yeoh");
    model.component("comp1").physics("solid").feature("hmm1").set("Compressibility_Yeoh", "CompressibleUncoupled");
    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 2);
    model.component("comp1").physics("solid").feature("lemm2")
         .label("\u7ebf\u5f39\u6027\u6750\u6599 [\u80ce\u4f53]");
    model.component("comp1").physics("solid").feature("lemm2").selection().named("sel4");
    model.component("comp1").physics("solid").feature("lemm2").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").feature("lemm2").set("TransverseIsotropic", true);
    model.component("comp1").physics("solid").feature("lemm1")
         .label("\u7ebf\u5f39\u6027\u6750\u6599 [\u80ce\u5708\u94a2\u4e1d]");
    model.component("comp1").physics().create("cc", "CurvilinearCoordinates", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/cc", true);

    model.component("comp1").physics("cc").selection().named("sel4");
    model.component("comp1").physics("cc").prop("Settings").set("CreateBasis", true);
    model.component("comp1").physics("cc").create("diff1", "DiffusionMethod", 2);
    model.component("comp1").physics("cc").feature("diff1").create("inl1", "Inlet", 1);
    model.component("comp1").physics("cc").feature("diff1").feature("inl1").selection().set(21);
    model.component("comp1").physics("cc").feature("diff1").create("out1", "Outlet", 1);
    model.component("comp1").physics("cc").feature("diff1").feature("out1").selection().set(17);
    model.component("comp1").physics("solid").feature("lemm2").set("coordinateSystem", "cc_cs");

    model.param().set("dcord_belts", "0.5[mm]");
    model.param().descr("dcord_belts", "\u5e26\u675f\u5c42\u7ef3\u7d22\u7684\u76f4\u5f84");
    model.param().set("spcord_belts", "1.16[mm]");
    model.param().descr("spcord_belts", "\u5e26\u675f\u5c42\u7ef3\u7d22\u7684\u95f4\u8ddd");
    model.param().set("alpha_belt", "70[deg]");
    model.param().descr("alpha_belt", "\u5e26\u675f\u5c42\u7ef3\u7d22\u7684\u89d2\u5ea6");

    model.component("comp1").physics("solid").create("tl1", "ThinLayer", 1);
    model.component("comp1").physics("solid").feature("tl1").selection().set(57, 58, 59);

    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").label("\u5e26\u675f\u5c42");
    model.component("comp1").selection("sel6").set(57, 58, 59);

    model.component("comp1").physics("solid").feature("tl1").selection().named("sel6");
    model.component("comp1").physics("solid").feature("tl1").set("lth", "dcord_belts");
    model.component("comp1").physics("solid").feature("tl1").create("hmm1", "HyperelasticModel", 1);
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").selection().named("sel6");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").set("MaterialModel", "Yeoh");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1")
         .set("Compressibility_Yeoh", "CompressibleUncoupled");

    model.component("comp1").material().duplicate("matlnk5", "matlnk2");
    model.component("comp1").material("matlnk5")
         .label("\u6750\u6599\u94fe\u63a5\uff1a\u6a61\u80f6 [\u5e26\u675f\u5c42]");
    model.component("comp1").material("matlnk5").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk5").selection().named("sel6");

    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").create("fibt1", "FiberTL", 1);
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1")
         .label("\u7ea4\u7ef4 [\u7ef3\u7d22\uff0c\u5e26\u675f\u5c42 1]");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1")
         .set("HyperModelSelection", "LinearElastic");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1")
         .set("Fiber_material", "mat2");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1")
         .set("directionSelection", "userDefinedVector");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1")
         .set("direction", new String[]{"cos(alpha_belt)", "sin(alpha_belt)", "0"});
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1")
         .set("d", "dcord_belts");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1")
         .set("sp", "spcord_belts");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1").selection().set(57);

    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7").label("\u5e26\u675f\u5c42 1");
    model.component("comp1").selection("sel7").set(57);

    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt1").selection()
         .named("sel7");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature().duplicate("fibt2", "fibt1");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt2")
         .label("\u7ea4\u7ef4 [\u7ef3\u7d22\uff0c\u5e26\u675f\u5c42 2]");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt2").selection().set(58);

    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").geom(1);
    model.component("comp1").selection("sel8").label("\u5e26\u675f\u5c42 2");
    model.component("comp1").selection("sel8").set(58);

    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt2").selection()
         .named("sel8");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt2")
         .set("direction", new String[]{"-cos(alpha_belt)", "sin(alpha_belt)", "0"});
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature().duplicate("fibt3", "fibt2");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt3")
         .label("\u7ea4\u7ef4 [\u7ef3\u7d22\uff0c\u5e26\u675f\u5c42 3]");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt3").selection().set(59);

    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").geom(1);
    model.component("comp1").selection("sel9").label("\u5e26\u675f\u5c42 3");
    model.component("comp1").selection("sel9").set(59);

    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt3").selection()
         .named("sel9");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").feature("fibt3")
         .set("directionSelection", "x2");
    model.component("comp1").physics("solid").feature("tl1").create("rollt1", "RollerTL", 0);
    model.component("comp1").physics("solid").feature("tl1").feature("rollt1").selection().set(46, 48, 49);
    model.component("comp1").physics("solid").prop("Mode2Daxi").set("includeTwist", true);
    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("Sil", new String[]{"0", "0", "0", "0", "300[MPa]", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("fp_penalty", 2);

    model.param().set("p0", "200[kPa]");
    model.param().descr("p0", "\u5145\u6c14\u538b\u529b");
    model.param().set("para", "0");
    model.param().descr("para", "\u5145\u6c14\u7684\u8fde\u7eed\u53c2\u6570");

    model.component("comp1").physics("solid").create("enc1", "EnclosedCavity", 1);
    model.component("comp1").physics("solid").feature("enc1").selection().named("sel5");
    model.component("comp1").physics("solid").feature("enc1").set("allowExternalSelection", true);
    model.component("comp1").physics("solid").feature("enc1").set("volumeType", "openSurface");
    model.component("comp1").physics("solid").feature("enc1").selection("referencePoint").set(4);
    model.component("comp1").physics("solid").feature("enc1").set("fV", 2);
    model.component("comp1").physics("solid").feature("enc1").feature().remove("fl1");
    model.component("comp1").physics("solid").feature("enc1").create("pp1", "PrescribedPressure", 1);
    model.component("comp1").physics("solid").feature("enc1").feature("pp1")
         .set("p", "if(solid.incontact, 0, p0*para)");
    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(20, 21, 22, 23, 25, 26);
    model.component("comp1").physics("solid").feature("symp1").set("circumferentialCondition", "antisymmetry");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(2, 3, 4, 8, 19);
    model.component("comp1").mesh("mesh1").feature("fq1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").selection().set(15, 95, 96);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").selection().set(55, 56, 70);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").set("elemcount", 6);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").set("elemratio", 1.2);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis3").selection().set(45);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis3").set("elemcount", 15);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis3").set("elemratio", 1.4);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis3").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature().duplicate("dis4", "dis3");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis4").selection().set(71);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis4").set("elemratio", 1.6);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis4").set("reverse", false);
    model.component("comp1").mesh("mesh1").feature("fq1").feature().duplicate("dis5", "dis4");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis5").selection().set(72);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis5").set("elemratio", 1.8);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis6", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis6").selection().set(63);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis6").set("numelem", 6);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis6").selection().set(42, 43, 44, 51, 52, 63);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis7", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis7").selection().set(34, 37);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis7").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis8", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis8").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis8").set("elemratio", 1.5);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis8").selection().set(30);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis9", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis9").selection().set(32, 35);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis9").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis10", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis10").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis10").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis10").set("elemratio", 1.5);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis10").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis10").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis10").selection().set(31, 33, 36);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis11", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis11").selection().set(46, 53, 54);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis11").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis11").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis11").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis11").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ref1", "Refine");
    model.component("comp1").mesh("mesh1").feature("ref1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ref1").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").run("ref1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(5, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 39, 40, 41, 42, 43, 44, 45);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(24, 26, 27, 50, 59, 60, 61, 62);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(49);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(47);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(101, 102, 103);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("numelem", 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").selection().set(131);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis6", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").selection().set(130);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("elemcount", 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("elemratio", 1.2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("reverse", true);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(38);
    model.component("comp1").mesh("mesh1").feature("map2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(7, 20);
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 7);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(6);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(7, 8, 10);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.05);
    model.component("comp1").mesh("mesh1").run("edg1");
    model.component("comp1").mesh("mesh1").create("ftri3", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri3").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hauto", 9);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("rmd1", "RemoveDetails");

    return model;
  }

  public static Model run2(Model model) {

    model.component("comp2").mesh("mesh2").run();

    model.component("comp2").geom("geom2").run("rmd1");

    model.component("comp2").mesh("mesh2").autoMeshSize(4);

    model.study("std1").label("\u7814\u7a76\uff1a\u7ea4\u7ef4\u65b9\u5411");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledpair", new String[]{"p1"});
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u77e2\u91cf\u573a (cc)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("color", "red");
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
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u5750\u6807\u7cfb (cc)");
    model.result("pg2").create("sys1", "CoordSysSurface");
    model.result("pg2").feature("sys1").set("sys", "cc_cs");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("sys1").set("placement", "gausspoints");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u5145\u6c14");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").setSolveFor("/physics/cc", false);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "dcord_belts", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "dcord_belts", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0, 0.1, 1)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "cm", "cm"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").label("\u4f4d\u79fb (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").label("\u4f4d\u79fb (solid)");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormax", 15);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u9762\u5916\u4f4d\u79fb (solid)");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "v");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f6e\u80ce\u4f53\u79ef");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"solid.enc1.V"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u603b\u4f53\u79ef\uff0c\u53d8\u5f62\u6784\u578b"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"m^3"});
    model.result("pg5").feature("glob1").setIndex("unit", "l", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "p0*para");
    model.result("pg5").feature("glob1").set("xdataunit", "bar");
    model.result("pg5").feature("glob1").set("xdatadescractive", true);
    model.result("pg5").feature("glob1").set("xdatadescr", "\u538b\u529b");
    model.result().setOnlyPlotWhenRequested(true);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u8f6e\u80ce");
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().set(2, 3, 4, 5, 6);
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").label("\u4e09\u7ef4\u955c\u50cf\uff1a\u8f6e\u80ce");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result().dataset("mir1").set("quickz", "-7E-2");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u4e09\u7ef4\u5e94\u529b (solid)");
    model.result("pg6").set("data", "mir1");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "von Mises \u5e94\u529b (MPa)");
    model.result("pg6").set("paramindicator", "para=eval(para)");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "solid.misesGp");
    model.result("pg6").feature("vol1").set("rangecoloractive", true);
    model.result("pg6").feature("vol1").set("rangecolormax", 180);
    model.result("pg6").feature("vol1").set("colortable", "Prism");
    model.result("pg6").feature("vol1").label("\u8f6e\u80ce");
    model.result().dataset().duplicate("rev2", "rev1");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u80ce\u5708\u94a2\u4e1d");
    model.result().dataset("rev2").set("revangle", 275);
    model.result().dataset("rev2").selection().named("sel1");
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").label("\u4e09\u7ef4\u955c\u50cf\uff1a\u80ce\u5708\u94a2\u4e1d");
    model.result().dataset("mir2").set("data", "rev2");
    model.result("pg6").feature().duplicate("vol2", "vol1");
    model.result("pg6").feature("vol2").label("\u80ce\u5708\u94a2\u4e1d");
    model.result("pg6").feature("vol2").set("data", "mir2");
    model.result("pg6").feature("vol2").set("solutionparams", "parent");
    model.result("pg6").feature("vol2").set("inheritplot", "vol1");
    model.result().dataset().duplicate("rev3", "rev2");
    model.result().dataset("rev3").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u5e26\u675f\u5c42 1");
    model.result().dataset("rev3").set("startangle", 135);
    model.result().dataset("rev3").set("revangle", 40);
    model.result().dataset("rev3").set("layermethod", "fine");
    model.result().dataset("rev3").selection().geom("geom1", 1);
    model.result().dataset("rev3").selection().named("sel7");
    model.result().dataset().duplicate("mir3", "mir2");
    model.result().dataset("mir3").label("\u4e09\u7ef4\u955c\u50cf\uff1a\u5e26\u675f\u5c42 1");
    model.result().dataset("mir3").set("hasvar", true);
    model.result().dataset("mir3").set("data", "rev3");
    model.result("pg6").create("str1", "StreamlineSurface");
    model.result("pg6").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("str1").label("\u5e26\u675f\u5c42 1");
    model.result("pg6").feature("str1").set("data", "mir3");
    model.result("pg6").feature("str1").set("solutionparams", "parent");
    model.result("pg6").feature("str1")
         .set("expr", new String[]{"solid.tl1.hmm1.fibt1.a0R*((mir1side*2)-1)", "v", "w"});
    model.result("pg6").feature("str1").setIndex("expr", "solid.tl1.hmm1.fibt1.a0PHI", 1);
    model.result("pg6").feature("str1").setIndex("expr", "solid.tl1.hmm1.fibt1.a0Z*((mir1side*2)-1)", 2);
    model.result("pg6").feature("str1").selection().set(5);
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("udist", 0.02);
    model.result("pg6").feature("str1").set("linetype", "tube");
    model.result("pg6").feature("str1").set("radiusexpr", "solid.tl1.hmm1.fibt1.d/2");
    model.result("pg6").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg6").feature("str1").set("tuberadiusscale", 2);
    model.result("pg6").feature("str1").set("inheritplot", "vol1");
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("expr", "solid.tl1.hmm1.fibt1.mises");
    model.result().dataset().duplicate("rev4", "rev3");
    model.result().dataset("rev4").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u5e26\u675f\u5c42 2");
    model.result().dataset("rev4").set("revangle", 30);
    model.result().dataset("rev4").selection().named("sel8");
    model.result().dataset().duplicate("mir4", "mir3");
    model.result().dataset("mir4").label("\u4e09\u7ef4\u955c\u50cf\uff1a\u5e26\u675f\u5c42 2");
    model.result().dataset("mir4").set("data", "rev4");
    model.result("pg6").feature().duplicate("str2", "str1");
    model.result("pg6").feature("str2").label("\u5e26\u675f\u5c42 2");
    model.result("pg6").feature("str2").set("data", "mir4");
    model.result("pg6").feature("str2").setIndex("expr", "solid.tl1.hmm1.fibt2.a0R*((mir1side*2)-1)", 0);
    model.result("pg6").feature("str2").setIndex("expr", "solid.tl1.hmm1.fibt2.a0PHI", 1);
    model.result("pg6").feature("str2").setIndex("expr", "solid.tl1.hmm1.fibt2.a0Z*((mir1side*2)-1)", 2);
    model.result("pg6").feature("str2").set("radiusexpr", "solid.tl1.hmm1.fibt2.d/2");
    model.result("pg6").feature("str2").feature("col1").set("expr", "solid.tl1.hmm1.fibt2.mises");
    model.result("pg6").run();
    model.result().dataset().duplicate("rev5", "rev4");
    model.result().dataset("rev5").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u5e26\u675f\u5c42 3");
    model.result().dataset("rev5").set("revangle", 20);
    model.result().dataset("rev5").selection().named("sel9");
    model.result().dataset().duplicate("mir5", "mir4");
    model.result().dataset("mir5").set("data", "rev5");
    model.result().dataset("mir5").label("\u4e8c\u7ef4\u955c\u50cf\uff1a\u5e26\u675f\u5c42 3");
    model.result("pg6").feature().duplicate("str3", "str2");
    model.result("pg6").feature("str3").label("\u5e26\u675f\u5c42 3");
    model.result("pg6").feature("str3").set("data", "mir5");
    model.result("pg6").feature("str3").setIndex("expr", "solid.tl1.hmm1.fibt3.a0R", 0);
    model.result("pg6").feature("str3").setIndex("expr", "solid.tl1.hmm1.fibt3.a0PHI", 1);
    model.result("pg6").feature("str3").setIndex("expr", "solid.tl1.hmm1.fibt3.a0Z", 2);
    model.result("pg6").feature("str3").set("udist", 0.008);
    model.result("pg6").feature("str3").set("uadv", "manual");
    model.result("pg6").feature("str3").set("udistend", 0.1);
    model.result("pg6").feature("str3").set("radiusexpr", "solid.tl1.hmm1.fibt3.d/2");
    model.result("pg6").feature("str3").feature("col1").set("expr", "solid.tl1.hmm1.fibt3.mises");
    model.result("pg6").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").label("\u8f6e\u8f8b\u51e0\u4f55");
    model.result().dataset("dset3").set("comp", "comp2");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8f6e\u8f8b");
    model.result("pg6").feature("surf1").set("data", "dset3");
    model.result("pg6").feature("surf1").set("expr", "1");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("resolution", "fine");
    model.result("pg6").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").label("\u6750\u6599\u548c\u7ea4\u7ef4\u65b9\u5411");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").feature("vol1").set("expr", "1");
    model.result("pg7").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").feature("vol1").feature("mtrl1").set("material", "matlnk2");
    model.result("pg7").run();
    model.result("pg7").feature("vol2").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("str1").feature().remove("col1");
    model.result("pg7").feature("str1").set("inheritplot", "none");
    model.result("pg7").feature("str1").set("color", "custom");
    model.result("pg7").feature("str1")
         .set("customcolor", new double[]{0.9921568632125854, 0.7254902124404907, 0.07450980693101883});
    model.result("pg7").feature("str2").feature().remove("col1");
    model.result("pg7").feature("str2").set("inheritplot", "none");
    model.result("pg7").feature("str2").set("color", "custom");
    model.result("pg7").feature("str2")
         .set("customcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg7").feature("str3").feature().remove("col1");
    model.result("pg7").feature("str3").set("inheritplot", "none");
    model.result("pg7").feature("str3").set("color", "custom");
    model.result("pg7").feature("str3")
         .set("customcolor", new double[]{0.6509804129600525, 0.8392156958580017, 0.8156862854957581});
    model.result().dataset().duplicate("rev6", "rev3");
    model.result().dataset("rev6").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u80ce\u4f53");
    model.result().dataset("rev6").set("revangle", 50);
    model.result().dataset("rev6").selection().geom("geom1", 1);
    model.result().dataset("rev6").selection().set(35, 36, 37, 42, 52, 53, 56);
    model.result().dataset().duplicate("mir6", "mir3");
    model.result().dataset("mir6").label("\u4e09\u7ef4\u955c\u50cf\uff1a\u80ce\u4f53");
    model.result().dataset("mir6").set("data", "rev6");
    model.result("pg7").feature().duplicate("str4", "str1");
    model.result("pg7").feature("str4").label("\u80ce\u4f53");
    model.result("pg7").feature("str4").set("data", "mir6");
    model.result("pg7").feature("str4").set("expr", new String[]{"cc.e1R", "cc.e1PHI", "cc.e1Z"});
    model.result("pg7").feature("str4").set("descr", "\u7b2c\u4e00\u57fa\u77e2");
    model.result("pg7").feature("str4").setIndex("expr", "cc.e1R*((mir1side*2)-1)", 0);
    model.result("pg7").feature("str4").setIndex("expr", "cc.e1Z*((mir1side*2)-1)", 2);
    model.result("pg7").feature("str4").selection().set(4);
    model.result("pg7").feature("str4").set("udist", 0.01);
    model.result("pg7").feature("str4").set("radiusexpr", "dcord_belts/2");
    model.result("pg7").feature("str4").set("color", "red");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().setOnlyPlotWhenRequested(false);

    model.view().create("view8", 3);
    model.view("view8").set("showgrid", false);
    model.view("view8").set("showaxisorientation", false);
    model.view("view8").set("ssao", true);
    model.view("view8").set("shadowmapping", true);
    model.view("view8").set("environmentmap", "envmap_meadow");
    model.view("view8").set("flooreffect", true);
    model.view("view8").camera().set("zoomanglefull", 5);
    model.view("view8").camera().setIndex("position", -263, 0);
    model.view("view8").camera().setIndex("position", -195, 1);
    model.view("view8").camera().set("position", new int[]{-263, -195, 373});
    model.view("view8").camera().setIndex("target", 0.78, 0);
    model.view("view8").camera().setIndex("target", 0, 1);
    model.view("view8").camera().set("target", new double[]{0.78, 0, -0.04});
    model.view("view8").camera().setIndex("up", -0.7, 0);
    model.view("view8").camera().setIndex("up", 0.71, 1);
    model.view("view8").camera().set("up", new double[]{-0.7, 0.71, -0.12});
    model.view("view8").camera().setIndex("rotationpoint", 0.78, 0);
    model.view("view8").camera().setIndex("rotationpoint", -0.005, 1);
    model.view("view8").camera().set("rotationpoint", new double[]{0.78, -0.005, 0.044});

    model.result("pg7").run();
    model.result("pg7").set("view", "view8");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("str1").create("vis1", "VisualEffects");
    model.result("pg7").run();
    model.result("pg7").feature("str1").feature("vis1").set("lighting", false);
    model.result("pg7").feature("str1").feature("vis1").set("ssaomode", "manual");
    model.result("pg7").feature("str1").feature("vis1").set("ssaocasts", false);
    model.result("pg7").feature("str1").feature("vis1").set("ssaoreceives", false);
    model.result("pg7").feature("str1").feature("vis1").set("directmode", "manual");
    model.result("pg7").feature("str1").feature("vis1").set("directcasts", false);
    model.result("pg7").feature("str1").feature("vis1").set("directreceives", false);
    model.result("pg7").run();
    model.result("pg7").feature("str2").feature().copy("vis1", "pg7/str1/vis1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("str3").feature().copy("vis1", "pg7/str1/vis1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("str4").feature().copy("vis1", "pg7/str1/vis1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").set("view", "view8");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").feature("str1").feature().copy("vis1", "pg7/str1/vis1");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").feature("str2").feature().copy("vis1", "pg7/str1/vis1");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").feature("str3").feature().copy("vis1", "pg7/str1/vis1");
    model.result("pg6").run();
    model.result().dataset("rev1").set("startangle", "-90+180");
    model.result().dataset("rev1").set("revangle", "225-180");
    model.result().dataset("rev2").set("startangle", "-90+180");
    model.result().dataset("rev2").set("revangle", "275-180");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").active(false);
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").active(false);
    model.result("pg6").run();
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 275);
    model.result("pg7").run();
    model.result("pg7").feature("surf1").active(true);
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").active(true);

    model.component("comp1").selection("sel3").set("color", "custom");
    model.component("comp1").selection("sel3")
         .set("customcolor", new double[]{0.6352941393852234, 0.9411764740943909, 0.01568627543747425});
    model.component("comp1").selection("sel4").set("color", "9");
    model.component("comp1").selection("sel1").set("color", "4");
    model.component("comp1").selection("sel2").set("color", "custom");
    model.component("comp1").selection("sel2")
         .set("customcolor", new double[]{0.6509804129600525, 0.9411764740943909, 0.9921568632125854});
    model.component("comp1").selection("sel3").set("color", "none");
    model.component("comp1").selection("sel4").set("color", "none");
    model.component("comp1").selection("sel1").set("color", "none");
    model.component("comp1").selection("sel2").set("color", "none");

    model.result("pg7").run();

    model.title("\u8f6e\u80ce\u5145\u6c14");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u8584\u5c42\u7ea4\u7ef4\u6765\u6a21\u62df\u5d4c\u5165\u56fa\u4f53\u4e2d\u7684\u5404\u5411\u5f02\u6027\u8584\u590d\u5408\u6750\u6599\uff0c\u800c\u65e0\u9700\u660e\u786e\u7ed8\u5236\u6750\u6599\u5c42\u6216\u589e\u5f3a\u7ea4\u7ef4\u3002\u5177\u4f53\u800c\u8a00\uff0c\u5b83\u7528\u4e8e\u6a21\u62df\u8f6e\u80ce\u5e26\u675f\u4e2d\u7684\u94a2\u4e1d\u5e18\u7ebf\uff0c\u4e3a\u80ce\u9762\u4e0b\u65b9\u7684\u8f6e\u80ce\u63d0\u4f9b\u7ed3\u6784\u652f\u6491\u3002\u6b64\u5916\uff0c\u672c\u4f8b\u91c7\u7528\u66f2\u7ebf\u5750\u6807\u7cfb\u6765\u5b9a\u4e49\u80ce\u4f53\u5e18\u5e03\u5c42\u7684\u5404\u5411\u5f02\u6027\u6750\u6599\u5c5e\u6027\u3002\u8be5\u6a21\u578b\u8fd8\u5229\u7528\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u4e2d\u7684\u201c\u626d\u8f6c\u201d\u81ea\u7531\u5ea6\uff0c\u4ee5\u6355\u83b7\u5145\u6c14\u8fc7\u7a0b\u4e2d\u94a2\u4e1d\u5e18\u7ebf\u65b9\u5411\u5f15\u8d77\u7684\u65b9\u4f4d\u4f4d\u79fb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("tire_inflation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
