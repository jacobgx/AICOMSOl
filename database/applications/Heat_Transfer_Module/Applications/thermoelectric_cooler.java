/*
 * thermoelectric_cooler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:18 by COMSOL 6.3.0.290. */
public class thermoelectric_cooler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.component("comp1").multiphysics().create("tee1", "ThermoelectricEffect", 3);
    model.component("comp1").multiphysics("tee1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("tee1").set("EMCurrentDensity_physics", "ec");
    model.component("comp1").multiphysics("tee1").selection().all();
    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.param().set("length", "8[mm]");
    model.param().descr("length", "Total length");
    model.param().set("width", "10[mm]");
    model.param().descr("width", "Total width");
    model.param().set("height", "2.5[mm]");
    model.param().descr("height", "Total height");
    model.param().set("d_conductor", "100[um]");
    model.param().descr("d_conductor", "Conductor thickness");
    model.param().set("d_ceramics", "0.3[mm]");
    model.param().descr("d_ceramics", "Ceramics thickness");
    model.param().set("leg_length", "1[mm]");
    model.param().descr("leg_length", "Leg cross section in length");
    model.param().set("leg_width", "1.2[mm]");
    model.param().descr("leg_width", "Leg cross section in width");
    model.param().set("leg_height", "height-2*(d_conductor+d_ceramics)");
    model.param().descr("leg_height", "Leg height");
    model.param().set("pitch", "0.5[mm]");
    model.param().descr("pitch", "Pitch");
    model.param()
         .set("n_length", "floor((length-2*pitch-leg_length)/(leg_length+pitch))+1-mod(floor((length-2*pitch-leg_length)/(leg_length+pitch))+1,2)");
    model.param().descr("n_length", "Number of legs in length");
    model.param().set("n_width", "floor((width-2*pitch-leg_width)/(leg_width+pitch))+1");
    model.param().descr("n_width", "Number of legs in width");
    model.param().set("network_length", "(leg_length+pitch)*n_length-pitch");
    model.param().descr("network_length", "Length of legs network");
    model.param().set("network_width", "(leg_width+pitch)*n_width-pitch");
    model.param().descr("network_width", "Width of legs network");
    model.param().set("N", "n_length*n_width/2");
    model.param().descr("N", "Number of thermocouples");
    model.param().set("Tref", "323.15[K]");
    model.param().descr("Tref", "Hot side temperature");
    model.param().set("dT0", "50[K]");
    model.param().descr("dT0", "Prescribed temperature difference");
    model.param().set("I0", "1");
    model.param().descr("I0", "Prescribed relative electric current");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat1").propertyGroup("def").func().create("int3", "Interpolation");
    model.material("mat1").label("Bismuth Telluride - Bi2Te3");
    model.material("mat1").propertyGroup("def").func("int1").label("Seebeck coefficient");
    model.material("mat1").propertyGroup("def").func("int1").set("funcname", "S");
    model.material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "168e-6"}, {"250", "192e-6"}, {"300", "210e-6"}, {"350", "225e-6"}, {"400", "237e-6"}});
    model.material("mat1").propertyGroup("def").func("int1").set("interp", "cubicspline");
    model.material("mat1").propertyGroup("def").func("int1").set("fununit", new String[]{"V/K"});
    model.material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("int2").label("Thermal conductivity");
    model.material("mat1").propertyGroup("def").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat1").propertyGroup("def").func("int2").set("funcname", "k");
    model.material("mat1").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"200", "24e-1"}, {"250", "19e-1"}, {"300", "16e-1"}, {"350", "16e-1"}, {"400", "17.5e-1"}});
    model.material("mat1").propertyGroup("def").func("int2").set("interp", "cubicspline");
    model.material("mat1").propertyGroup("def").func("int2").set("fununit", new String[]{"W/m/K"});
    model.material("mat1").propertyGroup("def").func("int2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("int3").label("Electrical conductivity");
    model.material("mat1").propertyGroup("def").func("int3").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat1").propertyGroup("def").func("int3").set("funcname", "sigma");
    model.material("mat1").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"200", "1.4286e5"}, {"250", "1.1111e5"}, {"300", "0.86957e5"}, {"350", "0.71429e5"}, {"400", "0.58824e5"}});
    model.material("mat1").propertyGroup("def").func("int3").set("interp", "cubicspline");
    model.material("mat1").propertyGroup("def").func("int3").set("fununit", new String[]{"S/m"});
    model.material("mat1").propertyGroup("def").func("int3").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "154[J/(kg*K)]");
    model.material("mat1").propertyGroup("def").set("density", "7700[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("seebeckcoefficient", new String[]{"S(T)", "0", "0", "0", "S(T)", "0", "0", "0", "S(T)"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma(T)", "0", "0", "0", "sigma(T)", "0", "0", "0", "sigma(T)"});
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("Lead Telluride - PbTe");
    model.material("mat2").propertyGroup("def").set("density", "8160[kg/m^3]");
    model.material("mat2").propertyGroup("def").set("heatcapacity", "151[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("seebeckcoefficient", new String[]{"187e-6[V/K]", "0", "0", "0", "187e-6[V/K]", "0", "0", "0", "187e-6[V/K]"});
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.46[W/(m*K)]", "0", "0", "0", "1.46[W/(m*K)]", "0", "0", "0", "1.46[W/(m*K)]"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"6.0976e4[S/m]", "0", "0", "0", "6.0976e4[S/m]", "0", "0", "0", "6.0976e4[S/m]"});
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat3").propertyGroup("def").func().create("int3", "Interpolation");
    model.material("mat3").label("Bismuth Telluride - Bi2Te3.1");
    model.material("mat3").propertyGroup("def").func("int1").label("Seebeck coefficient");
    model.material("mat3").propertyGroup("def").func("int1").set("funcname", "S");
    model.material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "168e-6"}, {"250", "192e-6"}, {"300", "210e-6"}, {"350", "225e-6"}, {"400", "237e-6"}});
    model.material("mat3").propertyGroup("def").func("int1").set("interp", "cubicspline");
    model.material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V/K"});
    model.material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("def").func("int2").label("Thermal conductivity");
    model.material("mat3").propertyGroup("def").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat3").propertyGroup("def").func("int2").set("funcname", "k");
    model.material("mat3").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"200", "24e-1"}, {"250", "19e-1"}, {"300", "16e-1"}, {"350", "16e-1"}, {"400", "17.5e-1"}});
    model.material("mat3").propertyGroup("def").func("int2").set("interp", "cubicspline");
    model.material("mat3").propertyGroup("def").func("int2").set("fununit", new String[]{"W/m/K"});
    model.material("mat3").propertyGroup("def").func("int2").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("def").func("int3").label("Electrical conductivity");
    model.material("mat3").propertyGroup("def").func("int3").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat3").propertyGroup("def").func("int3").set("funcname", "sigma");
    model.material("mat3").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"200", "1.4286e5"}, {"250", "1.1111e5"}, {"300", "0.86957e5"}, {"350", "0.71429e5"}, {"400", "0.58824e5"}});
    model.material("mat3").propertyGroup("def").func("int3").set("interp", "cubicspline");
    model.material("mat3").propertyGroup("def").func("int3").set("fununit", new String[]{"S/m"});
    model.material("mat3").propertyGroup("def").func("int3").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "154[J/(kg*K)]");
    model.material("mat3").propertyGroup("def").set("density", "7700[kg/m^3]");
    model.material("mat3").propertyGroup("def")
         .set("seebeckcoefficient", new String[]{"S(T)", "0", "0", "0", "S(T)", "0", "0", "0", "S(T)"});
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma(T)", "0", "0", "0", "sigma(T)", "0", "0", "0", "sigma(T)"});
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def").addInput("temperature");
    model.material().create("mat4", "Common", "");
    model.material("mat4").label("Lead Telluride - PbTe 1");
    model.material("mat4").propertyGroup("def").set("density", "8160[kg/m^3]");
    model.material("mat4").propertyGroup("def").set("heatcapacity", "151[J/(kg*K)]");
    model.material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat4").propertyGroup("def")
         .set("seebeckcoefficient", new String[]{"187e-6[V/K]", "0", "0", "0", "187e-6[V/K]", "0", "0", "0", "187e-6[V/K]"});
    model.material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.46[W/(m*K)]", "0", "0", "0", "1.46[W/(m*K)]", "0", "0", "0", "1.46[W/(m*K)]"});
    model.material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"6.0976e4[S/m]", "0", "0", "0", "6.0976e4[S/m]", "0", "0", "0", "6.0976e4[S/m]"});
    model.material("mat1").label("Bismuth Telluride - Bi2Te3, N-Type");
    model.material("mat1").propertyGroup("def").set("seebeckcoefficient", new String[]{"-S(Tref)"});
    model.material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"sigma(Tref)"});
    model.material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k(Tref)"});
    model.material("mat2").label("Lead Telluride - PbTe, N-Type");
    model.material("mat2").propertyGroup("def").set("seebeckcoefficient", new String[]{"-187e-6[V/K]"});
    model.material("mat3").label("Bismuth Telluride - Bi2Te3, P-Type");
    model.material("mat3").propertyGroup("def").set("seebeckcoefficient", new String[]{"S(Tref)"});
    model.material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"sigma(Tref)"});
    model.material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"k(Tref)"});
    model.material("mat4").label("Lead Telluride - PbTe, P-Type");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"leg_width", "leg_length", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "leg_height", 2);
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"(width-2*pitch-((leg_width+pitch)*n_width-pitch))/2+pitch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1")
         .setIndex("pos", "(length-2*pitch-((leg_length+pitch)*n_length-pitch))/2+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "d_ceramics+d_conductor", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("arr1")
         .set("fullsize", new String[]{"ceil(n_width/2)", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("fullsize", "ceil(n_length/2)", 1);
    model.component("comp1").geom("geom1").feature("arr1")
         .set("displ", new String[]{"2*(leg_width+pitch)", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("displ", "2*(leg_length+pitch)", 1);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("N-Type Legs");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"leg_width", "leg_length", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "leg_height", 2);
    model.component("comp1").geom("geom1").feature("blk2")
         .set("pos", new String[]{"(width-2*pitch-((leg_width+pitch)*n_width-pitch))/2+pitch+leg_width+pitch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2")
         .setIndex("pos", "(length-2*pitch-((leg_length+pitch)*n_length-pitch))/2+pitch+leg_length+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "d_ceramics+d_conductor", 2);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("blk2");
    model.component("comp1").geom("geom1").feature("arr2")
         .set("fullsize", new String[]{"floor(n_width/2)", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr2").setIndex("fullsize", "floor(n_length/2)", 1);
    model.component("comp1").geom("geom1").feature("arr2")
         .set("displ", new String[]{"2*(leg_width+pitch)", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr2").setIndex("displ", "2*(leg_length+pitch)", 1);
    model.component("comp1").geom("geom1").feature("arr2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"leg_width", "leg_length", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "leg_height", 2);
    model.component("comp1").geom("geom1").feature("blk3")
         .set("pos", new String[]{"(width-2*pitch-((leg_width+pitch)*n_width-pitch))/2+pitch+leg_width+pitch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk3")
         .setIndex("pos", "(length-2*pitch-((leg_length+pitch)*n_length-pitch))/2+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "d_ceramics+d_conductor", 2);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("blk3");
    model.component("comp1").geom("geom1").feature("arr3")
         .set("fullsize", new String[]{"floor(n_width/2)", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr3").setIndex("fullsize", "ceil(n_length/2)", 1);
    model.component("comp1").geom("geom1").feature("arr3")
         .set("displ", new String[]{"2*(leg_width+pitch)", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr3").setIndex("displ", "2*(leg_length+pitch)", 1);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("P-Type Legs");
    model.component("comp1").geom("geom1").feature("arr3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"leg_width", "leg_length", "1"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("size", "leg_height", 2);
    model.component("comp1").geom("geom1").feature("blk4")
         .set("pos", new String[]{"(width-2*pitch-((leg_width+pitch)*n_width-pitch))/2+pitch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk4")
         .setIndex("pos", "(length-2*pitch-((leg_length+pitch)*n_length-pitch))/2+pitch+leg_length+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk4").setIndex("pos", "d_ceramics+d_conductor", 2);
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("arr4", "Array");
    model.component("comp1").geom("geom1").feature("arr4").selection("input").set("blk4");
    model.component("comp1").geom("geom1").feature("arr4")
         .set("fullsize", new String[]{"ceil(n_width/2)", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr4").setIndex("fullsize", "floor(n_length/2)", 1);
    model.component("comp1").geom("geom1").feature("arr4")
         .set("displ", new String[]{"2*(leg_width+pitch)", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr4").setIndex("displ", "2*(leg_length+pitch)", 1);
    model.component("comp1").geom("geom1").feature("arr4").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("arr4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"leg_width", "leg_length", "1"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("size", "d_conductor", 2);
    model.component("comp1").geom("geom1").feature("blk5")
         .set("pos", new String[]{"(width-2*pitch-network_width)/2+pitch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk5")
         .setIndex("pos", "(length-2*pitch-network_length)/2+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk5").setIndex("pos", "d_ceramics", 2);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Conductors");
    model.component("comp1").geom("geom1").feature("blk5").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("blk5");
    model.component("comp1").geom("geom1").feature("copy1")
         .set("displx", "mod(n_length,2)*(network_width-leg_width)");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "network_length-leg_length");
    model.component("comp1").geom("geom1").feature("copy1")
         .set("displz", "mod(n_length*n_width,2)*(d_conductor+leg_height)");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Grounded Conductor");
    model.component("comp1").geom("geom1").feature("copy1").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("blk6", "Block");
    model.component("comp1").geom("geom1").feature("blk6").set("size", new String[]{"2*leg_width+pitch", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk6").setIndex("size", "leg_length", 1);
    model.component("comp1").geom("geom1").feature("blk6").setIndex("size", "d_conductor", 2);
    model.component("comp1").geom("geom1").feature("blk6")
         .set("pos", new String[]{"(width-2*pitch-network_width)/2+pitch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk6")
         .setIndex("pos", "(length-2*pitch-network_length)/2+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk6").setIndex("pos", "d_ceramics+d_conductor+leg_height", 2);
    model.component("comp1").geom("geom1").run("blk6");
    model.component("comp1").geom("geom1").create("arr5", "Array");
    model.component("comp1").geom("geom1").feature("arr5").selection("input").set("blk6");
    model.component("comp1").geom("geom1").feature("arr5")
         .set("fullsize", new String[]{"floor(n_width/2)", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr5").setIndex("fullsize", "n_length", 1);
    model.component("comp1").geom("geom1").feature("arr5")
         .set("displ", new String[]{"2*(leg_width+pitch)", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr5").setIndex("displ", "leg_length+pitch", 1);
    model.component("comp1").geom("geom1").feature("arr5").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("arr5");
    model.component("comp1").geom("geom1").create("blk7", "Block");
    model.component("comp1").geom("geom1").feature("blk7").set("size", new String[]{"2*leg_width+pitch", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk7").setIndex("size", "leg_length", 1);
    model.component("comp1").geom("geom1").feature("blk7").setIndex("size", "d_conductor", 2);
    model.component("comp1").geom("geom1").feature("blk7")
         .set("pos", new String[]{"(width-2*pitch-network_width)/2+pitch+leg_width+pitch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk7")
         .setIndex("pos", "(length-2*pitch-network_length)/2+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk7").setIndex("pos", "d_ceramics", 2);
    model.component("comp1").geom("geom1").run("blk7");
    model.component("comp1").geom("geom1").create("arr6", "Array");
    model.component("comp1").geom("geom1").feature("arr6").selection("input").set("blk7");
    model.component("comp1").geom("geom1").feature("arr6")
         .set("fullsize", new String[]{"floor((n_width-1)/2)", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr6").setIndex("fullsize", "n_length", 1);
    model.component("comp1").geom("geom1").feature("arr6")
         .set("displ", new String[]{"2*(leg_width+pitch)", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr6").setIndex("displ", "leg_length+pitch", 1);
    model.component("comp1").geom("geom1").feature("arr6").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("arr6");
    model.component("comp1").geom("geom1").create("blk8", "Block");
    model.component("comp1").geom("geom1").feature("blk8")
         .set("size", new String[]{"leg_width", "2*leg_length+pitch", "1"});
    model.component("comp1").geom("geom1").feature("blk8").setIndex("size", "d_conductor", 2);
    model.component("comp1").geom("geom1").feature("blk8")
         .set("pos", new String[]{"(width-2*pitch-network_width)/2+pitch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk8")
         .setIndex("pos", "(length-2*pitch-network_length)/2+pitch+leg_length+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk8").setIndex("pos", "d_ceramics", 2);
    model.component("comp1").geom("geom1").run("blk8");
    model.component("comp1").geom("geom1").create("arr7", "Array");
    model.component("comp1").geom("geom1").feature("arr7").selection("input").set("blk8");
    model.component("comp1").geom("geom1").feature("arr7").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr7").set("linearsize", "floor((n_length-1)/2)");
    model.component("comp1").geom("geom1").feature("arr7")
         .set("displ", new String[]{"0", "2*(leg_length+pitch)", "0"});
    model.component("comp1").geom("geom1").feature("arr7").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("arr7");
    model.component("comp1").geom("geom1").create("blk9", "Block");
    model.component("comp1").geom("geom1").feature("blk9")
         .set("size", new String[]{"leg_width", "2*leg_length+pitch", "1"});
    model.component("comp1").geom("geom1").feature("blk9").setIndex("size", "d_conductor", 2);
    model.component("comp1").geom("geom1").feature("blk9")
         .set("pos", new String[]{"width-(width-2*pitch-network_width)/2-pitch-leg_width", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk9")
         .setIndex("pos", "(length-2*pitch-network_length)/2+pitch", 1);
    model.component("comp1").geom("geom1").feature("blk9")
         .setIndex("pos", "d_ceramics+mod(n_width,2)*(d_conductor+leg_height)", 2);
    model.component("comp1").geom("geom1").run("blk9");
    model.component("comp1").geom("geom1").create("arr8", "Array");
    model.component("comp1").geom("geom1").feature("arr8").selection("input").set("blk9");
    model.component("comp1").geom("geom1").feature("arr8").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr8").set("linearsize", "floor((n_length)/2)");
    model.component("comp1").geom("geom1").feature("arr8")
         .set("displ", new String[]{"0", "2*(leg_length+pitch)", "0"});
    model.component("comp1").geom("geom1").feature("arr8").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("arr8");
    model.component("comp1").geom("geom1").create("blk10", "Block");
    model.component("comp1").geom("geom1").feature("blk10").set("size", new String[]{"width", "length", "1"});
    model.component("comp1").geom("geom1").feature("blk10").setIndex("size", "d_ceramics", 2);
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("Ceramics, Bottom");
    model.component("comp1").geom("geom1").feature("blk10").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("blk10");
    model.component("comp1").geom("geom1").create("blk11", "Block");
    model.component("comp1").geom("geom1").feature("blk11").set("size", new String[]{"width", "length", "1"});
    model.component("comp1").geom("geom1").feature("blk11").setIndex("size", "d_ceramics", 2);
    model.component("comp1").geom("geom1").feature("blk11").set("pos", new String[]{"0", "0", "height-d_ceramics"});
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("Ceramics, Top");
    model.component("comp1").geom("geom1").feature("blk11").set("contributeto", "csel6");
    model.component("comp1").geom("geom1").run("blk11");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("Hot Side");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("blk10", 1);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("Cold Side");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("blk11", 4);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("Terminal");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("blk5", 1);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("Ceramics Faces Toward Interior");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("blk10", 4);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("blk11", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("Ceramics");
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_csel5_dom", "geom1_csel6_dom"});
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("Conductors, Bottom");
    model.component("comp1").selection("box1").set("zmin", "d_ceramics/2");
    model.component("comp1").selection("box1").set("zmax", "d_ceramics+d_conductor+leg_height/2");
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("Conductors, Top");
    model.component("comp1").selection("box2").set("zmin", "d_ceramics+d_conductor+leg_height/2");
    model.component("comp1").selection("box2").set("zmax", "d_ceramics+5*d_conductor/2+leg_height");
    model.component("comp1").selection("box2").set("condition", "inside");
    model.component("comp1").selection().create("box3", "Box");
    model.component("comp1").selection("box3").label("Mesh, Mapped Surfaces");
    model.component("comp1").selection("box3").set("entitydim", 2);
    model.component("comp1").selection("box3").set("zmin", "d_ceramics+d_conductor/2");
    model.component("comp1").selection("box3").set("zmax", "d_ceramics+d_conductor+leg_height/2");
    model.component("comp1").selection("box3").set("condition", "inside");
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("Thermocouples");
    model.component("comp1").selection("uni2").set("input", new String[]{"geom1_csel1_dom", "geom1_csel2_dom"});
    model.component("comp1").selection().create("uni3", "Union");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").selection("uni3").label("Electric Domains");
    model.component("comp1").selection("uni3").set("input", new String[]{"uni2", "geom1_csel3_dom"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("Mesh, Ceramics Faces Toward Interior");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"geom1_sel4"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"geom1_csel3_bnd"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("Ground");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"geom1_csel4_bnd", "geom1_sel4"});

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("Average: Hot Side");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("geom1_sel1");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").label("Average: Cold Side");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().named("geom1_sel2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("dT", "aveop1(T)-aveop2(T)");

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("N-Type Legs");
    model.component("comp1").material("matlnk1").selection().named("geom1_csel1_dom");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("P-Type Legs");
    model.component("comp1").material("matlnk2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("matlnk2").set("link", "mat3");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat5").label("Tungsten");
    model.component("comp1").material("mat5").set("family", "custom");
    model.component("comp1").material("mat5")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5")
         .set("customambient", new double[]{0.7058823529411765, 0.7058823529411765, 0.7058823529411765});
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("fresnel", 0.9);
    model.component("comp1").material("mat5").set("roughness", 0.15);
    model.component("comp1").material("mat5").set("diffusewrap", 0);
    model.component("comp1").material("mat5").set("reflectance", 0);
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"4.5e-6[1/K]", "0", "0", "0", "4.5e-6[1/K]", "0", "0", "0", "4.5e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "17800[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "132[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"175[W/(m*K)]", "0", "0", "0", "175[W/(m*K)]", "0", "0", "0", "175[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "360[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("l", "-4.7e11[Pa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("m", "-7.9e11[Pa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("n", "-1.1e12[Pa]");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat6").label("Copper");
    model.component("comp1").material("mat6").set("family", "copper");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat5").selection().named("uni1");
    model.component("comp1").material("mat6").selection().named("geom1_csel3_dom");

    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "2s");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "Tref");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("geom1_sel1");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "Tref");
    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 2);
    model.component("comp1").physics("ht").feature("bhs1").label("Boundary Heat Source (Study 2)");
    model.component("comp1").physics("ht").feature("bhs1").selection().named("geom1_sel2");
    model.component("comp1").physics("ht").feature("bhs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("bhs1").set("Pb", "Qmax");
    model.component("comp1").physics("ht").create("bhs2", "BoundaryHeatSource", 2);
    model.component("comp1").physics("ht").feature("bhs2").label("Boundary Heat Source (Study 4)");
    model.component("comp1").physics("ht").feature("bhs2").selection().named("geom1_sel2");
    model.component("comp1").physics("ht").feature("bhs2").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("bhs2").set("Pb", "Qvar");
    model.component("comp1").physics("ht").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("ht").feature("ge1").label("Global Equations (Study 2)");
    model.component("comp1").physics("ht").feature("ge1").setIndex("name", "Qmax", 0, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("equation", "dT", 0, 0);
    model.component("comp1").physics("ht").feature("ge1").set("DependentVariableQuantity", "power");
    model.component("comp1").physics("ht").feature("ge1").set("SourceTermQuantity", "temperature");
    model.component("comp1").physics("ht").create("ge2", "GlobalEquations", -1);
    model.component("comp1").physics("ht").feature("ge2").label("Global Equations (Study 4)");
    model.component("comp1").physics("ht").feature("ge2").setIndex("name", "Qvar", 0, 0);
    model.component("comp1").physics("ht").feature("ge2").setIndex("equation", "dT-dT0", 0, 0);
    model.component("comp1").physics("ht").feature("ge2").set("DependentVariableQuantity", "power");
    model.component("comp1").physics("ht").feature("ge2").set("SourceTermQuantity", "temperature");
    model.component("comp1").physics("ec").selection().named("uni3");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().named("int1");
    model.component("comp1").physics("ec").create("term1", "Terminal", 2);
    model.component("comp1").physics("ec").feature("term1").label("Terminal (Study 1 and 2)");
    model.component("comp1").physics("ec").feature("term1").selection().named("geom1_sel3");
    model.component("comp1").physics("ec").feature("term1").set("I0", "Imax");
    model.component("comp1").physics("ec").create("term2", "Terminal", 2);
    model.component("comp1").physics("ec").feature("term2").label("Terminal (Study 3 and 4)");
    model.component("comp1").physics("ec").feature("term2").selection().named("geom1_sel3");
    model.component("comp1").physics("ec").feature("term2").set("I0", "I0*Imax[A]");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics().create("opt", "GeneralOptimization", "geom1");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "dTmax", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "dT-dTmax", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "temperature");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "temperature");
    model.component("comp1").physics("opt").create("gobj1", "GlobalObjective", -1);
    model.component("comp1").physics("opt").feature("gobj1").set("objectiveExpression", "dTmax^2/(100[K])^2");
    model.component("comp1").physics("opt").create("gcvar1", "GlobalControlVariables", -1);
    model.component("comp1").physics("opt").feature("gcvar1").setIndex("variableList", "Imax", 0, 0);
    model.component("comp1").physics("opt").feature("gcvar1").setIndex("initList", 1, 0, 0);
    model.component("comp1").physics("opt").feature("gcvar1").setIndex("lowerBoundList", 0.1, 0, 0);
    model.component("comp1").physics("opt").feature("gcvar1").setIndex("upperBoundList", 10, 0, 0);

    model.component("comp1").multiphysics("tee1").selection().named("uni2");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("box3");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "min(leg_width,leg_length)/2");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1")
         .set("hmax", "min(leg_width,leg_length)/4");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1")
         .set("hmin", "min(leg_width,leg_length)/6");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("box1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("uni2");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemcount", 9);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().named("box2");
    model.component("comp1").mesh("mesh1").feature("swe3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().named("dif1");
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe4").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe4").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("swe4").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe4").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("swe4").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std1").feature("stat").setSolveFor("/physics/opt", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/tee1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std2").feature("stat").setSolveFor("/physics/opt", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/tee1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/emh1", true);
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std3").feature("stat").setSolveFor("/physics/opt", false);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/tee1", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/emh1", true);
    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std4").feature("stat").setSolveFor("/physics/opt", false);
    model.study("std4").feature("stat").setSolveFor("/multiphysics/tee1", true);
    model.study("std4").feature("stat").setSolveFor("/multiphysics/emh1", true);
    model.study("std1").label("Study 1: Optimal Performance");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("opt", "Optimization");
    model.study("std1").feature("opt").set("optsolver", "snopt");
    model.study("std1").feature("opt").set("opttolinner", "1.0E-3");
    model.study("std1").feature("opt").set("nsolvemax", 20);
    model.study("std1").feature("opt").set("objectivetype", "maximization");
    model.study("std1").feature("opt").set("useobjtable", false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"ht/bhs1", "ht/bhs2", "ht/ge1", "ht/ge2", "ec/term2"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("o1").feature("s1").create("fc1", "FullyCoupled");

    model.study("std2").label("Study 2: Maximum Heat Dissipation");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"ht/bhs2", "ht/ge2", "ec/term2"});
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");

    model.study("std3").label("Study 3: Temperature Difference vs. Electric Current");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "length", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "length", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "I0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(0.1,0.1,1.1)", 0);
    model.study("std3").feature("param").setIndex("punit", 1, 0);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat")
         .set("disabledphysics", new String[]{"ht/bhs1", "ht/bhs2", "ht/ge1", "ht/ge2", "ec/term1"});
    model.study("std3").feature("stat").set("usesol", true);
    model.study("std3").feature("stat").set("notsolmethod", "sol");
    model.study("std3").feature("stat").set("notstudy", "std1");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("s1").create("fc1", "FullyCoupled");

    model.study("std4").label("Study 4: Coefficient of Performance");
    model.study("std4").setGenPlots(false);
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").set("sweeptype", "filled");
    model.study("std4").feature("param").setIndex("pname", "length", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "length", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "dT0", 0);
    model.study("std4").feature("param").setIndex("plistarr", "20 40 60", 0);
    model.study("std4").feature("param").setIndex("punit", "K", 0);
    model.study("std4").feature("param").setIndex("pname", "length", 1);
    model.study("std4").feature("param").setIndex("plistarr", "", 1);
    model.study("std4").feature("param").setIndex("punit", "m", 1);
    model.study("std4").feature("param").setIndex("pname", "length", 1);
    model.study("std4").feature("param").setIndex("plistarr", "", 1);
    model.study("std4").feature("param").setIndex("punit", "m", 1);
    model.study("std4").feature("param").setIndex("pname", "I0", 1);
    model.study("std4").feature("param").setIndex("plistarr", "range(0.1,0.05,0.3) range(0.3,0.2,1)", 1);
    model.study("std4").feature("param").setIndex("punit", 1, 1);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"ht/bhs1", "ht/ge1", "ec/term1"});
    model.study("std4").feature("stat").set("usesol", true);
    model.study("std4").feature("stat").set("notsolmethod", "sol");
    model.study("std4").feature("stat").set("notstudy", "std1");
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("s1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");
    model.study("std1").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std1").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std1").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std1").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Maximum Temperature Difference");
    model.result().numerical("gev1").set("expr", new String[]{"dTmax"});
    model.result().numerical("gev1").set("descr", new String[]{"State variable dTmax"});
    model.result().numerical("gev1").set("unit", new String[]{"K"});
    model.result().numerical("gev1").setIndex("descr", "Maximum temperature difference", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Maximum Temperature Difference");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("Required Current for Maximum Temperature Difference");
    model.result().numerical("gev2").set("expr", new String[]{"ec.I0_1"});
    model.result().numerical("gev2").set("descr", new String[]{"Terminal current"});
    model.result().numerical("gev2").set("unit", new String[]{"A"});
    model.result().numerical("gev2").setIndex("descr", "Required current for maximum temperature difference", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Required Current for Maximum Temperature Difference");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("Required Voltage for Maximum Temperature Difference");
    model.result().numerical("gev3").set("expr", new String[]{"ec.V0_1"});
    model.result().numerical("gev3").set("descr", new String[]{"Terminal voltage"});
    model.result().numerical("gev3").set("unit", new String[]{"V"});
    model.result().numerical("gev3").setIndex("descr", "Required voltage for maximum temperature difference", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Required Voltage for Maximum Temperature Difference");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("Overall Electric Resistance");
    model.result().numerical("gev4").set("expr", new String[]{"ec.R11"});
    model.result().numerical("gev4").set("descr", new String[]{"Resistance"});
    model.result().numerical("gev4").set("unit", new String[]{"\u03a9"});
    model.result().numerical("gev4").setIndex("descr", "Overall electric resistance", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("Overall Electric Resistance");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").setResult();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").label("Maximum Heat Dissipation");
    model.result().numerical("gev5").set("data", "dset2");
    model.result().numerical("gev5").set("expr", new String[]{"Qmax"});
    model.result().numerical("gev5").set("descr", new String[]{"State variable Qmax"});
    model.result().numerical("gev5").set("unit", new String[]{"W"});
    model.result().numerical("gev5").setIndex("descr", "Maximum heat dissipation", 0);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("Maximum Heat Dissipation");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").setResult();

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Temperature (ht)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Electric Potential (ec)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "V");
    model.result("pg2").feature("vol1").set("colortable", "Dipole");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Electric Field (ec)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "ec.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Temperature Difference vs. Electric Current");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "I (A)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\\Delta T (K)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "dT", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "K", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Temperature difference", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "", 2);
    model.result("pg4").feature("glob1").remove("unit", 2);
    model.result("pg4").feature("glob1").remove("descr", 2);
    model.result("pg4").feature("glob1").remove("expr", new int[]{2});
    model.result("pg4").feature("glob1").setIndex("unit", "", 1);
    model.result("pg4").feature("glob1").remove("unit", 1);
    model.result("pg4").feature("glob1").remove("descr", 1);
    model.result("pg4").feature("glob1").remove("expr", new int[]{1});
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "ec.I0_2");
    model.result("pg4").feature("glob1").set("xdatadescr", "Terminal current");
    model.result("pg4").feature("glob1").set("linewidth", 3);
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").run();

    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().numerical().create("gev6", "EvalGlobal");
    model.result().numerical("gev6").label("Coefficient of Performance");
    model.result().numerical("gev6").set("data", "dset4");
    model.result().numerical("gev6").set("tablecols", "level2");
    model.result().numerical("gev6").set("expr", new String[]{});
    model.result().numerical("gev6").set("descr", new String[]{});
    model.result().numerical("gev6").setIndex("expr", "Qvar/(ec.V0_2*ec.I0_2)", 0);
    model.result().numerical("gev6").setIndex("descr", "Coefficient of performance", 0);
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("Coefficient of Performance");
    model.result().numerical("gev6").set("table", "tbl6");
    model.result().numerical("gev6").setResult();
    model.result().numerical().create("gev7", "EvalGlobal");
    model.result().numerical("gev7").label("Maximum Coefficient of Performance");
    model.result().numerical("gev7").set("data", "dset4");
    model.result().numerical("gev7").set("tablecols", "level2");
    model.result().numerical("gev7").set("expr", new String[]{});
    model.result().numerical("gev7").set("descr", new String[]{});
    model.result().numerical("gev7").setIndex("expr", "Qvar/(ec.V0_2*ec.I0_2)", 0);
    model.result().numerical("gev7").setIndex("descr", "Maximum coefficient of performance", 0);
    model.result().numerical("gev7").set("dataseries", "maximum");
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").comments("Maximum Coefficient of Performance");
    model.result().numerical("gev7").set("table", "tbl7");
    model.result().numerical("gev7").setResult();
    model.result().numerical().create("av1", "AvVolume");
    model.result().numerical("av1").label("Figure of Merit");
    model.result().numerical("av1").selection().named("geom1_csel1_dom");
    model.result().numerical("av1").set("expr", new String[]{"tee1.Zmean"});
    model.result().numerical("av1").set("descr", new String[]{"Mean figure of merit"});
    model.result().numerical("av1").set("unit", new String[]{"1/K"});
    model.result().numerical("av1").setIndex("descr", "Figure of merit", 0);
    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8").comments("Figure of Merit");
    model.result().numerical("av1").set("table", "tbl8");
    model.result().numerical("av1").setResult();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "table");
    model.result("pg5").feature("tblp1").set("table", "tbl6");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("linewidth", 3);
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("legendmethod", "manual");
    model.result("pg5").feature("tblp1").setIndex("legends", "\\Delta<i>T</i>: 20 K", 0);
    model.result("pg5").feature("tblp1").setIndex("legends", "\\Delta<i>T</i>: 40 K", 1);
    model.result("pg5").feature("tblp1").setIndex("legends", "\\Delta<i>T</i>: 60 K", 2);
    model.result("pg5").run();
    model.result("pg5").label("Coefficient of Performance");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "I/Imax");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "COP");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").run();
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset2");
    model.result().dataset("grid1").set("par1", "Q");
    model.result().dataset("grid1").set("parmax1", 1000);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("Temperature Difference vs. Heat Dissipation");
    model.result("pg6").set("data", "grid1");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\\Delta T(Q)");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "Q (W)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\\Delta T (K)");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1")
         .set("expr", "if(dTmax-(dTmax/Qmax)*root.Q>0,dTmax-(dTmax/Qmax)*root.Q,NaN)");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "root.Q");
    model.result("pg6").feature("lngr1").set("linewidth", 3);
    model.result("pg1").run();

    model.title("\u70ed\u7535\u51b7\u5374\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u5c06\u6750\u6599\u7684\u5916\u89c2\u3001\u989c\u8272\u548c\u7eb9\u7406\u53ef\u89c6\u5316\n\u2022 \u6839\u636e\u9009\u5b9a\u7684\u7ed8\u56fe\u64cd\u4f5c\uff0c\u5728\u56fe\u5f62\u4e0b\u65b9\u663e\u793a\u6709\u5173\u51e0\u4f55\u53c2\u6570\u3001\u7ed3\u679c\u548c\u6027\u80fd\u7684\u4fe1\u606f\n\n\u70ed\u7535\u51b7\u5374\u5668\u5e7f\u6cdb\u7528\u4e8e\u7535\u5b50\u51b7\u5374\uff0c\u6d89\u53ca\u4ece\u6d88\u8d39\u54c1\u5230\u822a\u5929\u5668\u8bbe\u8ba1\u7684\u5404\u79cd\u5e94\u7528\u9886\u57df\u3002\u70ed\u7535\u6a21\u5757\u662f\u70ed\u7535\u5e94\u7528\u4e2d\u5e38\u7528\u7684\u4e00\u79cd\u7ec4\u4ef6\u7c7b\u578b\u3002\u5178\u578b\u7684\u6a21\u5757\u7531\u5939\u5728\u4e24\u5757\u5bfc\u70ed\u677f\uff08\u4e00\u4e2a\u51b7\u677f\u548c\u4e00\u4e2a\u70ed\u677f\uff09\u4e4b\u95f4\u7684\u51e0\u4e2a\u70ed\u7535\u81c2\u7ec4\u6210\u3002\u9700\u8981\u51b7\u5374\u7684\u8bbe\u5907\u5fc5\u987b\u8fde\u63a5\u5230\u51b7\u7684\u4e00\u9762\u3002\n\n\u7531\u4e8e\u5e94\u7528\u7684\u591a\u6837\u6027\uff0c\u70ed\u7535\u51b7\u5374\u5668\u53ef\u80fd\u4f1a\u6709\u8bb8\u591a\u4e0d\u540c\u7684\u914d\u7f6e\u3002\u6b64 App \u6db5\u76d6\u5177\u6709\u5404\u79cd\u70ed\u7535\u5076\u5c3a\u5bf8\u548c\u5206\u5e03\u7684\u4e0d\u540c\u5927\u5c0f\u7684\u5355\u7ea7\u70ed\u7535\u51b7\u5374\u5668\u7684\u57fa\u672c\u8bbe\u8ba1\u3002\u5982\u679c\u60a8\u5e0c\u671b\u4f7f\u7528\u9644\u52a0\u7684\u8f93\u5165\u9009\u9879\u8fdb\u884c\u66f4\u8be6\u7ec6\u7684\u8ba1\u7b97\uff0c\u4e5f\u53ef\u4ee5\u4f7f\u7528\u6b64 App \u4f5c\u4e3a\u8d77\u70b9\uff0c\u5c06\u5176\u6269\u5c55\u4e3a\u591a\u7ea7\u70ed\u7535\u51b7\u5374\u5668\u3002");

    model.label("thermoelectric_cooler.mph");

    model.result("pg1").run();

    model.setExpectedComputationTime("7 \u5206\u949f");

    model.param()
         .set("N", "(floor((length-2*pitch-leg_length)/(leg_length+pitch))+1-mod(floor((length-2*pitch-leg_length)/(leg_length+pitch))+1,2))*((floor((width-2*pitch-leg_width)/(leg_width+pitch))+1))/2");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///thermoelectric_cooler.docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("logo", "none");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").label("\u76ee\u5f55");
    model.result().report("rpt1").feature("toc1").set("levels", "1");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u8f6f\u4ef6\u5c5e\u6027");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includepath", false);
    model.result().report("rpt1").feature("sec1").feature("root1").set("includename", false);
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeusedproducts", false);
    model.result().report("rpt1").feature("sec1").feature().create("head1", "Heading");
    model.result().report("rpt1").feature("sec1").feature("head1").set("text", "\u7814\u7a76 1");
    model.result().report("rpt1").feature("sec1").feature("head1").set("sublevel", "level2");
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature().create("head2", "Heading");
    model.result().report("rpt1").feature("sec1").feature("head2").set("text", "\u7814\u7a76 2");
    model.result().report("rpt1").feature("sec1").feature("head2").set("sublevel", "level2");
    model.result().report("rpt1").feature("sec1").feature().create("std2", "Study");
    model.result().report("rpt1").feature("sec1").feature("std2").set("noderef", "std2");
    model.result().report("rpt1").feature("sec1").feature("std2").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature().create("head3", "Heading");
    model.result().report("rpt1").feature("sec1").feature("head3").set("text", "\u7814\u7a76 3");
    model.result().report("rpt1").feature("sec1").feature().create("std3", "Study");
    model.result().report("rpt1").feature("sec1").feature("std3").set("noderef", "std3");
    model.result().report("rpt1").feature("sec1").feature("std3").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std3").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature().create("head4", "Heading");
    model.result().report("rpt1").feature("sec1").feature("head4").set("text", "\u7814\u7a76 4");
    model.result().report("rpt1").feature("sec1").feature("head4").set("sublevel", "level2");
    model.result().report("rpt1").feature("sec1").feature().create("std4", "Study");
    model.result().report("rpt1").feature("sec1").feature("std4").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std4").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature("head3").set("sublevel", "level2");
    model.result().report("rpt1").feature("sec1").feature("std1").set("commentssource", "custom");
    model.result().report("rpt1").feature("sec1").feature("std1")
         .set("comments", "\u8ba1\u7b97\u9676\u74f7\u677f\u4e4b\u95f4\u7684\u6e29\u5dee\u8fbe\u5230\u6700\u5927\u503c\uff08\u5373\u8fbe\u5230\u6700\u5927\u51b7\u5374\u91cf\uff09\u65f6\u7684\u6700\u4f73\u5de5\u4f5c\u6761\u4ef6\uff0c\u5176\u4e2d\u4f7f\u7528\u4f18\u5316\u6c42\u89e3\u5668\u67e5\u627e\u4ea7\u751f\u6700\u5927\u6e29\u5dee\u7684\u5916\u52a0\u7535\u6d41\uff0c\u8fd8\u4f1a\u8fd4\u56de\u76f8\u5e94\u7684\u7535\u538b\u548c\u7535\u963b\u3002");
    model.result().report("rpt1").feature("sec1").feature("std2").set("commentssource", "custom");
    model.result().report("rpt1").feature("sec1").feature("std2")
         .set("comments", "\u5728\u6b64\u7814\u7a76\u4e2d\uff0c\u6700\u4f73\u7535\u6d41\u4e3a\u70ed\u7535\u51b7\u5374\u5668\u4e2d\u7684\u5de5\u4f5c\u7535\u6d41\uff0c\u8ba1\u7b97\u6b64\u6761\u4ef6\u4e0b\u9676\u74f7\u677f\u4e4b\u95f4\u7684\u6e29\u5dee\u4e3a\u96f6\u65f6\u7684\u9650\u5236\u8017\u6563\u529f\u7387\u3002");
    model.result().report("rpt1").feature("sec1").feature("std3").set("commentssource", "custom");
    model.result().report("rpt1").feature("sec1").feature("std3")
         .set("comments", "\u8ba1\u7b97\u4e0d\u540c\u6307\u5b9a\u5916\u52a0\u7535\u6d41\uff08\u4ece 0.1 Imax \u589e\u52a0\u5230 1.1 Imax\uff09\u65f6\u9676\u74f7\u677f\u4e4b\u95f4\u7684\u6e29\u5dee\uff0c\u7ed3\u679c\u7528\u4e8e\u751f\u6210\u6e29\u5dee vs. \u7535\u6d41\u7684\u6027\u80fd\u56fe\u3002");
    model.result().report("rpt1").feature("sec1").feature("std4").set("commentssource", "custom");
    model.result().report("rpt1").feature("sec1").feature("std4")
         .set("comments", "\u901a\u8fc7\u53cc\u53c2\u6570\u5316\u626b\u63cf\u8ba1\u7b97\u6e29\u5dee\u4e3a 20 K\u300140 K \u548c 60 K \u65f6\u4e0d\u540c\u6307\u5b9a\u5916\u52a0\u7535\u6d41\u7684\u70ed\u8017\u7387\u548c\u7535\u529f\u7387\uff0c\u7ed3\u679c\u7528\u4e8e\u751f\u6210\u6e29\u5dee\u503c\u4e0d\u540c\u65f6\u7684\u6027\u80fd\u7cfb\u6570\u66f2\u7ebf\u4ee5\u53ca\u70ed\u8017\u7387\u4e0e\u7535\u529f\u7387\u7684\u6bd4\u503c\u3002");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1").label("\u53c2\u6570 - \u51e0\u4f55");
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec2").feature().create("param2", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param2").label("\u53c2\u6570 - \u5de5\u4f5c\u6761\u4ef6");
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature("param2").setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec2").feature().create("head1", "Heading");
    model.result().report("rpt1").feature("sec2").feature("head1").set("text", "\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature().create("txt1", "Text");
    model.result().report("rpt1").feature("sec2").feature("txt1")
         .set("text", "\u5bf9\u4e8e n \u578b\u534a\u5bfc\u4f53\u6750\u6599\uff0c\u4f7f\u7528\u7684\u662f\u5e26\u8d1f\u53f7\u7684\u585e\u8d1d\u514b\u7cfb\u6570\uff0cp \u578b\u534a\u5bfc\u4f53\u7684\u585e\u8d1d\u514b\u7cfb\u6570\u5219\u5e94\u4e3a\u6b63\u3002");
    model.result().report("rpt1").feature("sec2").feature().create("mat1", "Material");
    model.result().report("rpt1").feature("sec2").feature("mat1").set("includeselection", false);
    model.result().report("rpt1").feature("sec2").feature("mat1").set("includesettings", false);
    model.result().report("rpt1").feature("sec2").feature("mat1").set("noderef", "matlnk1");
    model.result().report("rpt1").feature("sec2").feature("mat1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature().create("mat2", "Material");
    model.result().report("rpt1").feature("sec2").feature("mat2").set("includeselection", false);
    model.result().report("rpt1").feature("sec2").feature("mat2").set("includesettings", false);
    model.result().report("rpt1").feature("sec2").feature("mat2").set("noderef", "matlnk2");
    model.result().report("rpt1").feature("sec2").feature("mat2").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature().create("mat3", "Material");
    model.result().report("rpt1").feature("sec2").feature("mat3").set("noderef", "mat5");
    model.result().report("rpt1").feature("sec2").feature("mat3").set("includesettings", false);
    model.result().report("rpt1").feature("sec2").feature("mat3").set("includeselection", false);
    model.result().report("rpt1").feature("sec2").feature("mat3").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("mat3").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("mat3").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature().create("mat4", "Material");
    model.result().report("rpt1").feature("sec2").feature("mat4").set("noderef", "mat6");
    model.result().report("rpt1").feature("sec2").feature("mat4").set("includesettings", false);
    model.result().report("rpt1").feature("sec2").feature("mat4").set("includeselection", false);
    model.result().report("rpt1").feature("sec2").feature("mat4").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("mat4").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("mat4").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u6a21\u578b");
    model.result().report("rpt1").feature("sec3").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec3").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec3").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec4").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec4").feature("param1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec4").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").label("\u6700\u5927\u6e29\u5dee");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl2", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl2")
         .label("\u8fbe\u5230\u6700\u5927\u6e29\u5dee\u6240\u9700\u7684\u7535\u6d41");
    model.result().report("rpt1").feature("sec4").feature("mtbl2").set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec4").feature("mtbl2").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl3", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl3")
         .label("\u8fbe\u5230\u6700\u5927\u6e29\u5dee\u6240\u9700\u7684\u7535\u538b");
    model.result().report("rpt1").feature("sec4").feature("mtbl3").set("noderef", "tbl3");
    model.result().report("rpt1").feature("sec4").feature("mtbl3").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl4", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl4").label("\u603b\u7535\u963b");
    model.result().report("rpt1").feature("sec4").feature("mtbl4").set("noderef", "tbl4");
    model.result().report("rpt1").feature("sec4").feature("mtbl4").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl5", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl5").label("\u6700\u5927\u6563\u70ed\u91cf");
    model.result().report("rpt1").feature("sec4").feature("mtbl5").set("noderef", "tbl5");
    model.result().report("rpt1").feature("sec4").feature("mtbl5").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl6", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl6").label("\u6027\u80fd\u7cfb\u6570");
    model.result().report("rpt1").feature("sec4").feature("mtbl6").set("noderef", "tbl6");
    model.result().report("rpt1").feature("sec4").feature("mtbl6").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl7", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl7").label("\u6700\u5927\u6027\u80fd\u7cfb\u6570");
    model.result().report("rpt1").feature("sec4").feature("mtbl7").set("noderef", "tbl7");
    model.result().report("rpt1").feature("sec4").feature("mtbl7").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl8", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl8").label("\u54c1\u8d28\u56e0\u6570");
    model.result().report("rpt1").feature("sec4").feature("mtbl8").set("noderef", "tbl8");
    model.result().report("rpt1").feature("sec4").feature("mtbl8").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg1").label("\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg2").label("\u6e29\u5dee vs. \u6563\u70ed\u91cf");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("noderef", "pg6");
    model.result().report("rpt1").feature("sec4").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg3").label("\u6e29\u5dee vs. \u7535\u6d41");
    model.result().report("rpt1").feature("sec4").feature("pg3").set("noderef", "pg4");
    model.result().report("rpt1").feature("sec4").feature().create("pg4", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg4").label("\u6027\u80fd\u7cfb\u6570");
    model.result().report("rpt1").feature("sec4").feature("pg4").set("noderef", "pg5");
    model.result("pg1").run();

    model.title("\u70ed\u7535\u51b7\u5374\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u5c06\u6750\u6599\u7684\u5916\u89c2\u3001\u989c\u8272\u548c\u7eb9\u7406\u53ef\u89c6\u5316\n\u2022 \u6839\u636e\u9009\u5b9a\u7684\u7ed8\u56fe\u64cd\u4f5c\uff0c\u5728\u56fe\u5f62\u4e0b\u65b9\u663e\u793a\u6709\u5173\u51e0\u4f55\u53c2\u6570\u3001\u7ed3\u679c\u548c\u6027\u80fd\u7684\u4fe1\u606f\n\n\u70ed\u7535\u51b7\u5374\u5668\u5e7f\u6cdb\u7528\u4e8e\u7535\u5b50\u51b7\u5374\uff0c\u6d89\u53ca\u4ece\u6d88\u8d39\u54c1\u5230\u822a\u5929\u5668\u8bbe\u8ba1\u7684\u5404\u79cd\u5e94\u7528\u9886\u57df\u3002\u70ed\u7535\u6a21\u5757\u662f\u70ed\u7535\u5e94\u7528\u4e2d\u5e38\u7528\u7684\u4e00\u79cd\u7ec4\u4ef6\u7c7b\u578b\u3002\u5178\u578b\u7684\u6a21\u5757\u7531\u5939\u5728\u4e24\u5757\u5bfc\u70ed\u677f\uff08\u4e00\u4e2a\u51b7\u677f\u548c\u4e00\u4e2a\u70ed\u677f\uff09\u4e4b\u95f4\u7684\u51e0\u4e2a\u70ed\u7535\u81c2\u7ec4\u6210\u3002\u9700\u8981\u51b7\u5374\u7684\u8bbe\u5907\u5fc5\u987b\u8fde\u63a5\u5230\u51b7\u7684\u4e00\u9762\u3002\n\n\u7531\u4e8e\u5e94\u7528\u7684\u591a\u6837\u6027\uff0c\u70ed\u7535\u51b7\u5374\u5668\u53ef\u80fd\u4f1a\u6709\u8bb8\u591a\u4e0d\u540c\u7684\u914d\u7f6e\u3002\u6b64 App \u6db5\u76d6\u5177\u6709\u5404\u79cd\u70ed\u7535\u5076\u5c3a\u5bf8\u548c\u5206\u5e03\u7684\u4e0d\u540c\u5927\u5c0f\u7684\u5355\u7ea7\u70ed\u7535\u51b7\u5374\u5668\u7684\u57fa\u672c\u8bbe\u8ba1\u3002\u5982\u679c\u60a8\u5e0c\u671b\u4f7f\u7528\u9644\u52a0\u7684\u8f93\u5165\u9009\u9879\u8fdb\u884c\u66f4\u8be6\u7ec6\u7684\u8ba1\u7b97\uff0c\u4e5f\u53ef\u4ee5\u4f7f\u7528\u6b64 App \u4f5c\u4e3a\u8d77\u70b9\uff0c\u5c06\u5176\u6269\u5c55\u4e3a\u591a\u7ea7\u70ed\u7535\u51b7\u5374\u5668\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("thermoelectric_cooler.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
