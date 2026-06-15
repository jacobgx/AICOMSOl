/*
 * quenching_of_a_steel_billet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:06 by COMSOL 6.3.0.290. */
public class quenching_of_a_steel_billet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Metal_Processing_Module\\Steel_Quenching");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("audc", "AusteniteDecomposition", "geom1");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("trip", "1");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("plasticity", "0");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("dilstrain", "1");
    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", "1");
    model.component("comp1").physics("audc").prop("ShapeProperty").set("order_straindiscr_disc", "2");

    model.component("comp1").multiphysics().create("lht1", "PhaseTransformationLatentHeat", 2);
    model.component("comp1").multiphysics("lht1").set("Metphase_physics", "audc");
    model.component("comp1").multiphysics("lht1").set("HeatTransfer_physics", "ht");
    model.component("comp1").multiphysics("lht1").selection().all();
    model.component("comp1").multiphysics().create("ptstr1", "PhaseTransformationStrain", 2);
    model.component("comp1").multiphysics("ptstr1").set("Metphase_physics", "audc");
    model.component("comp1").multiphysics("ptstr1").set("SolidMechanics_physics", "solid");
    model.component("comp1").multiphysics("ptstr1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/audc", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/lht1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ptstr1", true);

    model.nodeGroup().create("grp1", "GlobalDefinitions");
    model.nodeGroup("grp1").label("\u5965\u6c0f\u4f53\u5230\u94c1\u7d20\u4f53");

    model.func().create("int1", "Interpolation");

    model.nodeGroup("grp1").add("func", "int1");

    model.func("int1").set("funcname", "K_Austenite_to_Ferrite");
    model.func("int1")
         .set("table", new String[][]{{"0", "0"}, {"450", "0"}, {"620", "0.005"}, {"750", "0"}, {"1000", "0"}});
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").setIndex("argunit", "degC", 0);
    model.func("int1").setIndex("fununit", "1/s", 0);
    model.func().create("int2", "Interpolation");

    model.nodeGroup("grp1").add("func", "int2");

    model.func("int2").set("funcname", "L_Austenite_to_Ferrite");
    model.func("int2")
         .set("table", new String[][]{{"0", "0"}, {"450", "0"}, {"620", "0.001"}, {"750", "0"}, {"1000", "0"}});
    model.func("int2").set("interp", "piecewisecubic");
    model.func("int2").setIndex("argunit", "degC", 0);
    model.func("int2").setIndex("fununit", "1/s", 0);

    model.nodeGroup().create("grp2", "GlobalDefinitions");
    model.nodeGroup("grp2").label("\u5965\u6c0f\u4f53\u5230\u73e0\u5149\u4f53");

    model.func().create("int3", "Interpolation");

    model.nodeGroup("grp2").add("func", "int3");

    model.func("int3").set("funcname", "K_Austenite_to_Pearlite");
    model.func("int3")
         .set("table", new String[][]{{"0", "0"}, {"450", "0"}, {"550", "0.015"}, {"750", "0"}, {"1000", "0"}});
    model.func("int3").set("interp", "piecewisecubic");
    model.func("int3").setIndex("argunit", "degC", 0);
    model.func("int3").setIndex("fununit", "1/s", 0);
    model.func().create("int4", "Interpolation");

    model.nodeGroup("grp2").add("func", "int4");

    model.func("int4").set("funcname", "L_Austenite_to_Pearlite");
    model.func("int4")
         .set("table", new String[][]{{"0", "0"}, {"450", "0"}, {"550", "0.001"}, {"750", "0"}, {"1000", "0"}});
    model.func("int4").set("interp", "piecewisecubic");
    model.func("int4").setIndex("argunit", "degC", 0);
    model.func("int4").setIndex("fununit", "1/s", 0);

    model.nodeGroup().create("grp3", "GlobalDefinitions");
    model.nodeGroup("grp3").label("\u5965\u6c0f\u4f53\u5230\u8d1d\u6c0f\u4f53");

    model.func().create("int5", "Interpolation");

    model.nodeGroup("grp3").add("func", "int5");

    model.func("int5").set("funcname", "K_Austenite_to_Bainite");
    model.func("int5").set("table", new String[][]{{"100", "0"}, {"380", "0"}, {"490", "0.06"}, {"550", "0"}});
    model.func("int5").set("interp", "piecewisecubic");
    model.func("int5").setIndex("argunit", "degC", 0);
    model.func("int5").setIndex("fununit", "1/s", 0);
    model.func().create("int6", "Interpolation");

    model.nodeGroup("grp3").add("func", "int6");

    model.func("int6").set("funcname", "L_Austenite_to_Bainite");
    model.func("int6").set("table", new String[][]{{"0", "0"}, {"200", "0"}, {"490", "0.010"}, {"630", "0"}});
    model.func("int6").set("interp", "piecewisecubic");
    model.func("int6").setIndex("argunit", "degC", 0);
    model.func("int6").setIndex("fununit", "1/s", 0);
    model.func().create("int7", "Interpolation");
    model.func("int7").set("funcname", "EYoung");
    model.func("int7")
         .set("table", new String[][]{{"0", "210"}, {"300", "180"}, {"600", "165"}, {"900", "120"}, {"", ""}});
    model.func("int7").setIndex("argunit", "degC", 0);
    model.func("int7").setIndex("fununit", "GPa", 0);
    model.func().create("int8", "Interpolation");
    model.func("int8").set("funcname", "htc");
    model.func("int8")
         .set("table", new String[][]{{"0", "200"}, {"300", "200"}, {"500", "2800"}, {"650", "750"}, {"1300", "750"}});
    model.func("int8").setIndex("argunit", "degC", 0);
    model.func("int8").setIndex("fununit", "W/(m^2*K)", 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.02, 0.1});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", false);
    model.component("comp1").physics("audc").prop("SolidMechanics").set("plasticity", true);
    model.component("comp1").physics("audc").prop("MaterialProperties").runCommand("makecompoundmaterial");
    model.component("comp1").physics("audc").feature("phase1").runCommand("makephasematerial");
    model.component("comp1").physics("audc").feature("phase1")
         .set("IsotropicHardeningModel", "LinearIsotropicHardening");
    model.component("comp1").physics("audc").feature("phase2").runCommand("makephasematerial");
    model.component("comp1").physics("audc").feature("phase2")
         .set("IsotropicHardeningModel", "LinearIsotropicHardening");
    model.component("comp1").physics("audc").feature("phase3").runCommand("makephasematerial");
    model.component("comp1").physics("audc").feature("phase3")
         .set("IsotropicHardeningModel", "LinearIsotropicHardening");
    model.component("comp1").physics("audc").feature("phase4").runCommand("makephasematerial");
    model.component("comp1").physics("audc").feature("phase4")
         .set("IsotropicHardeningModel", "LinearIsotropicHardening");
    model.component("comp1").physics("audc").feature("phase5").runCommand("makephasematerial");
    model.component("comp1").physics("audc").feature("phase5")
         .set("IsotropicHardeningModel", "LinearIsotropicHardening");

    model.material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup("def").func("int1").set("funcname", "k");
    model.material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "15"}, {"300", "20"}, {"600", "22"}, {"900", "25"}, {"", ""}});
    model.material("mat2").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat2").propertyGroup("def").func("int1").setIndex("fununit", "W/(m*K)", 0);
    model.material("mat2").propertyGroup("def").addInput("temperature");
    model.material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat2").propertyGroup("def").func("int2").set("funcname", "Cp");
    model.material("mat2").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "520"}, {"300", "560"}, {"600", "590"}, {"900", "620"}});
    model.material("mat2").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat2").propertyGroup("def").func("int2").setIndex("fununit", "J/(kg*K)", 0);
    model.material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"7930"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.material("mat2").propertyGroup("ThermalExpansion")
         .set("thermalexpansioncoefficient", new String[]{"2.2e-5"});
    model.material("mat2").propertyGroup("Enu").addInput("temperature");
    model.material("mat2").propertyGroup("Enu").set("E", new String[]{"EYoung(T)"});
    model.material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat2").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "sY");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"0", "200"}, {"300", "135"}, {"600", "40"}, {"850", "36"}});
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").setIndex("fununit", "MPa", 0);
    model.material("mat2").propertyGroup("ElastoplasticModel").func().create("int2", "Interpolation");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int2").set("funcname", "h");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int2")
         .set("table", new String[][]{{"0", "1"}, {"300", "15"}, {"600", "11"}, {"900", "0.6"}});
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int2").setIndex("fununit", "GPa", 0);
    model.material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"sY(T)"});
    model.material("mat2").propertyGroup("ElastoplasticModel").set("Et", new String[]{"h(T)"});
    model.material("mat3").propertyGroup("def").addInput("temperature");
    model.material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup("def").func("int1").set("funcname", "k");
    model.material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "50"}, {"300", "42"}, {"600", "35"}, {"900", "26"}});
    model.material("mat3").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat3").propertyGroup("def").func("int1").setIndex("fununit", "K/(m*K)", 0);
    model.material("mat3").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat3").propertyGroup("def").func("int2").set("funcname", "Cp");
    model.material("mat3").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "480"}, {"300", "570"}, {"600", "640"}, {"900", "700"}, {"", ""}});
    model.material("mat3").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat3").propertyGroup("def").func("int2").setIndex("fununit", "J/(kg*K)", 0);
    model.material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"7850"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.material("mat3").propertyGroup("ThermalExpansion")
         .set("thermalexpansioncoefficient", new String[]{"1.5e-5"});
    model.material("mat3").propertyGroup("Enu").addInput("temperature");
    model.material("mat3").propertyGroup("Enu").set("E", new String[]{"EYoung(T)"});
    model.material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.material("mat3").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat3").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "sY");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"0", "400"}, {"300", "200"}, {"600", "150"}, {"900", "35"}});
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").setIndex("fununit", "MPa", 0);
    model.material("mat3").propertyGroup("ElastoplasticModel").func().create("int2", "Interpolation");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int2").set("funcname", "h");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int2")
         .set("table", new String[][]{{"0", "1"}, {"300", "15"}, {"600", "11"}, {"900", "0.6"}});
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int2").setIndex("fununit", "GPa", 0);
    model.material("mat3").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"sY(T)"});
    model.material("mat3").propertyGroup("ElastoplasticModel").set("Et", new String[]{"h(T)"});
    model.material("mat4").propertyGroup("def").addInput("temperature");
    model.material("mat4").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat4").propertyGroup("def").func("int1").set("funcname", "k");
    model.material("mat4").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "50"}, {"300", "42"}, {"600", "35"}, {"900", "26"}});
    model.material("mat4").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat4").propertyGroup("def").func("int1").setIndex("fununit", "W/(m*K)", 0);
    model.material("mat4").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat4").propertyGroup("def").func("int2").set("funcname", "Cp");
    model.material("mat4").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "480"}, {"300", "570"}, {"600", "640"}, {"900", "700"}, {"", ""}});
    model.material("mat4").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat4").propertyGroup("def").func("int2").setIndex("fununit", "J/(kg*K)", 0);
    model.material("mat4").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.material("mat4").propertyGroup("def").set("density", new String[]{"7850"});
    model.material("mat4").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.material("mat4").propertyGroup("ThermalExpansion")
         .set("thermalexpansioncoefficient", new String[]{"1.5e-5"});
    model.material("mat4").propertyGroup("Enu").addInput("temperature");
    model.material("mat4").propertyGroup("Enu").set("E", new String[]{"EYoung(T)"});
    model.material("mat4").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.material("mat4").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat4").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat4").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "sY");
    model.material("mat4").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"0", "400"}, {"300", "200"}, {"600", "150"}, {"900", "35"}});
    model.material("mat4").propertyGroup("ElastoplasticModel").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat4").propertyGroup("ElastoplasticModel").func("int1").setIndex("fununit", "MPa", 0);
    model.material("mat4").propertyGroup("ElastoplasticModel").func().create("int2", "Interpolation");
    model.material("mat4").propertyGroup("ElastoplasticModel").func("int2").set("funcname", "h");
    model.material("mat4").propertyGroup("ElastoplasticModel").func("int2")
         .set("table", new String[][]{{"0", "1"}, {"300", "15"}, {"600", "11"}, {"900", "0.6"}});
    model.material("mat4").propertyGroup("ElastoplasticModel").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat4").propertyGroup("ElastoplasticModel").func("int2").setIndex("fununit", "GPa", 0);
    model.material("mat4").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"sY(T)"});
    model.material("mat4").propertyGroup("ElastoplasticModel").set("Et", new String[]{"h(T)"});
    model.material("mat5").propertyGroup("def").addInput("temperature");
    model.material("mat5").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat5").propertyGroup("def").func("int1").set("funcname", "k");
    model.material("mat5").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "50"}, {"300", "42"}, {"600", "35"}, {"900", "26"}});
    model.material("mat5").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat5").propertyGroup("def").func("int1").setIndex("fununit", "W/(m*K)", 0);
    model.material("mat5").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat5").propertyGroup("def").func("int2").set("funcname", "Cp");
    model.material("mat5").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "480"}, {"300", "570"}, {"600", "640"}, {"900", "700"}, {"", ""}});
    model.material("mat5").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat5").propertyGroup("def").func("int2").setIndex("fununit", "J/(kg*K)", 0);
    model.material("mat5").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.material("mat5").propertyGroup("def").set("density", new String[]{"7850"});
    model.material("mat5").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.material("mat5").propertyGroup("ThermalExpansion")
         .set("thermalexpansioncoefficient", new String[]{"1.5e-5"});
    model.material("mat5").propertyGroup("Enu").addInput("temperature");
    model.material("mat5").propertyGroup("Enu").set("E", new String[]{"EYoung(T)"});
    model.material("mat5").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.material("mat5").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat5").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat5").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "sY");
    model.material("mat5").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"0", "400"}, {"300", "200"}, {"600", "150"}, {"900", "35"}});
    model.material("mat5").propertyGroup("ElastoplasticModel").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat5").propertyGroup("ElastoplasticModel").func("int1").setIndex("fununit", "MPa", 0);
    model.material("mat5").propertyGroup("ElastoplasticModel").func().create("int2", "Interpolation");
    model.material("mat5").propertyGroup("ElastoplasticModel").func("int2").set("funcname", "h");
    model.material("mat5").propertyGroup("ElastoplasticModel").func("int2")
         .set("table", new String[][]{{"0", "1"}, {"300", "15"}, {"600", "11"}, {"900", "0.6"}});
    model.material("mat5").propertyGroup("ElastoplasticModel").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat5").propertyGroup("ElastoplasticModel").func("int2").setIndex("fununit", "GPa", 0);
    model.material("mat5").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"sY(T)"});
    model.material("mat5").propertyGroup("ElastoplasticModel").set("Et", new String[]{"h(T)"});
    model.material("mat6").propertyGroup("def").addInput("temperature");
    model.material("mat6").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat6").propertyGroup("def").func("int1").set("funcname", "k");
    model.material("mat6").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "44"}, {"300", "38"}, {"600", "30"}, {"900", "24"}});
    model.material("mat6").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat6").propertyGroup("def").func("int1").setIndex("fununit", "W/(m*K)", 0);
    model.material("mat6").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat6").propertyGroup("def").func("int2").set("funcname", "Cp");
    model.material("mat6").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "480"}, {"300", "570"}, {"600", "640"}, {"900", "650"}, {"", ""}});
    model.material("mat6").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat6").propertyGroup("def").func("int2").setIndex("fununit", "J/(kg*K)", 0);
    model.material("mat6").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.material("mat6").propertyGroup("def").set("density", new String[]{"7850"});
    model.material("mat6").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.material("mat6").propertyGroup("ThermalExpansion")
         .set("thermalexpansioncoefficient", new String[]{"1.4e-5"});
    model.material("mat6").propertyGroup("Enu").addInput("temperature");
    model.material("mat6").propertyGroup("Enu").set("E", new String[]{"EYoung(T)"});
    model.material("mat6").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.material("mat6").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat6").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "sY");
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"0", "1600"}, {"300", "1500"}, {"600", "1400"}, {"900", "100"}, {"", ""}});
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int1").setIndex("fununit", "MPa", 0);
    model.material("mat6").propertyGroup("ElastoplasticModel").func().create("int2", "Interpolation");
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int2").set("funcname", "h");
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int2")
         .set("table", new String[][]{{"0", "1"}, {"300", "15"}, {"600", "11"}, {"900", "0.6"}});
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int2").setIndex("fununit", "GPa", 0);
    model.material("mat6").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"sY(T)"});
    model.material("mat6").propertyGroup("ElastoplasticModel").set("Et", new String[]{"h(T)"});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "900[degC]");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("ht").feature("sym1").selection().set(2);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(3, 4);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htc(T)");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "80[degC]");
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("eil", new double[]{0.005, 0, 0, 0, 0.005, 0, 0, 0, 0.005});
    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(2);

    model.component("comp1").common().create("minpt1", "CommonInputDef");
    model.component("comp1").common("minpt1").set("quantity", "strainreferencetemperature");
    model.component("comp1").common("minpt1").selection().geom("geom1", 2);
    model.component("comp1").common("minpt1").selection().set(1);
    model.component("comp1").common("minpt1").selection().inherit(false);
    model.component("comp1").common("minpt1").selection().embedded(false);
    model.component("comp1").common("minpt1").selection().extraDim("");
    model.component("comp1").common("minpt1").set("value", "900[degC]");

    model.component("comp1").physics("audc").feature("ptran1").set("K", "K_Austenite_to_Ferrite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran1").set("L", "L_Austenite_to_Ferrite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran1").set("trip", true);
    model.component("comp1").physics("audc").feature("ptran1").set("recovery", true);
    model.component("comp1").physics("audc").feature("ptran2").set("K", "K_Austenite_to_Pearlite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran2").set("L", "L_Austenite_to_Pearlite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran2").set("trip", true);
    model.component("comp1").physics("audc").feature("ptran2").set("recovery", true);
    model.component("comp1").physics("audc").feature("ptran3").set("K", "K_Austenite_to_Bainite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran3").set("L", "L_Austenite_to_Bainite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran3").set("trip", true);
    model.component("comp1").physics("audc").feature("ptran3").set("recovery", true);
    model.component("comp1").physics("audc").feature("ptran4").set("Ms", "300[degC]");
    model.component("comp1").physics("audc").feature("ptran4").set("trip", true);
    model.component("comp1").physics("audc").feature("ptran4").set("recovery", true);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5,600)");
    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1solidrev");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").label("\u5e94\u529b, 3D (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg3").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("surf1").feature("def").set("descractive", true);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"audc.phase1.xi"});
    model.result("pg4").label("\u5965\u6c0f\u4f53 (audc)");
    model.result("pg4").feature("surf1").set("coloring", "gradient");
    model.result("pg4").feature("surf1").set("topcolor", "custom");
    model.result("pg4").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg4").feature("surf1").set("bottomcolor", "custom");
    model.result("pg4").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg4").feature("surf1").set("colortablerev", false);
    model.result("pg4").feature("surf1").set("titletype", "manual");
    model.result("pg4").feature("surf1").set("title", "\u5965\u6c0f\u4f53");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "rev1");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"audc.phase1.xi"});
    model.result("pg5").label("\u5965\u6c0f\u4f53, 3D (audc)");
    model.result("pg5").feature("surf1").set("coloring", "gradient");
    model.result("pg5").feature("surf1").set("topcolor", "custom");
    model.result("pg5").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg5").feature("surf1").set("bottomcolor", "custom");
    model.result("pg5").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg5").feature("surf1").set("colortablerev", false);
    model.result("pg5").feature("surf1").set("titletype", "manual");
    model.result("pg5").feature("surf1").set("title", "\u5965\u6c0f\u4f53");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"audc.phase2.xi"});
    model.result("pg6").label("\u94c1\u7d20\u4f53 (audc)");
    model.result("pg6").feature("surf1").set("coloring", "gradient");
    model.result("pg6").feature("surf1").set("topcolor", "custom");
    model.result("pg6").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg6").feature("surf1").set("bottomcolor", "custom");
    model.result("pg6").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg6").feature("surf1").set("colortablerev", false);
    model.result("pg6").feature("surf1").set("titletype", "manual");
    model.result("pg6").feature("surf1").set("title", "\u94c1\u7d20\u4f53");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "rev1");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"audc.phase2.xi"});
    model.result("pg7").label("\u94c1\u7d20\u4f53, 3D (audc)");
    model.result("pg7").feature("surf1").set("coloring", "gradient");
    model.result("pg7").feature("surf1").set("topcolor", "custom");
    model.result("pg7").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg7").feature("surf1").set("bottomcolor", "custom");
    model.result("pg7").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg7").feature("surf1").set("colortablerev", false);
    model.result("pg7").feature("surf1").set("titletype", "manual");
    model.result("pg7").feature("surf1").set("title", "\u94c1\u7d20\u4f53");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"audc.phase3.xi"});
    model.result("pg8").label("\u73e0\u5149\u4f53 (audc)");
    model.result("pg8").feature("surf1").set("coloring", "gradient");
    model.result("pg8").feature("surf1").set("topcolor", "custom");
    model.result("pg8").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg8").feature("surf1").set("bottomcolor", "custom");
    model.result("pg8").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg8").feature("surf1").set("colortablerev", false);
    model.result("pg8").feature("surf1").set("titletype", "manual");
    model.result("pg8").feature("surf1").set("title", "\u73e0\u5149\u4f53");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "rev1");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"audc.phase3.xi"});
    model.result("pg9").label("\u73e0\u5149\u4f53, 3D (audc)");
    model.result("pg9").feature("surf1").set("coloring", "gradient");
    model.result("pg9").feature("surf1").set("topcolor", "custom");
    model.result("pg9").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg9").feature("surf1").set("bottomcolor", "custom");
    model.result("pg9").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg9").feature("surf1").set("colortablerev", false);
    model.result("pg9").feature("surf1").set("titletype", "manual");
    model.result("pg9").feature("surf1").set("title", "\u73e0\u5149\u4f53");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").setIndex("looplevel", 1, 0);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"audc.phase4.xi"});
    model.result("pg10").label("\u8d1d\u6c0f\u4f53 (audc)");
    model.result("pg10").feature("surf1").set("coloring", "gradient");
    model.result("pg10").feature("surf1").set("topcolor", "custom");
    model.result("pg10").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg10").feature("surf1").set("bottomcolor", "custom");
    model.result("pg10").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg10").feature("surf1").set("colortablerev", false);
    model.result("pg10").feature("surf1").set("titletype", "manual");
    model.result("pg10").feature("surf1").set("title", "\u8d1d\u6c0f\u4f53");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "rev1");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"audc.phase4.xi"});
    model.result("pg11").label("\u8d1d\u6c0f\u4f53, 3D (audc)");
    model.result("pg11").feature("surf1").set("coloring", "gradient");
    model.result("pg11").feature("surf1").set("topcolor", "custom");
    model.result("pg11").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg11").feature("surf1").set("bottomcolor", "custom");
    model.result("pg11").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg11").feature("surf1").set("colortablerev", false);
    model.result("pg11").feature("surf1").set("titletype", "manual");
    model.result("pg11").feature("surf1").set("title", "\u8d1d\u6c0f\u4f53");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").set("data", "dset1");
    model.result("pg12").setIndex("looplevel", 1, 0);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"audc.phase5.xi"});
    model.result("pg12").label("\u9a6c\u6c0f\u4f53 (audc)");
    model.result("pg12").feature("surf1").set("coloring", "gradient");
    model.result("pg12").feature("surf1").set("topcolor", "custom");
    model.result("pg12").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg12").feature("surf1").set("bottomcolor", "custom");
    model.result("pg12").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg12").feature("surf1").set("colortablerev", false);
    model.result("pg12").feature("surf1").set("titletype", "manual");
    model.result("pg12").feature("surf1").set("title", "\u9a6c\u6c0f\u4f53");
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").set("data", "rev1");
    model.result("pg13").setIndex("looplevel", 1, 0);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", new String[]{"audc.phase5.xi"});
    model.result("pg13").label("\u9a6c\u6c0f\u4f53, 3D (audc)");
    model.result("pg13").feature("surf1").set("coloring", "gradient");
    model.result("pg13").feature("surf1").set("topcolor", "custom");
    model.result("pg13").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg13").feature("surf1").set("bottomcolor", "custom");
    model.result("pg13").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg13").feature("surf1").set("colortablerev", false);
    model.result("pg13").feature("surf1").set("titletype", "manual");
    model.result("pg13").feature("surf1").set("title", "\u9a6c\u6c0f\u4f53");
    model.result("pg1").run();

    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result("pg1").run();

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("\u94a2\u576f\u4e2d\u5fc3\u7684\u76f8\u5206\u6570");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("ylabel", "\u76f8\u5206\u6570");
    model.result("pg14").create("ptgr1", "PointGraph");
    model.result("pg14").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg14").feature("ptgr1").set("linewidth", "preference");
    model.result("pg14").feature("ptgr1").selection().set(1);
    model.result("pg14").feature("ptgr1").set("expr", "audc.phase1.xi");
    model.result("pg14").feature("ptgr1").set("descr", "\u76f8\u5206\u6570");
    model.result("pg14").feature("ptgr1").set("linewidth", 2);
    model.result("pg14").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg14").feature("ptgr1").set("markerpos", "interp");
    model.result("pg14").feature("ptgr1").set("legend", true);
    model.result("pg14").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg14").feature("ptgr1").setIndex("legends", "\u5965\u6c0f\u4f53", 0);
    model.result("pg14").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg14").run();
    model.result("pg14").feature("ptgr2").set("expr", "audc.phase2.xi");
    model.result("pg14").feature("ptgr2").setIndex("legends", "\u94c1\u7d20\u4f53", 0);
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg14").run();
    model.result("pg14").feature("ptgr3").set("expr", "audc.phase3.xi");
    model.result("pg14").feature("ptgr3").setIndex("legends", "\u73e0\u5149\u4f53", 0);
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("ptgr4", "ptgr1");
    model.result("pg14").run();
    model.result("pg14").feature("ptgr4").set("expr", "audc.phase4.xi");
    model.result("pg14").feature("ptgr4").setIndex("legends", "\u8d1d\u6c0f\u4f53", 0);
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("ptgr5", "ptgr1");
    model.result("pg14").run();
    model.result("pg14").feature("ptgr5").set("expr", "audc.phase5.xi");
    model.result("pg14").feature("ptgr5").setIndex("legends", "\u9a6c\u6c0f\u4f53", 0);
    model.result("pg14").run();
    model.result().duplicate("pg15", "pg14");
    model.result("pg15").run();
    model.result("pg15").label("\u94a2\u576f\u8868\u9762\u7684\u76f8\u5206\u6570");
    model.result("pg15").run();
    model.result("pg15").feature("ptgr1").selection().set(3);
    model.result("pg15").run();
    model.result("pg15").feature("ptgr2").selection().set(3);
    model.result("pg15").run();
    model.result("pg15").feature("ptgr3").selection().set(3);
    model.result("pg15").run();
    model.result("pg15").feature("ptgr4").selection().set(3);
    model.result("pg15").run();
    model.result("pg15").feature("ptgr5").selection().set(3);
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u8f74\u5411\u5e94\u529b\u5206\u5e03");
    model.result("pg16").setIndex("looplevelinput", "last", 0);
    model.result("pg16").create("lngr1", "LineGraph");
    model.result("pg16").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg16").feature("lngr1").set("linewidth", "preference");
    model.result("pg16").feature("lngr1").selection().set(2);
    model.result("pg16").feature("lngr1").set("expr", "solid.sGpzz");
    model.result("pg16").feature("lngr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg16").feature("lngr1").set("xdata", "expr");
    model.result("pg16").feature("lngr1").set("xdataexpr", "R");
    model.result("pg16").feature("lngr1").set("linewidth", 2);
    model.result("pg16").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").run();
    model.result("pg17").set("data", "mir1");
    model.result("pg17").set("titletype", "none");
    model.result("pg17").set("plotarrayenable", true);
    model.result("pg17").set("arrayaxis", "y");
    model.result("pg17").set("paddinglinear", "absolute");
    model.result("pg17").set("padding", 0.05);
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("arraydim", "1");
    model.result("pg17").feature("surf1").label("\u8f74\u5411\u5e94\u529b");
    model.result("pg17").feature("surf1").set("expr", "solid.sGpzz");
    model.result("pg17").feature("surf1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg17").feature().duplicate("surf2", "surf1");
    model.result("pg17").feature("surf2").set("arraydim", "1");
    model.result("pg17").run();
    model.result("pg17").feature("surf2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg17").feature("surf2").set("expr", "solid.epeGp");
    model.result("pg17").feature("surf2").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg17").feature("surf2").set("colortable", "Traffic");
    model.result("pg13").run();
    model.result("pg17").run();
    model.result("pg17").feature().copy("surf3", "pg13/surf1");
    model.result("pg17").feature("surf3").set("arraydim", "1");
    model.result("pg17").run();
    model.result("pg17").feature("surf3").label("\u9a6c\u6c0f\u4f53\u76f8\u5206\u6570");

    model.title("\u94a2\u576f\u6dec\u706b");

    model
         .description("\u672c\u4f8b\u5c06\u94a2\u576f\u5728\u6cb9\u4e2d\u4ece\u5965\u6c0f\u4f53\u72b6\u6001\u8fdb\u884c\u6dec\u706b\u3002\u4f7f\u7528\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\u6765\u6a21\u62df\u5965\u6c0f\u4f53\u5206\u89e3\u3001\u4f20\u70ed\u548c\u56fa\u4f53\u529b\u5b66\u7684\u8026\u5408\u95ee\u9898\u3002\u5728\u6dec\u706b\u8fc7\u7a0b\u4e2d\uff0c\u5965\u6c0f\u4f53\u5206\u89e3\u6210\u7531\u94c1\u7d20\u4f53\u3001\u73e0\u5149\u4f53\u3001\u8d1d\u6c0f\u4f53\u548c\u9a6c\u6c0f\u4f53\u7ec4\u6210\u7684\u6df7\u5408\u7269\u3002\u672c\u6a21\u578b\u53ef\u4ee5\u8ba1\u7b97\u7531\u70ed\u81a8\u80c0\u548c\u76f8\u53d8\u8bf1\u5bfc\u5851\u6027 (TRIP) \u5f15\u8d77\u7684\u76f8\u53d8\u5e94\u53d8\uff0c\u5e76\u8ba1\u7b97\u7531\u6b64\u4ea7\u751f\u7684\u6b8b\u4f59\u5e94\u529b\u72b6\u6001\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("quenching_of_a_steel_billet.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
