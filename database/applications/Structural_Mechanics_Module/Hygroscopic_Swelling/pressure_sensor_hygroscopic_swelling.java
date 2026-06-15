/*
 * pressure_sensor_hygroscopic_swelling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:10 by COMSOL 6.3.0.290. */
public class pressure_sensor_hygroscopic_swelling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Hygroscopic_Swelling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("ts", "TransportInSolids", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/shell", true);
    model.study("std1").feature("time").setSolveFor("/physics/ts", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("l", "20[mm]", "\u8bbe\u5907\u957f\u5ea6");
    model.param().set("w", "15[mm]", "\u8bbe\u5907\u5bbd\u5ea6");
    model.param().set("l_MC", "15[mm]", "\u73af\u6c27\u6a21\u5851\u6599\u957f\u5ea6");
    model.param().set("w_MC", "10[mm]", "\u73af\u6c27\u6a21\u5851\u6599\u5bbd\u5ea6");
    model.param().set("l_die", "1.2[mm]", "\u538b\u529b\u4f20\u611f\u5668\u8fb9\u957f");
    model.param().set("l_memb", "700[um]", "\u7845\u819c\u8fb9\u957f");
    model.param().set("l_hole", "800[um]", "FR4 \u4e2d\u5b54\u7684\u8fb9\u957f");
    model.param().set("t_FR4", "1[mm]", "FR4 \u539a\u5ea6");
    model.param().set("t_Si", "200[um]", "\u7845\u539a\u5ea6");
    model.param().set("t_memb", "20[um]", "\u7845\u819c\u539a\u5ea6");
    model.param().set("t_glass", "200[um]", "\u77f3\u82f1\u73bb\u7483\u6676\u7247\u539a\u5ea6");
    model.param().set("t_MC", "1.5[mm]", "\u5851\u5c01\u6750\u6599\u539a\u5ea6");
    model.param().set("cmax", "140[mol/m^3]", "\u5851\u5c01\u6750\u6599\u7684\u9971\u548c\u6d53\u5ea6");
    model.param().set("cini", "40[mol/m^3]", "\u521d\u59cb\u6c34\u5206\u6d53\u5ea6");
    model.param().set("pext", "1[bar]", "\u5916\u538b");
    model.param().set("t", "0[s]", "\u53c2\u6570\u5316\u626b\u63cf\u6240\u7528\u7684\u65f6\u95f4");
    model.param().set("Dc", "4e-13[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().label("\u6a21\u578b\u53c2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"l_die/2", "l_die/2", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "t_Si+t_glass", 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "t_Si", 0);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"l_MC/2", "w_MC/2", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "t_MC", 2);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"l_memb/2", "l_memb/2", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "t_Si", 2);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1", "blk2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"l/2", "w/2", "t_FR4"});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new String[]{"0", "0", "-t_FR4"});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"l_hole/2", "l_hole/2", "1"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("size", "t_FR4", 2);
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new String[]{"0", "0", "-t_FR4"});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("blk4");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("blk5");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"l_memb/2", "l_memb/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("FR4");
    model.component("comp1").selection("sel1").set(4);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7845");
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u73bb\u7483");
    model.component("comp1").selection("sel3").set(1);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5851\u5c01\u6750\u6599");
    model.component("comp1").selection("sel4").set(2);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u819c");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(1);

    model.component("comp1").physics("shell").selection().named("sel5");
    model.component("comp1").physics("shell").prop("z").set("z", -1);
    model.component("comp1").physics("shell").feature("to1").set("d", "t_memb");
    model.component("comp1").physics("shell").feature("to1").set("OffsetDefinition", "bottom");
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("shell").feature("fl1").selection().named("sel5");
    model.component("comp1").physics("shell").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("shell").feature("fl1").set("pressure", "-pext");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 2);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(15);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(2, 3, 5, 6, 9, 13, 24, 26);

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "0.5[h]");
    model.component("comp1").func("step1").set("smooth", "1[h]");

    model.component("comp1").physics("ts").selection().named("sel4");
    model.component("comp1").physics("ts").feature("solid1")
         .set("D_c", new String[]{"Dc", "0", "0", "0", "Dc", "0", "0", "0", "Dc"});
    model.component("comp1").physics("ts").feature("init1").setIndex("initc", "cini", 0);
    model.component("comp1").physics("ts").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ts").feature("sym1").selection().set(5, 6);
    model.component("comp1").physics("ts").create("ptq1", "PrescribedTransportedQuantity", 2);
    model.component("comp1").physics("ts").feature("ptq1").selection().set(8, 20, 29);
    model.component("comp1").physics("ts").feature("ptq1").setIndex("species", true, 0);
    model.component("comp1").physics("ts").feature("ptq1").setIndex("c0", "cini+(cmax-cini)*step1(t)", 0);

    model.component("comp1").multiphysics().create("sas1", "ShrinkageAndSwelling", 3);
    model.component("comp1").multiphysics("sas1").selection().named("sel4");
    model.component("comp1").multiphysics("sas1").set("c_ref_c", "cini");
    model.component("comp1").multiphysics("sas1").set("OmegaV_c", "3.3e-4[m^3/kg]*(0.018[kg/mol])");
    model.component("comp1").multiphysics("sas1").set("Mm_c", "0.018[kg/mol]");
    model.component("comp1").multiphysics("sas1").set("IncludeAsMass", true);
    model.component("comp1").multiphysics().create("sshc1", "SolidShellConnection", -1);
    model.component("comp1").multiphysics("sshc1").set("selectionControl", true);
    model.component("comp1").multiphysics("sshc1").selection("edgBndSolidSelection").set(10, 23);

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").selection().named("sel4");
    model.component("comp1").massProp("mass1").set("densitySource", "fromPhysics");

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").label("Silicon");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.7);
    model.component("comp1").material("mat2").set("roughness", 0.5);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").label("Silicon 1");
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
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat4").label("Silica glass");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("fresnel", 0.99);
    model.component("comp1").material("mat4").set("roughness", 0.02);
    model.component("comp1").material("mat4").set("diffusewrap", 0);
    model.component("comp1").material("mat4").set("reflectance", 0);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat3").label("\u7845\uff08\u819c\uff09");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().named("sel5");
    model.component("comp1").material("mat4").selection().named("sel3");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u5851\u5c01\u6750\u6599");
    model.component("comp1").material("mat5").selection().named("sel4");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", new String[]{"22[GPa]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"1900"});
    model.component("comp1").material("mat5").set("color", "black");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel5");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(11, 16);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(8, 20, 29);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "0 10^range(2,0.1,7.5)");
    model.study("std1").feature("time").setSolveFor("/physics/solid", false);
    model.study("std1").feature("time").setSolveFor("/physics/shell", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ts", false);
    model.study("std1").feature("stat").set("usesol", true);
    model.study("std1").feature("stat").set("notsolmethod", "sol");
    model.study("std1").feature("stat").set("notstudy", "std1");
    model.study("std1").feature("stat").set("notsolnum", "all");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "l", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "l", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 10^range(2,0.1,7.5)", 0);
    model.study("std1").showAutoSequences("all");

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "mm", "mm"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b5m", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u6d53\u5ea6");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "ts.c");
    model.result("pg1").feature("surf1").set("descr", "\u6d53\u5ea6 c");
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", 40);
    model.result("pg1").feature("surf1").set("rangecolormax", 140);

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 39, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u82af\u7247\u4f4d\u7f6e\u7684\u6d53\u5ea6");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(3);
    model.result("pg2").feature("ptgr1").set("expr", "ts.c");
    model.result("pg2").feature("ptgr1").set("descr", "\u6d53\u5ea6 c");
    model.result("pg2").feature("ptgr1").set("xdataparamunit", "d");
    model.result("pg2").run();
    model.result("pg2").set("xlog", true);
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u65f6\u95f4\uff08\u5929\u6570\uff09");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8d28\u91cf\u5438\u6536");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "(mass1.mass-at(0,mass1.mass))/at(0,mass1.mass)*100", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u76f8\u5bf9\u8d28\u91cf\u5438\u6536 (%)", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "sqrt(t[1/d])");
    model.result("pg3").feature("glob1").set("xdatadescractive", true);
    model.result("pg3").feature("glob1").set("xdatadescr", "(time)^(1/2) [d^(1/2)]");
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u65f6\u95f4\u7684\u5e73\u65b9\u6839\uff08\u5929\u6570^1/2\uff09");
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 0);
    model.result("pg3").set("xmax", 15);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 57, 0);
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").run();
    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 6, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 6, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().dataset("dset1shellshl")
         .set("defaultPlotIDs", new String[]{"stress|shell", "displacement|shell", "shellGeometry|shell|surf1"});

    return model;
  }

  public static Model run2(Model model) {
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1shellshl");
    model.result("pg5").setIndex("looplevel", 57, 0);
    model.result("pg5").label("\u5e94\u529b (shell)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg5").label("\u5e94\u529b (shell)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b");
    model.result("pg4").feature().copy("surf1", "pg5/surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").set("inheritplot", "vol1");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("vol1").feature("def").set("scale", 600);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 57, 0);
    model.result("pg5").label("\u4f4d\u79fb (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg5").feature("vol1").set("threshold", "manual");
    model.result("pg5").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg5").feature("vol1").set("colortabletrans", "none");
    model.result("pg5").feature("vol1").set("colorscalemode", "linear");
    model.result("pg5").feature("vol1").set("resolution", "custom");
    model.result("pg5").feature("vol1").set("refine", 2);
    model.result("pg5").feature("vol1").create("def", "Deform");
    model.result("pg5").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").label("\u4f4d\u79fb (solid)");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("vol1").feature("def").set("scale", 600);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("vol2", "vol1");
    model.result("pg5").run();
    model.result("pg5").feature("vol2").set("data", "dset1shellshl");
    model.result("pg5").feature("vol2").set("solutionparams", "parent");
    model.result("pg5").feature("vol2").set("expr", "shell.disp");
    model.result("pg5").feature("vol2").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg5").feature("vol2").set("titletype", "none");
    model.result("pg5").feature("vol2").set("inheritplot", "vol1");
    model.result("pg5").run();
    model.result("pg5").feature("vol2").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg5").feature("vol2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 39, 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5e94\u53d8");
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", "l_memb/2-30[\u00b5m]");
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().dataset("cpt1").set("snapping", "boundary");
    model.result().dataset().create("cpt2", "CutPoint3D");
    model.result().dataset("cpt2").set("pointx", 0);
    model.result().dataset("cpt2").set("pointy", "l_memb/2-30[\u00b5m]");
    model.result().dataset("cpt2").set("pointz", 0);
    model.result().dataset("cpt2").set("snapping", "boundary");
    model.result("pg6").run();
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").set("data", "cpt1");
    model.result("pg6").feature("ptgr1").set("expr", "shell.eXX");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "t");
    model.result("pg6").feature("ptgr1").set("xdataunit", "d");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1").setIndex("legends", "X \u8f74\u7684 XX \u5e94\u53d8", 0);
    model.result("pg6").run();
    model.result("pg6").create("ptgr2", "PointGraph");
    model.result("pg6").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr2").set("linewidth", "preference");
    model.result("pg6").feature("ptgr2").set("data", "cpt2");
    model.result("pg6").feature("ptgr2").set("expr", "shell.eYY");
    model.result("pg6").feature("ptgr2").set("xdata", "expr");
    model.result("pg6").feature("ptgr2").set("xdataexpr", "t");
    model.result("pg6").feature("ptgr2").set("xdataunit", "d");
    model.result("pg6").feature("ptgr2").set("legend", true);
    model.result("pg6").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr2").setIndex("legends", "Y \u8f74\u7684 YY \u5e94\u53d8", 0);
    model.result("pg6").run();
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u65f6\u95f4\uff08\u5929\u6570\uff09");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u6750\u6599");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf3", "surf1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf4", "surf1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf5", "surf1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("mtrl1").set("material", "mat2");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").feature("mtrl1").set("material", "mat3");
    model.result("pg7").run();
    model.result("pg7").feature("surf4").feature("mtrl1").set("material", "mat4");
    model.result("pg7").run();
    model.result("pg7").feature("surf5").feature("mtrl1").set("material", "mat5");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result("pg5").run();

    model.title("\u5438\u6e7f\u81a8\u80c0\u5f15\u8d77\u7684 MEMS \u538b\u529b\u4f20\u611f\u5668\u6f02\u79fb");

    model
         .description("MEMS \u538b\u529b\u4f20\u611f\u5668\u4e0a\u8986\u76d6\u7684\u73af\u6c27\u6a21\u5851\u6599\u5177\u6709\u5438\u6e7f\u6027\u3002\u672c\u4f8b\u901a\u8fc7\u77ac\u6001\u7814\u7a76\u8ba1\u7b97\u5851\u5c01\u6750\u6599\u7684\u6c34\u5206\u6269\u6563\uff0c\u5e76\u6267\u884c\u53c2\u6570\u5316\u7a33\u6001\u5206\u6790\u8ba1\u7b97\u7531\u5438\u6e7f\u81a8\u80c0\u5f15\u8d77\u7684\u538b\u529b\u6d4b\u91cf\u503c\u7684\u6f02\u79fb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pressure_sensor_hygroscopic_swelling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
