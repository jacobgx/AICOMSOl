/*
 * temperature_dependent_plasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:36 by COMSOL 6.3.0.290. */
public class temperature_dependent_plasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);

    model.param().set("internalPressure", "70[bar]");
    model.param().descr("internalPressure", "\u5185\u538b");
    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4\u53d8\u91cf\uff1b\u7528\u4e8e\u7a33\u6001\u5206\u6790");

    model.component("comp1").geom("geom1").insertFile("temperature_dependent_plasticity_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").selection().set(1, 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u4e0d\u9508\u94a2");
    model.component("comp1").material("mat1").selection().set(1, 3);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8000"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "fE");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("table", 20, 0, 0);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("table", 194, 0, 1);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("table", 100, 1, 0);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("table", 189, 1, 1);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("table", 200, 2, 0);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("table", 179, 2, 1);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("table", 300, 3, 0);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("table", 175, 3, 1);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").setIndex("fununit", "GPa", 0);
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"fE(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "fA");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("table", 20, 0, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("table", "16e-6", 0, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("table", 100, 1, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("table", "16.5e-6", 1, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("table", 200, 2, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("table", "17e-6", 2, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("table", 300, 3, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("table", "17.5e-6", 3, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").setIndex("fununit", "1/K", 0);
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("funcname", "fK");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("table", 20, 0, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("table", 13.9, 0, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("table", 100, 1, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("table", 15.5, 1, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("table", 200, 2, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("table", 16.8, 2, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("table", 300, 3, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("table", 17.8, 3, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").setIndex("fununit", "W/(m*degC)", 0);
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcname", "fCp");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("table", 20, 0, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("table", 482, 0, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("table", 100, 1, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("table", 504, 1, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("table", 200, 2, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("table", 521, 2, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("table", 300, 3, 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("table", 530, 3, 1);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .setIndex("fununit", "J/(kg*degC)", 0);
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"fK(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"fCp(T)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"fA(T)"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("filename", "temperature_dependent_plasticity_function.txt");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("argunit", "1", 1);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setEntry("funcnames", "col3", "sY");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("fununit", "Pa", 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").importData();
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"sY(T,0)"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", new String[]{"sY(T,epe)-sY(T,0)"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u78b3\u94a2");
    model.component("comp1").material("mat2").selection().set(2, 4);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"8000"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "fE");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("table", 20, 0, 0);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("table", 208, 0, 1);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("table", 100, 1, 0);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("table", 202, 1, 1);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("table", 200, 2, 0);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("table", 196, 2, 1);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("table", 300, 3, 0);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("table", 189, 3, 1);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").setIndex("fununit", "GPa", 0);
    model.component("comp1").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "fA");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("table", 20, 0, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("table", "10.9e-6", 0, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("table", 100, 1, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("table", "12.4e-6", 1, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("table", 200, 2, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("table", "13.8e-6", 2, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("table", 300, 3, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("table", "14.9e-6", 3, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").setIndex("fununit", "1/K", 0);
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("funcname", "fK");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("table", 20, 0, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("table", 51.2, 0, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("table", 100, 1, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("table", 48.3, 1, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("table", 200, 2, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("table", 45.5, 2, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("table", 300, 3, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("table", 42.7, 3, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").setIndex("fununit", "W/(m*degC)", 0);
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("funcname", "fCp");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("table", 20, 0, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("table", 489, 0, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("table", 100, 1, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("table", 519, 1, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("table", 200, 2, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("table", 546, 2, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("table", 300, 3, 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("table", 569, 3, 1);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").setIndex("argunit", "degC", 0);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3")
         .setIndex("fununit", "J/(kg*degC)", 0);
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"fE(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"fK(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"fCp(T)"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"fA(T)"});

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("funcname", "pipeWaterTemp");
    model.component("comp1").func("step1").set("location", "1[s]");
    model.component("comp1").func("step1").set("from", "280[degC]");
    model.component("comp1").func("step1").set("to", "20[degC]");
    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "vesselWaterTemp");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 280, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1800, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 20, 1, 1);
    model.component("comp1").func("int1").setIndex("argunit", "s", 0);
    model.component("comp1").func("int1").setIndex("fununit", "degC", 0);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1, 3, 4, 5, 7, 8, 11, 13, 16, 17, 19, 22);

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "280[degC]");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().set(9, 20, 23);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 10);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "50[degC]");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().set(10, 15);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", 100);
    model.component("comp1").physics("ht").feature("hf2").set("Text", "pipeWaterTemp(t)");
    model.component("comp1").physics("ht").create("hf3", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf3").selection().set(2);
    model.component("comp1").physics("ht").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf3").set("h", 100);
    model.component("comp1").physics("ht").feature("hf3").set("Text", "vesselWaterTemp(t)");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("solid").create("sym2", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym2").selection().set(4, 8);
    model.component("comp1").physics("solid").feature("sym2").set("NormalDirectionTranslation", "FreeDisplacement");
    model.component("comp1").physics("solid").create("sym3", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym3").selection().set(24, 25);
    model.component("comp1").physics("solid").feature("sym3").set("NormalDirectionTranslation", "FreeDisplacement");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(2, 10, 15);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "internalPressure");
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").selection().set(4, 8);
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0", "0", "4.76*internalPressure"});
    model.component("comp1").physics("solid").create("sl1", "StressLinearization", -1);
    model.component("comp1").physics("solid").feature("sl1").set("TypeSelection", "Distributed");
    model.component("comp1").physics("solid").feature("sl1").selection("BoundarySelection").set(9);
    model.component("comp1").physics("solid").feature("sl1").selection("DomainSelection").set(1, 2);

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(19);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", 0.015);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").create("fq2", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq2").selection().set(9);
    model.component("comp1").mesh("mesh1").feature("fq2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq2").feature("size1").set("hmax", 0.05);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe4").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe4").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("swe4");

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u5316");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", false);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/te1", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.2,2) range(60,60,1800)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").label("\u7814\u7a76 2\uff1a\u4f20\u70ed");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std1");
    model.study("std3").feature("stat").set("usesol", true);
    model.study("std3").feature("stat").set("notsolmethod", "sol");
    model.study("std3").feature("stat").set("notstudy", "std2");
    model.study("std3").feature("stat").set("notsolnum", "all");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "internalPressure", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std3").feature("stat").setIndex("pname", "internalPressure", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std3").feature("stat").setIndex("pname", "t", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0,0.2,2) range(60,60,1800)", 0);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u5851\u6027");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("s1").feature("fc1").set("dtech", "ddog");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").set("data", "dset3");
    model.result().dataset("cln1")
         .set("genpoints", new String[][]{{"comp1.solid.sl1.pt0X", "comp1.solid.sl1.pt0Y", "comp1.solid.sl1.pt0Z"}, {"comp1.solid.sl1.pt1X", "comp1.solid.sl1.pt1Y", "comp1.solid.sl1.pt1Z"}});
    model.result().dataset("cln1").set("spacevars", new String[]{"sllx"});
    model.result().dataset("cln1").label("\u7ebf\u6027\u5316\u7ebf (\u5e94\u529b\u7ebf\u6027\u5316 1)");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "cln1");
    model.result("pg2").label("\u5e94\u529b\u7ebf\u6027\u5316 1 (solid)");
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").set("data", "cln1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("expr", "solid.sl1.Slls22");
    model.result("pg2").feature("lngr1").label("\u5df2\u8ba1\u7b97");
    model.result("pg2").feature("lngr1").set("linestyle", "cyclereset");
    model.result("pg2").feature("lngr1").set("linecolor", "custom");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autoplotlabel", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1")
         .set("customlinecolor", new double[]{0.07058823853731155, 0.2235294133424759, 0.9529411792755127});
    model.result("pg2").create("lnsg1", "LineSegments");
    model.result("pg2").feature("lnsg1").label("\u819c\u5e94\u529b");
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "solid.sl1.xs", 0);
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "solid.sl1.xe", 1);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "solid.sl1.ySm22", 0);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "solid.sl1.ySm22", 1);
    model.result("pg2").feature("lnsg1").set("linestyle", "cyclereset");
    model.result("pg2").feature("lnsg1").set("linecolor", "custom");
    model.result("pg2").feature("lnsg1").set("legend", true);
    model.result("pg2").feature("lnsg1").set("autoplotlabel", true);
    model.result("pg2").feature("lnsg1").set("autosolution", false);
    model.result("pg2").feature("lnsg1")
         .set("customlinecolor", new double[]{0.1568627506494522, 0.7254902124404907, 0.24313725531101227});
    model.result("pg2").create("lnsg2", "LineSegments");
    model.result("pg2").feature("lnsg2").label("\u819c\u5e94\u529b\u52a0\u5f2f\u66f2\u5e94\u529b");
    model.result("pg2").feature("lnsg2").setIndex("xexpr", "solid.sl1.xs", 0);
    model.result("pg2").feature("lnsg2").setIndex("xexpr", "solid.sl1.xe", 1);
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "solid.sl1.ySmbs22", 0);
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "solid.sl1.ySmbe22", 1);
    model.result("pg2").feature("lnsg2").set("linestyle", "cyclereset");
    model.result("pg2").feature("lnsg2").set("linecolor", "custom");
    model.result("pg2").feature("lnsg2").set("legend", true);
    model.result("pg2").feature("lnsg2").set("autoplotlabel", true);
    model.result("pg2").feature("lnsg2").set("autosolution", false);
    model.result("pg2").feature("lnsg2")
         .set("customlinecolor", new double[]{0.8235294222831726, 0.019607843831181526, 0.019607843831181526});
    model.result("pg1").run();
    model.result("pg1").set("view", "new");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", 0);
    model.result("pg1").run();

    model.view("view4").set("showgrid", false);

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg3").feature("surf1").set("inheritplot", "none");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortabletype", "discrete");
    model.result("pg3").feature("surf1").set("bandcount", 11);
    model.result("pg3").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").run();
    model.result("pg3").set("view", "view4");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 41, 0);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "T");
    model.result("pg4").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("unit", "degC");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u5e94\u529b\u5f3a\u5ea6\uff0c\u6700\u5927\u503c");
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup("eg1").create("max1", "MaxSurface");
    model.result().evaluationGroup("eg1").feature("max1").selection().set(9);
    model.result().evaluationGroup("eg1").feature("max1").set("expr", new String[]{"solid.SImb"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("descr", new String[]{"\u5e94\u529b\u5f3a\u5ea6\uff0c\u819c\u52a0\u5f2f\u66f2"});
    model.result().evaluationGroup("eg1").feature("max1").set("unit", new String[]{"N/m^2"});
    model.result().evaluationGroup("eg1").feature("max1").set("includepos", true);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg5").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u529b\u5f3a\u5ea6\uff0c\u6700\u5927\u503c");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u5e94\u529b\u5f3a\u5ea6");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("view", "new");
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "solid.SImb");
    model.result("pg6").feature("surf1").set("descr", "\u5e94\u529b\u5f3a\u5ea6\uff0c\u819c\u52a0\u5f2f\u66f2");
    model.result("pg6").feature("surf1").create("mrkr1", "Marker");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("mrkr1").set("display", "max");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 30, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").run();

    model.component("comp1").physics("solid").feature("sl1").set("pt0sls", new double[]{0.778, 0.778, 0.298});

    model.sol("sol3").updateSolution();

    model.result("pg1").run();
    model.result().configuration().create("gsty1", "GraphStyle");
    model.result().configuration("gsty1").set("linewidth", 2);
    model.result().configuration("gsty1").set("autopoint", false);
    model.result().configuration("gsty1").set("autoplotlabel", true);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", new int[]{1, 41}, 0);
    model.result("pg2").set("styleconfig", "gsty1");
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").run();
    model.result("pg4").run();

    model.title("\u538b\u529b\u5bb9\u5668\u4e2d\u6e29\u5ea6\u76f8\u5173\u7684\u5851\u6027");

    model
         .description("\u538b\u529b\u5bb9\u5668\u5728\u6e29\u5ea6\u53d1\u751f\u5feb\u901f\u53d8\u5316\u65f6\u4f1a\u4ea7\u751f\u53d8\u5f62\u3002\u964d\u6e29\u8fc7\u7a0b\u4e2d\u7684\u6e29\u5ea6\u68af\u5ea6\u5bfc\u81f4\u4e0d\u9508\u94a2\u5305\u5c42\u53d1\u751f\u5851\u6027\u53d8\u5f62\uff0c\u5176\u4e2d\u7684\u5c48\u670d\u5e94\u529b\u548c\u786c\u5316\u51fd\u6570\u968f\u6e29\u5ea6\u53d1\u751f\u53d8\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("temperature_dependent_plasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
