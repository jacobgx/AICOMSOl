/*
 * li_battery_pack_designer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class li_battery_pack_designer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("lb", "LumpedBattery");

    model.param().label("\u53c2\u6570\uff1a\u51e0\u4f55\u5e8f\u5217");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d_batt", "21[mm]", "Battery diameter");
    model.param().set("r_batt", "d_batt/2", "Battery radius");
    model.param().set("h_batt", "70[mm]", "Battery height");
    model.param().set("nx_batt", "6", "Number of batteries in x-direction");
    model.param().set("ny_batt", "4", "Number of batteries in y-direction");
    model.param().set("d_term", "6[mm]", "Terminal diameter");
    model.param().set("r_term", "d_term/2", "Terminal radius");
    model.param().set("h_term", "1[mm]", "Terminal thickness");
    model.param().set("d_sc", "3[mm]", "Serial connector width");
    model.param().set("h_sc", "1[mm]", "Bus bar thickness");
    model.param().set("w_pc", "1[mm]", "Parallel connector width");
    model.param().set("isEven", "if(floor(nx_batt/2)<nx_batt/2, 0, 1)", "Help parameter for geometry creation");
    model.param().set("beta", "atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad]", "Angle for connecting battery rows");
    model.param().set("stacking_type", "1", "0 = straight packing, 1 = offset packing");
    model.param().set("n_batt", "nx_batt*ny_batt", "Number of batteries");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u53c2\u6570\u4f30\u8ba1");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("eta_IR_1C", "10[mV]", "Ohmic overpotential at 1C, fitting parameter");
    model.param("par2").set("invJ0", "1", "Inverse dimensionless charge exchange current, fitting parameter");
    model.param("par2").set("tau", "1000[s]", "Diffusion time constant, fitting parameter");
    model.param("par2").set("J0", "1/invJ0", "Dimensionless charge exchange current");
    model.param("par2").set("Q_cell0", "4[A*h]", "Battery capacity");
    model.param("par2").set("SOC_0", "0.3797", "Initial state of charge");
    model.param("par2").set("T_ref", "25[degC]", "Reference temperature");
    model.param("par2").set("opt_tol", "0.01", "Optimality tolerance");
    model.param("par2").set("output_steps", "11", "Output steps");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570\uff1a\u70ed\u7ba1\u7406");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("C_rate", "4", "C rate");
    model.param("par3").set("Q_cell", "4[A*h]", "Battery cell capacity");
    model.param("par3").set("I_1C", "Q_cell/1[h]", "1C current");
    model.param("par3").set("kT_batt_ang", "30[W/(m*K)]", "Thermal conductivity, in plane");
    model.param("par3").set("kT_batt_r", "1[W/(m*K)]", "Thermal conductivity, cross plane");
    model.param("par3").set("Ea_eta1C", "24[kJ/mol]", "Activation energy");
    model.param("par3").set("Ea_J0", "-59[kJ/mol]", "Activation energy");
    model.param("par3").set("Ea_Tau", "24[kJ/mol]", "Activation energy");
    model.param("par3").set("T0", "25[degC]", "Reference temperature");
    model.param("par3").set("J0_0", "0.85", "J0 at reference temperature");
    model.param("par3").set("tau_0", "1000[s]", "tau at reference temperature");
    model.param("par3").set("eta_1C", "4.5[mV]", "eta_1C at reference temperature");
    model.param("par3").set("rho_batt", "2000[kg/m^3]", "Battery density");
    model.param("par3").set("Cp_batt", "1400[J/(kg*K)]", "Battery heat capacity");
    model.param("par3").set("ht", "30[W/(m^2*K)]", "Heat transfer coefficient, sides");
    model.param("par3").set("ht_top", "30[W/(m^2*K)]", "Heat transfer coefficient, top");
    model.param("par3").set("ht_bottom", "5[W/(m^2*K)]", "Heat transfer coefficient, bottom");
    model.param("par3").set("T_init", "20[degC]", "Initial/external temperature");
    model.param("par3").set("opt_eta_IR_1C", "0.0045162[V]", "Ohmic overpotential at 1C");
    model.param("par3").set("opt_invJ0", "1.1565", "Inverse dimensionless charge exchange current");
    model.param("par3").set("opt_J0", "0.86471", "Dimensionless charge exchange current");
    model.param("par3").set("opt_tau", "1374.4[s]", "Diffusion time constant");
    model.param("par3").set("initial_SOC", "1", "Initial state of charge");
    model.param("par3").set("final_SOC", "0.2", "Final state of charge");
    model.param("par3").set("C_sign", "sign(final_SOC-initial_SOC)", "Current direction");
    model.param("par3").set("number_of_steps", "11", "Number of steps for time-stepping");

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("li_battery_pack_designer_embedded_experimental_data.txt");
    model.result().table("tbl1").label("\u8d1f\u8f7d\u5468\u671f\u6570\u636e");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("\u5f00\u8def\u7535\u538b");
    model.result().table("tbl2").importData("li_battery_pack_designer_embedded_SOC_EOCV_interpolation.txt");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u8d1f\u8f7d\u5468\u671f\u6570\u636e");
    model.func("int1").set("source", "resultTable");
    model.func("int1").setIndex("argunit", "s", 0);
    model.func("int1").setEntry("funcnames", "col2", "E_cell_exp");
    model.func("int1").setEntry("columnType", "col3", "value");
    model.func("int1").setEntry("funcnames", "col3", "I_cell_exp");
    model.func().create("int2", "Interpolation");
    model.func("int2").label("\u5f00\u8def\u7535\u538b");
    model.func("int2").set("source", "resultTable");
    model.func("int2").set("resultTable", "tbl2");

    model.component("comp1").physics("lb").prop("BatterySettings").set("I_app", "I_cell_exp(t)[A]");
    model.component("comp1").physics("lb").prop("BatterySettings").set("Q_cell0", "Q_cell0");
    model.component("comp1").physics("lb").prop("BatterySettings").set("SOC_cell0", "SOC_0");
    model.component("comp1").physics("lb").feature("cep1").set("OpenCircuitVoltageInput", "fromdef");
    model.component("comp1").physics("lb").feature("cep1").set("Eocvdef", "int2");
    model.component("comp1").physics("lb").feature("cep1").set("Tref", "T_ref");
    model.component("comp1").physics("lb").feature("vl1").set("minput_temperature", "T_ref");
    model.component("comp1").physics("lb").feature("vl1").set("eta_ir1C", "eta_IR_1C");
    model.component("comp1").physics("lb").feature("vl1").set("J0", "J0");
    model.component("comp1").physics("lb").feature("vl1").set("IncludeConcentrationOverpotential", true);
    model.component("comp1").physics("lb").feature("vl1").set("tau", "tau");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 3);
    model.component("comp2").geom("geom1").geomRep("comsol");

    model.component("comp2").mesh().create("mesh1");
    model.component("comp2").mesh("mesh1").contribute("geom/detail", true);

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").label("\u96f6\u4ef6 1\uff1a\u76f4\u5305\u88c5");
    model.geom("part1").create("cyl1", "Cylinder");
    model.geom("part1").feature("cyl1").set("r", "r_batt");
    model.geom("part1").feature("cyl1").set("h", "h_batt");
    model.geom("part1").run("cyl1");
    model.geom("part1").create("arr1", "Array");
    model.geom("part1").feature("arr1").selection("input").set("cyl1");
    model.geom("part1").feature("arr1").set("fullsize", new String[]{"nx_batt", "ny_batt", "1"});
    model.geom("part1").feature("arr1").set("displ", new String[]{"d_batt", "d_batt", "0"});
    model.geom("part1").selection().create("csel1", "CumulativeSelection");
    model.geom("part1").selection("csel1").label("\u7535\u6c60");
    model.geom("part1").feature("arr1").set("contributeto", "csel1");
    model.geom("part1").run("arr1");
    model.geom("part1").create("cyl2", "Cylinder");
    model.geom("part1").feature("cyl2").set("r", "r_term");
    model.geom("part1").feature("cyl2").set("h", "h_term");
    model.geom("part1").feature("cyl2").set("pos", new String[]{"0", "0", "-h_term"});
    model.geom("part1").run("cyl2");
    model.geom("part1").create("arr2", "Array");
    model.geom("part1").feature("arr2").selection("input").set("cyl2");
    model.geom("part1").feature("arr2").set("fullsize", new String[]{"nx_batt", "ny_batt", "1"});
    model.geom("part1").feature("arr2").setIndex("fullsize", 2, 2);
    model.geom("part1").feature("arr2").set("displ", new String[]{"d_batt", "d_batt", "0"});
    model.geom("part1").feature("arr2").setIndex("displ", "h_batt+h_term", 2);
    model.geom("part1").selection().create("csel2", "CumulativeSelection");
    model.geom("part1").selection("csel2").label("\u7ec8\u7aef");
    model.geom("part1").feature("arr2").set("contributeto", "csel2");
    model.geom("part1").run("arr2");
    model.geom("part1").create("blk1", "Block");
    model.geom("part1").feature("blk1").set("size", new String[]{"d_batt", "d_sc", "h_sc"});
    model.geom("part1").feature("blk1").set("pos", new String[]{"-d_batt+w_pc/2", "0", "0"});
    model.geom("part1").feature("blk1").setIndex("pos", "-d_sc/2", 1);
    model.geom("part1").feature("blk1").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part1").run("blk1");
    model.geom("part1").create("arr3", "Array");
    model.geom("part1").feature("arr3").selection("input").set("blk1");
    model.geom("part1").feature("arr3").set("type", "linear");
    model.geom("part1").feature("arr3").set("linearsize", "ny_batt");
    model.geom("part1").feature("arr3").set("displ", new String[]{"0", "d_batt", "0"});
    model.geom("part1").selection().create("csel3", "CumulativeSelection");
    model.geom("part1").selection("csel3").label("\u6bcd\u7ebf");
    model.geom("part1").feature("arr3").set("contributeto", "csel3");
    model.geom("part1").run("arr3");
    model.geom("part1").create("copy1", "Copy");
    model.geom("part1").feature("copy1").selection("input").named("csel3");
    model.geom("part1").feature("copy1").set("displx", "(nx_batt)*d_batt-w_pc");
    model.geom("part1").feature("copy1").set("displz", "if(floor(nx_batt/2)<nx_batt/2, -h_batt-h_term*2-h_sc,0 )");
    model.geom("part1").feature("copy1").set("contributeto", "csel3");
    model.geom("part1").run("copy1");
    model.geom("part1").create("blk2", "Block");
    model.geom("part1").feature("blk2").set("size", new String[]{"d_batt+w_pc", "1", "1"});
    model.geom("part1").feature("blk2").setIndex("size", "d_sc", 1);
    model.geom("part1").feature("blk2").setIndex("size", "h_sc", 2);
    model.geom("part1").feature("blk2").set("pos", new String[]{"-w_pc/2", "-d_sc/2", "0"});
    model.geom("part1").feature("blk2").setIndex("pos", "-h_sc-h_term", 2);
    model.geom("part1").run("blk2");
    model.geom("part1").create("arr4", "Array");
    model.geom("part1").feature("arr4").selection("input").set("blk2");
    model.geom("part1").feature("arr4").set("fullsize", new String[]{"floor(nx_batt/2)", "1", "1"});
    model.geom("part1").feature("arr4").setIndex("fullsize", "ny_batt", 1);
    model.geom("part1").feature("arr4").set("displ", new String[]{"d_batt*2", "d_batt", "0"});
    model.geom("part1").feature("arr4").set("contributeto", "csel3");
    model.geom("part1").run("arr4");
    model.geom("part1").create("if1", "If");
    model.geom("part1").feature().createAfter("endif1", "EndIf", "if1");
    model.geom("part1").feature("if1").label("If 1 (nx_batt > 2)");
    model.geom("part1").feature("if1").set("condition", "nx_batt > 2");
    model.geom("part1").create("blk3", "Block");
    model.geom("part1").feature("blk3").set("size", new String[]{"d_batt+w_pc", "1", "1"});
    model.geom("part1").feature("blk3").setIndex("size", "d_sc", 1);
    model.geom("part1").feature("blk3").setIndex("size", "h_sc", 2);
    model.geom("part1").feature("blk3").set("pos", new String[]{"d_batt-w_pc/2", "0", "0"});
    model.geom("part1").feature("blk3").setIndex("pos", "-d_sc/2", 1);
    model.geom("part1").feature("blk3").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part1").run("blk3");
    model.geom("part1").create("arr5", "Array");
    model.geom("part1").feature("arr5").selection("input").set("blk3");
    model.geom("part1").feature("arr5").set("fullsize", new String[]{"floor(nx_batt/2)", "1", "1"});
    model.geom("part1").feature("arr5").setIndex("fullsize", "ny_batt", 1);
    model.geom("part1").feature("arr5").set("displ", new String[]{"d_batt*2", "d_batt", "0"});
    model.geom("part1").feature("arr5").set("contributeto", "csel3");
    model.geom("part1").feature().createAfter("if2", "If", "endif1");
    model.geom("part1").feature("if2").label("If 2 (ny_batt >1)");
    model.geom("part1").feature("if2").set("condition", "ny_batt >1");
    model.geom("part1").feature().createAfter("endif2", "EndIf", "if2");
    model.geom("part1").run("arr5");
    model.geom("part1").create("blk4", "Block");
    model.geom("part1").feature().move("blk4", 13);
    model.geom("part1").feature().move("blk4", 14);
    model.geom("part1").feature("blk4").set("size", new String[]{"w_pc", "(d_batt)*(ny_batt-1)+d_sc", "1"});
    model.geom("part1").feature("blk4").setIndex("size", "h_sc", 2);
    model.geom("part1").feature("blk4").set("pos", new String[]{"-w_pc/2", "-d_sc/2", "0"});
    model.geom("part1").feature("blk4").setIndex("pos", "-h_sc-h_term", 2);
    model.geom("part1").run("blk4");
    model.geom("part1").create("arr6", "Array");
    model.geom("part1").feature("arr6").selection("input").set("blk4");
    model.geom("part1").feature("arr6").set("fullsize", new String[]{"nx_batt", "1", "2"});
    model.geom("part1").feature("arr6").set("displ", new String[]{"d_batt", "0", "h_batt+h_term*2+h_sc"});
    model.geom("part1").feature("arr6").set("contributeto", "csel3");
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").label("\u96f6\u4ef6 2\uff1a\u504f\u5305\u88c5");
    model.geom("part2").create("cyl1", "Cylinder");
    model.geom("part2").feature("cyl1").set("r", "r_batt");
    model.geom("part2").feature("cyl1").set("h", "h_batt");
    model.geom("part2").run("cyl1");
    model.geom("part2").create("arr1", "Array");
    model.geom("part2").feature("arr1").selection("input").set("cyl1");
    model.geom("part2").feature("arr1").set("fullsize", new String[]{"nx_batt", "ceil(ny_batt/2)", "1"});
    model.geom("part2").feature("arr1").set("displ", new String[]{"d_batt", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").selection().create("csel1", "CumulativeSelection");
    model.geom("part2").selection("csel1").label("\u7535\u6c60");
    model.geom("part2").feature("arr1").set("contributeto", "csel1");
    model.geom("part2").run("arr1");
    model.geom("part2").create("cyl2", "Cylinder");
    model.geom("part2").feature("cyl2").set("r", "r_batt");
    model.geom("part2").feature("cyl2").set("h", "h_batt");
    model.geom("part2").feature("cyl2").set("pos", new String[]{"r_batt", "r_batt*tan(60[deg])", "0"});
    model.geom("part2").run("cyl2");
    model.geom("part2").create("arr2", "Array");
    model.geom("part2").feature("arr2").selection("input").set("cyl2");
    model.geom("part2").feature("arr2").set("fullsize", new String[]{"nx_batt", "floor(ny_batt/2)", "1"});
    model.geom("part2").feature("arr2").set("displ", new String[]{"d_batt", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr2").set("contributeto", "csel1");
    model.geom("part2").run("arr2");
    model.geom("part2").create("cyl3", "Cylinder");
    model.geom("part2").feature("cyl3").set("r", "r_term");
    model.geom("part2").feature("cyl3").set("h", "h_term");
    model.geom("part2").feature("cyl3").set("pos", new String[]{"0", "0", "-h_term"});
    model.geom("part2").run("cyl3");
    model.geom("part2").create("arr3", "Array");
    model.geom("part2").feature("arr3").selection("input").set("cyl3");
    model.geom("part2").feature("arr3").set("fullsize", new String[]{"nx_batt", "ceil(ny_batt/2)", "1"});
    model.geom("part2").feature("arr3").setIndex("fullsize", 2, 2);
    model.geom("part2").feature("arr3").set("displ", new String[]{"d_batt", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr3").setIndex("displ", "h_batt+h_term", 2);
    model.geom("part2").selection().create("csel2", "CumulativeSelection");
    model.geom("part2").selection("csel2").label("\u7ec8\u7aef");
    model.geom("part2").feature("arr3").set("contributeto", "csel2");
    model.geom("part2").run("arr3");
    model.geom("part2").create("cyl4", "Cylinder");
    model.geom("part2").feature("cyl4").set("r", "r_term");
    model.geom("part2").feature("cyl4").set("h", "h_term");
    model.geom("part2").feature("cyl4").set("pos", new String[]{"r_batt", "r_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("cyl4").setIndex("pos", "-h_term", 2);
    model.geom("part2").run("cyl4");
    model.geom("part2").create("arr4", "Array");
    model.geom("part2").feature("arr4").selection("input").set("cyl4");
    model.geom("part2").feature("arr4").set("fullsize", new String[]{"nx_batt", "floor(ny_batt/2)", "1"});
    model.geom("part2").feature("arr4").setIndex("fullsize", 2, 2);
    model.geom("part2").feature("arr4").set("displ", new String[]{"d_batt", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr4").setIndex("displ", "h_batt+h_term", 2);
    model.geom("part2").feature("arr4").set("contributeto", "csel2");
    model.geom("part2").run("arr4");
    model.geom("part2").create("blk1", "Block");
    model.geom("part2").feature("blk1").set("size", new String[]{"d_batt", "d_sc", "h_sc"});
    model.geom("part2").feature("blk1").set("pos", new String[]{"-d_batt+w_pc/2", "0", "0"});
    model.geom("part2").feature("blk1").setIndex("pos", "-d_sc/2", 1);
    model.geom("part2").feature("blk1").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part2").run("blk1");
    model.geom("part2").create("arr5", "Array");
    model.geom("part2").feature("arr5").selection("input").set("blk1");
    model.geom("part2").feature("arr5").set("type", "linear");
    model.geom("part2").feature("arr5").set("linearsize", "ceil(ny_batt/2)");
    model.geom("part2").feature("arr5").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").selection().create("csel3", "CumulativeSelection");
    model.geom("part2").selection("csel3").label("\u6bcd\u7ebf");
    model.geom("part2").feature("arr5").set("contributeto", "csel3");
    model.geom("part2").run("arr5");
    model.geom("part2").create("blk2", "Block");
    model.geom("part2").feature("blk2").set("size", new String[]{"d_batt+r_batt", "1", "1"});
    model.geom("part2").feature("blk2").setIndex("size", "d_sc", 1);
    model.geom("part2").feature("blk2").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk2").set("pos", new String[]{"-d_batt+w_pc/2", "0", "0"});
    model.geom("part2").feature("blk2").setIndex("pos", "-d_sc/2+r_batt*tan(60[deg])", 1);
    model.geom("part2").feature("blk2").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part2").run("blk2");
    model.geom("part2").create("arr6", "Array");
    model.geom("part2").feature("arr6").selection("input").set("blk2");
    model.geom("part2").feature("arr6").set("type", "linear");
    model.geom("part2").feature("arr6").set("linearsize", "floor(ny_batt/2)");
    model.geom("part2").feature("arr6").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr6").set("contributeto", "csel3");
    model.geom("part2").run("arr6");
    model.geom("part2").create("if1", "If");
    model.geom("part2").feature().createAfter("endif1", "EndIf", "if1");
    model.geom("part2").feature("if1").label("If 1 (isEven == 1)");
    model.geom("part2").feature("if1").set("condition", "isEven == 1");
    model.geom("part2").create("blk3", "Block");
    model.geom("part2").feature("blk3").set("size", new String[]{"d_batt+r_batt", "1", "1"});
    model.geom("part2").feature("blk3").setIndex("size", "d_sc", 1);
    model.geom("part2").feature("blk3").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk3").set("pos", new String[]{"(nx_batt-1)*d_batt-w_pc/2", "0", "0"});
    model.geom("part2").feature("blk3").setIndex("pos", "-d_sc/2", 1);
    model.geom("part2").feature("blk3").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part2").run("blk3");
    model.geom("part2").create("arr7", "Array");
    model.geom("part2").feature("arr7").selection("input").set("blk3");
    model.geom("part2").feature("arr7").set("type", "linear");
    model.geom("part2").feature("arr7").set("linearsize", "ceil(ny_batt/2)");
    model.geom("part2").feature("arr7").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr7").set("contributeto", "csel3");
    model.geom("part2").run("arr7");
    model.geom("part2").create("blk4", "Block");
    model.geom("part2").feature("blk4").set("size", new String[]{"d_batt", "d_sc", "h_sc"});
    model.geom("part2").feature("blk4").set("pos", new String[]{"(nx_batt-1)*d_batt-w_pc/2+r_batt", "0", "0"});
    model.geom("part2").feature("blk4").setIndex("pos", "-d_sc/2+r_batt*tan(60[deg])", 1);
    model.geom("part2").feature("blk4").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part2").run("blk4");
    model.geom("part2").create("arr8", "Array");
    model.geom("part2").feature("arr8").selection("input").set("blk4");
    model.geom("part2").feature("arr8").set("type", "linear");
    model.geom("part2").feature("arr8").set("linearsize", "floor(ny_batt/2)");
    model.geom("part2").feature("arr8").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr8").set("contributeto", "csel3");
    model.geom("part2").feature().createAfter("else1", "Else", "arr8");
    model.geom("part2").run("arr8");
    model.geom("part2").create("blk5", "Block");
    model.geom("part2").feature().move("blk5", 18);
    model.geom("part2").feature("blk5").set("size", new String[]{"d_batt+r_batt", "1", "1"});
    model.geom("part2").feature("blk5").setIndex("size", "d_sc", 1);
    model.geom("part2").feature("blk5").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk5").set("pos", new String[]{"(nx_batt-1)*d_batt-w_pc/2", "0", "0"});
    model.geom("part2").feature("blk5").setIndex("pos", "-d_sc/2", 1);
    model.geom("part2").feature("blk5").setIndex("pos", "-h_term-h_sc", 2);
    model.geom("part2").run("blk5");
    model.geom("part2").create("arr9", "Array");
    model.geom("part2").feature("arr9").selection("input").set("blk5");
    model.geom("part2").feature("arr9").set("type", "linear");
    model.geom("part2").feature("arr9").set("linearsize", "ceil(ny_batt/2)");
    model.geom("part2").feature("arr9").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr9").set("contributeto", "csel3");
    model.geom("part2").feature().duplicate("blk6", "blk5");
    model.geom("part2").feature("blk6").set("size", new String[]{"d_batt", "d_sc", "h_sc"});
    model.geom("part2").feature("blk6").setIndex("pos", "(nx_batt-1)*d_batt-w_pc/2+r_batt", 0);
    model.geom("part2").feature("blk6").setIndex("pos", "-d_sc/2+r_batt*tan(60[deg])", 1);
    model.geom("part2").feature().duplicate("arr10", "arr9");
    model.geom("part2").runPre("arr10");
    model.geom("part2").feature("arr10").selection("input").set("blk6");
    model.geom("part2").feature("arr10").set("linearsize", "floor(ny_batt/2)");
    model.geom("part2").create("blk7", "Block");
    model.geom("part2").feature().move("blk7", 23);
    model.geom("part2").feature("blk7").set("size", new String[]{"d_batt+w_pc", "1", "1"});
    model.geom("part2").feature("blk7").setIndex("size", "d_sc", 1);
    model.geom("part2").feature("blk7").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk7").set("pos", new String[]{"-w_pc/2", "-d_sc/2", "0"});
    model.geom("part2").feature("blk7").setIndex("pos", "-h_sc-h_term", 2);
    model.geom("part2").run("blk7");
    model.geom("part2").create("if2", "If");
    model.geom("part2").feature().createAfter("endif2", "EndIf", "if2");
    model.geom("part2").feature("if2").label("If 2 (ny_batt == 1)");
    model.geom("part2").feature("if2").set("condition", "ny_batt ==1");
    model.geom("part2").feature().duplicate("arr11", "arr10");
    model.geom("part2").runPre("arr11");
    model.geom("part2").feature("arr11").selection("input").set("blk7");
    model.geom("part2").feature("arr11").set("linearsize", "floor(nx_batt/2)");
    model.geom("part2").feature("arr11").setIndex("displ", "2*d_batt", 0);
    model.geom("part2").feature("arr11").set("displ", new String[]{"2*d_batt", "0", "0"});
    model.geom("part2").feature().createAfter("else2", "Else", "arr11");
    model.geom("part2").run("else2");
    model.geom("part2").feature().duplicate("arr12", "arr11");
    model.geom("part2").feature("arr12").set("type", "three-dimensional");
    model.geom("part2").feature("arr12").set("fullsize", new String[]{"floor(nx_batt/2)", "1", "1"});
    model.geom("part2").feature("arr12").setIndex("fullsize", "ceil(ny_batt/2)", 1);
    model.geom("part2").feature("arr12").set("displ", new String[]{"2*d_batt", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").run("arr12");
    model.geom("part2").create("blk8", "Block");
    model.geom("part2").feature().move("blk8", 29);
    model.geom("part2").feature("blk8").set("size", new String[]{"d_batt+w_pc", "1", "1"});
    model.geom("part2").feature("blk8").setIndex("size", "d_sc", 1);
    model.geom("part2").feature("blk8").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk8").set("pos", new String[]{"-w_pc/2+r_batt", "0", "0"});
    model.geom("part2").feature("blk8").setIndex("pos", "-d_sc/2+r_batt*tan(60[deg])", 1);
    model.geom("part2").feature("blk8").setIndex("pos", "-h_sc-h_term", 2);
    model.geom("part2").run("blk8");
    model.geom("part2").create("arr13", "Array");
    model.geom("part2").feature("arr13").selection("input").set("blk8");
    model.geom("part2").feature("arr13").set("fullsize", new String[]{"floor(nx_batt/2)", "1", "1"});
    model.geom("part2").feature("arr13").setIndex("fullsize", "floor(ny_batt/2)", 1);
    model.geom("part2").feature("arr13").set("displ", new String[]{"2*d_batt", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr13").set("contributeto", "csel3");
    model.geom("part2").run("arr13");
    model.geom("part2").create("if3", "If");
    model.geom("part2").feature().createAfter("endif3", "EndIf", "if3");
    model.geom("part2").feature("if3").label("If 3 (nx_batt>2)");
    model.geom("part2").feature("if3").set("condition", "nx_batt>2");
    model.geom("part2").feature().duplicate("blk9", "blk8");
    model.geom("part2").feature("blk9").setIndex("pos", "-w_pc/2+d_batt", 0);
    model.geom("part2").feature("blk9").setIndex("pos", "-d_sc/2", 1);
    model.geom("part2").feature("blk9").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part2").feature().duplicate("arr14", "arr13");
    model.geom("part2").runPre("arr14");
    model.geom("part2").feature("arr14").selection("input").set("blk9");
    model.geom("part2").feature("arr14").setIndex("fullsize", "floor((nx_batt-1)/2)", 0);
    model.geom("part2").feature("arr14").setIndex("fullsize", "ceil(ny_batt/2)", 1);
    model.geom("part2").feature().duplicate("if4", "if3");
    model.geom("part2").feature().duplicate("blk10", "blk9");
    model.geom("part2").feature().duplicate("arr15", "arr14");
    model.geom("part2").feature().duplicate("endif4", "endif3");
    model.geom("part2").feature().move("arr14", 33);
    model.geom("part2").feature("if4").label("If 4 (ny_batt>1)");
    model.geom("part2").feature("if4").set("condition", "ny_batt>1");
    model.geom("part2").feature("blk10").setIndex("pos", "-w_pc/2+d_batt+r_batt", 0);
    model.geom("part2").feature("blk10").setIndex("pos", "-d_sc/2+r_batt*tan(60[deg])", 1);
    model.geom("part2").runPre("arr15");
    model.geom("part2").feature("arr15").selection("input").init();
    model.geom("part2").feature("arr15").selection("input").set("blk10");
    model.geom("part2").feature("arr15").setIndex("fullsize", "floor((ny_batt)/2)", 1);
    model.geom("part2").create("if5", "If");
    model.geom("part2").feature().createAfter("endif5", "EndIf", "if5");
    model.geom("part2").feature().move("endif5", 40);
    model.geom("part2").feature().move("if5", 39);
    model.geom("part2").feature("if5").label("If 5 (ny_batt > 1)");
    model.geom("part2").feature("if5").set("condition", "ny_batt > 1");
    model.geom("part2").run("if5");
    model.geom("part2").create("blk11", "Block");
    model.geom("part2").feature("blk11")
         .set("size", new String[]{"w_pc", "(r_batt*tan(60[deg])-d_sc)/sin(atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad])+w_pc*tan(90[deg]-beta)", "1"});
    model.geom("part2").feature("blk11").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk11").set("pos", new String[]{"-w_pc/2", "d_sc/2", "0"});
    model.geom("part2").feature("blk11").setIndex("pos", "-h_sc-h_term", 2);
    model.geom("part2").feature("blk11").set("rot", "-90+atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad]");
    model.geom("part2").run("blk11");
    model.geom("part2").create("arr16", "Array");
    model.geom("part2").feature("arr16").selection("input").set("blk11");
    model.geom("part2").feature("arr16").set("type", "linear");
    model.geom("part2").feature("arr16").set("linearsize", "ceil(nx_batt/2)");
    model.geom("part2").feature("arr16").set("displ", new String[]{"d_batt*2", "0", "0"});
    model.geom("part2").selection().create("csel4", "CumulativeSelection");
    model.geom("part2").selection("csel4").label("\u6bcd\u7ebf\u5e76\u8054_1");
    model.geom("part2").feature("arr16").set("contributeto", "csel4");
    model.geom("part2").feature().duplicate("blk12", "blk11");
    model.geom("part2").feature().duplicate("arr17", "arr16");
    model.geom("part2").feature("blk12")
         .setIndex("pos", "d_batt+w_pc/2-w_pc/cos(-90[deg]+atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad])", 0);
    model.geom("part2").runPre("arr17");
    model.geom("part2").feature("arr17").selection("input").set("blk12");
    model.geom("part2").feature("arr17").set("linearsize", "floor(nx_batt/2)");
    model.geom("part2").feature().duplicate("if6", "if5");
    model.geom("part2").feature().duplicate("blk13", "blk11");
    model.geom("part2").feature().duplicate("arr18", "arr16");
    model.geom("part2").feature().duplicate("blk14", "blk12");
    model.geom("part2").feature().duplicate("arr19", "arr17");
    model.geom("part2").feature().duplicate("endif6", "endif5");
    model.geom("part2").feature().move("endif6", 50);
    model.geom("part2").feature().move("arr19", 49);
    model.geom("part2").feature().move("blk14", 48);
    model.geom("part2").feature().move("arr18", 47);
    model.geom("part2").feature().move("blk13", 46);
    model.geom("part2").feature().move("if6", 45);
    model.geom("part2").feature("if6").label("If 6 (ny_batt > 2)");
    model.geom("part2").feature("if6").set("condition", "ny_batt > 2");
    model.geom("part2").feature("blk13").setIndex("pos", "-w_pc/2+r_batt+(w_pc/sin(beta))-sin(beta)*w_pc", 0);
    model.geom("part2").feature("blk13").setIndex("pos", "r_batt*tan(60[deg])+d_sc/2-cos(beta)*w_pc", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.geom("part2").feature("blk13").set("rot", "90-beta");
    model.geom("part2").runPre("arr18");
    model.geom("part2").feature("arr18").selection("input").set("blk13");
    model.geom("part2").selection().create("csel5", "CumulativeSelection");
    model.geom("part2").selection("csel5").label("\u6bcd\u7ebf\u5e76\u8054_2");
    model.geom("part2").feature("arr18").set("contributeto", "csel5");
    model.geom("part2").feature("blk14")
         .setIndex("pos", "-w_pc/2+r_batt+(w_pc/sin(beta))-sin(beta)*w_pc+d_batt+w_pc/2-w_pc/cos(-90[deg]+atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad])+w_pc/2", 0);
    model.geom("part2").feature("blk14").setIndex("pos", "r_batt*tan(60[deg])+d_sc/2-cos(beta)*w_pc", 1);
    model.geom("part2").feature("blk14").set("rot", "90-beta");
    model.geom("part2").runPre("arr19");
    model.geom("part2").feature("arr19").selection("input").init();
    model.geom("part2").feature("arr19").selection("input").set("blk14");
    model.geom("part2").feature("arr19").set("contributeto", "csel5");
    model.geom("part2").create("if7", "If");
    model.geom("part2").feature().createAfter("endif7", "EndIf", "if7");
    model.geom("part2").feature().move("endif7", 52);
    model.geom("part2").feature().move("if7", 51);
    model.geom("part2").feature("if7").label("If 7 (ny_batt > 3)");
    model.geom("part2").feature("if7").set("condition", "ny_batt > 3");
    model.geom("part2").run("if7");
    model.geom("part2").create("arr20", "Array");
    model.geom("part2").feature("arr20").selection("input").named("csel4");
    model.geom("part2").feature("arr20").set("type", "linear");
    model.geom("part2").feature("arr20").set("linearsize", "floor(ny_batt/2)");
    model.geom("part2").feature("arr20").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr20").set("contributeto", "csel4");
    model.geom("part2").run("arr20");
    model.geom("part2").create("if8", "If");
    model.geom("part2").feature().createAfter("endif8", "EndIf", "if8");
    model.geom("part2").feature().move("endif8", 55);
    model.geom("part2").feature().move("if8", 54);
    model.geom("part2").feature("if8").label("If 8 (ny_batt > 4)");
    model.geom("part2").feature("if8").set("condition", "ny_batt > 4");
    model.geom("part2").run("if8");
    model.geom("part2").create("arr21", "Array");
    model.geom("part2").feature("arr21").selection("input").named("csel5");
    model.geom("part2").feature("arr21").set("type", "linear");
    model.geom("part2").feature("arr21").set("linearsize", "ceil((ny_batt-2)/2)");
    model.geom("part2").feature("arr21").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr21").set("contributeto", "csel5");
    model.geom("part2").run("arr21");
    model.geom("part2").create("copy1", "Copy");
    model.geom("part2").feature().move("copy1", 57);
    model.geom("part2").runPre("copy1");
    model.geom("part2").feature("copy1").selection("input").named("csel3");
    model.geom("part2").selection().create("csel6", "CumulativeSelection");
    model.geom("part2").selection("csel6").label("\u6bcd\u7ebf\u526f\u672c_\u6e29\u5ea6");
    model.geom("part2").feature("copy1").set("contributeto", "csel6");
    model.geom("part2").create("unisel1", "UnionSelection");
    model.geom("part2").feature().move("unisel1", 58);
    model.geom("part2").feature("unisel1").set("entitydim", -1);
    model.geom("part2").runPre("unisel1");
    model.geom("part2").feature("unisel1").set("input", new String[]{"csel4", "csel5"});
    model.geom("part2").create("dif1", "Difference");
    model.geom("part2").feature().move("dif1", 59);
    model.geom("part2").runPre("dif1");
    model.geom("part2").feature("dif1").selection("input").named("unisel1");
    model.geom("part2").feature("dif1").selection("input2").named("csel6");
    model.geom("part2").feature("dif1").set("contributeto", "csel3");
    model.geom("part2").create("if9", "If");
    model.geom("part2").feature().createAfter("endif9", "EndIf", "if9");
    model.geom("part2").feature().move("endif9", 61);
    model.geom("part2").feature().move("if9", 60);
    model.geom("part2").feature("if9").label("If (ny_batt > 1)");
    model.geom("part2").feature("if9").set("condition", "ny_batt > 1");
    model.geom("part2").run("if9");
    model.geom("part2").create("blk15", "Block");
    model.geom("part2").feature("blk15")
         .set("size", new String[]{"w_pc", "(r_batt*tan(60[deg])-d_sc)/sin(atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad])+w_pc*tan(90[deg]-beta)", "1"});
    model.geom("part2").feature("blk15").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk15").set("pos", new String[]{"w_pc/2-(w_pc/sin(beta))", "0", "0"});
    model.geom("part2").feature("blk15").setIndex("pos", "d_sc/2", 1);
    model.geom("part2").feature("blk15").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part2").feature("blk15").set("rot", "-90+atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad]");
    model.geom("part2").selection().create("csel7", "CumulativeSelection");
    model.geom("part2").selection("csel7").label("\u6bcd\u7ebf\u5e76\u8054_\u9876\u90e8");
    model.geom("part2").feature("blk15").set("contributeto", "csel7");
    model.geom("part2").run("blk15");
    model.geom("part2").create("arr22", "Array");
    model.geom("part2").feature("arr22").selection("input").named("csel7");
    model.geom("part2").feature("arr22").set("type", "linear");
    model.geom("part2").feature("arr22").set("linearsize", "floor(ny_batt/2)");
    model.geom("part2").feature("arr22").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr22").set("contributeto", "csel7");
    model.geom("part2").feature().duplicate("if10", "if9");
    model.geom("part2").feature().duplicate("blk16", "blk15");
    model.geom("part2").feature().duplicate("endif10", "endif9");
    model.geom("part2").feature().move("endif10", 66);
    model.geom("part2").feature().move("blk16", 65);
    model.geom("part2").feature().move("if10", 64);
    model.geom("part2").feature("if10").label("If 10(ny_batt > 2)");
    model.geom("part2").feature("if10").set("condition", "ny_batt > 2");
    model.geom("part2").feature("blk16")
         .setIndex("pos", "r_batt+(w_pc/sin(beta))-sin(beta)*w_pc+w_pc/2-(w_pc/sin(beta))", 0);
    model.geom("part2").feature("blk16").setIndex("pos", "r_batt*tan(60[deg])+d_sc/2-cos(beta)*w_pc", 1);
    model.geom("part2").feature("blk16").set("rot", "90-beta");
    model.geom("part2").run("endif10");
    model.geom("part2").create("if11", "If");
    model.geom("part2").feature().createAfter("endif11", "EndIf", "if11");
    model.geom("part2").feature("if11").label("If 11 (ny_batt > 4)");
    model.geom("part2").feature("if11").set("condition", "ny_batt > 4");
    model.geom("part2").run("if11");
    model.geom("part2").create("arr23", "Array");
    model.geom("part2").feature("arr23").selection("input").set("blk16");
    model.geom("part2").feature("arr23").set("type", "linear");
    model.geom("part2").feature("arr23").set("linearsize", "ceil((ny_batt-2)/2)");
    model.geom("part2").feature("arr23").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr23").set("contributeto", "csel7");
    model.geom("part2").feature().duplicate("arr24", "arr23");
    model.geom("part2").feature().move("arr24", 70);
    model.geom("part2").runPre("arr24");
    model.geom("part2").feature("arr24").selection("input").named("csel7");
    model.geom("part2").feature("arr24").set("linearsize", "ceil((nx_batt)/2)");
    model.geom("part2").feature("arr24").setIndex("displ", "d_batt*2", 0);
    model.geom("part2").feature("arr24").set("displ", new String[]{"d_batt*2", "0", "0"});
    model.geom("part2").create("if12", "If");
    model.geom("part2").feature().createAfter("endif12", "EndIf", "if12");
    model.geom("part2").feature().move("arr24", 70);
    model.geom("part2").feature("if12").label("If 12 (ny_batt >1)");
    model.geom("part2").feature("if12").set("condition", "ny_batt >1");
    model.geom("part2").run("if12");
    model.geom("part2").create("if13", "If");
    model.geom("part2").feature().createAfter("endif13", "EndIf", "if13");
    model.geom("part2").feature("if13").label("If 13 (nx_batt > 1)");
    model.geom("part2").feature("if13").set("condition", "nx_batt > 1");
    model.geom("part2").run("if13");
    model.geom("part2").create("blk17", "Block");
    model.geom("part2").feature("blk17")
         .set("size", new String[]{"w_pc", "(r_batt*tan(60[deg])-d_sc)/sin(atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad])+w_pc*tan(90[deg]-beta)", "1"});
    model.geom("part2").feature("blk17").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk17")
         .set("pos", new String[]{"d_batt+w_pc/2-w_pc/cos(-90[deg]+atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad])+(w_pc/sin(beta))-w_pc", "0", "0"});
    model.geom("part2").feature("blk17").setIndex("pos", "d_sc/2", 1);
    model.geom("part2").feature("blk17").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part2").feature("blk17").set("rot", "-90+atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad]");
    model.geom("part2").selection().create("csel8", "CumulativeSelection");
    model.geom("part2").selection("csel8").label("\u6bcd\u7ebf\u5e76\u8054_\u9876\u90e8_2");
    model.geom("part2").feature("blk17").set("contributeto", "csel8");
    model.geom("part2").run("blk17");
    model.geom("part2").create("if14", "If");
    model.geom("part2").feature().createAfter("endif14", "EndIf", "if14");
    model.geom("part2").feature("if14").label("If 14 (ny_batt > 3)");
    model.geom("part2").feature("if14").set("condition", "ny_batt > 3");
    model.geom("part2").run("if14");
    model.geom("part2").create("arr25", "Array");
    model.geom("part2").feature("arr25").selection("input").set("blk17");
    model.geom("part2").feature("arr25").set("type", "linear");
    model.geom("part2").feature("arr25").set("linearsize", "floor(ny_batt/2)");
    model.geom("part2").feature("arr25").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr25").set("contributeto", "csel8");
    model.geom("part2").run("arr25");
    model.geom("part2").create("if15", "If");
    model.geom("part2").feature().createAfter("endif15", "EndIf", "if15");
    model.geom("part2").feature().move("endif15", 78);
    model.geom("part2").feature().move("if15", 77);
    model.geom("part2").feature("if15").label("If 15 (ny_batt > 2)");
    model.geom("part2").feature("if15").set("condition", "ny_batt > 2");
    model.geom("part2").run("if15");
    model.geom("part2").create("blk18", "Block");
    model.geom("part2").feature("blk18")
         .set("size", new String[]{"w_pc", "(r_batt*tan(60[deg])-d_sc)/sin(atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad])+w_pc*tan(90[deg]-beta)", "1"});
    model.geom("part2").feature("blk18").setIndex("size", "h_sc", 2);
    model.geom("part2").feature("blk18")
         .set("pos", new String[]{"r_batt+(w_pc/sin(beta))-sin(beta)*w_pc+d_batt-w_pc/cos(-90[deg]+atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad])-w_pc/2+(w_pc/sin(beta))", "0", "0"});
    model.geom("part2").feature("blk18").setIndex("pos", "r_batt*tan(60[deg])+d_sc/2-cos(beta)*w_pc", 1);
    model.geom("part2").feature("blk18").setIndex("pos", "h_batt+h_term", 2);
    model.geom("part2").feature("blk18").set("rot", "90[deg]-beta");
    model.geom("part2").feature("blk18").set("contributeto", "csel8");
    model.geom("part2").feature().createAfter("if16", "If", "endif15");
    model.geom("part2").feature("if16").label("If 16 (ny_batt > 4)");
    model.geom("part2").feature("if16").set("condition", "ny_batt > 4");
    model.geom("part2").feature().createAfter("endif16", "EndIf", "if16");
    model.geom("part2").run("blk18");
    model.geom("part2").create("arr26", "Array");
    model.geom("part2").feature().move("arr26", 81);
    model.geom("part2").runPre("arr26");
    model.geom("part2").feature("arr26").selection("input").set("blk18");
    model.geom("part2").feature("arr26").set("type", "linear");
    model.geom("part2").feature("arr26").set("linearsize", "ceil((ny_batt-2)/2)");
    model.geom("part2").feature("arr26").set("displ", new String[]{"0", "d_batt*tan(60[deg])", "0"});
    model.geom("part2").feature("arr26").set("contributeto", "csel8");
    model.geom("part2").run("arr26");
    model.geom("part2").create("if17", "If");
    model.geom("part2").feature().createAfter("endif17", "EndIf", "if17");
    model.geom("part2").feature().move("endif17", 85);
    model.geom("part2").feature().move("if17", 84);
    model.geom("part2").feature("if17").label("If 17 (nx_batt >3)");
    model.geom("part2").feature("if17").set("condition", "nx_batt > 3");
    model.geom("part2").run("if17");
    model.geom("part2").create("arr27", "Array");
    model.geom("part2").feature("arr27").set("type", "linear");
    model.geom("part2").feature("arr27").selection("input").named("csel8");
    model.geom("part2").feature("arr27").set("linearsize", "floor((nx_batt)/2)");
    model.geom("part2").feature("arr27").set("displ", new String[]{"d_batt*2", "0", "0"});
    model.geom("part2").feature("arr27").set("contributeto", "csel8");
    model.geom("part2").run("arr27");
    model.geom("part2").create("copy2", "Copy");
    model.geom("part2").feature().move("copy2", 87);
    model.geom("part2").feature().move("copy2", 88);
    model.geom("part2").runPre("copy2");
    model.geom("part2").feature("copy2").selection("input").named("csel3");
    model.geom("part2").feature("copy2").set("contributeto", "csel6");
    model.geom("part2").create("unisel2", "UnionSelection");
    model.geom("part2").feature().move("unisel2", 89);
    model.geom("part2").feature("unisel2").set("entitydim", -1);
    model.geom("part2").runPre("unisel2");
    model.geom("part2").feature("unisel2").set("input", new String[]{"csel7", "csel8"});
    model.geom("part2").feature("unisel2").set("contributeto", "csel3");
    model.geom("part2").run("unisel2");
    model.geom("part2").create("dif2", "Difference");
    model.geom("part2").feature("dif2").selection("input").named("unisel2");
    model.geom("part2").feature("dif2").selection("input2").named("csel6");
    model.geom("part2").run("dif2");
    model.geom().create("part3", "Part", 3);
    model.geom("part3").geomRep("comsol");
    model.geom("part3").label("\u96f6\u4ef6 3\uff1a\u70ed\u6536\u7f29\u76f4\u5305\u88c5");
    model.geom("part3").create("cyl1", "Cylinder");
    model.geom("part3").feature("cyl1").set("r", "r_batt");
    model.geom("part3").feature("cyl1").set("h", "h_batt+2*(h_term+h_sc)");
    model.geom("part3").feature("cyl1").set("pos", new String[]{"0", "0", "-h_term-h_sc"});
    model.geom("part3").feature("cyl1").setIndex("layer", "h_term+h_sc", 0);
    model.geom("part3").feature("cyl1").set("layerside", false);
    model.geom("part3").feature("cyl1").set("layerbottom", true);
    model.geom("part3").feature("cyl1").set("layertop", true);
    model.geom("part3").run("cyl1");
    model.geom("part3").create("blk1", "Block");
    model.geom("part3").feature("blk1").set("size", new String[]{"d_batt/2", "d_batt/2", "1"});
    model.geom("part3").feature("blk1").setIndex("size", "h_batt+2*(h_term+h_sc)", 2);
    model.geom("part3").feature("blk1").set("pos", new String[]{"-d_batt/2", "-d_batt/2", "0"});
    model.geom("part3").feature("blk1").setIndex("pos", "-h_term-h_sc", 2);
    model.geom("part3").feature("blk1").setIndex("layer", "h_term+h_sc", 0);
    model.geom("part3").feature("blk1").set("layertop", true);
    model.geom("part3").run("blk1");
    model.geom("part3").create("dif1", "Difference");
    model.geom("part3").feature("dif1").selection("input").set("blk1");
    model.geom("part3").feature("dif1").selection("input2").set("cyl1");
    model.geom("part3").feature("dif1").set("intbnd", false);
    model.geom("part3").run("dif1");
    model.geom("part3").create("cyl2", "Cylinder");
    model.geom("part3").feature("cyl2").set("r", "r_batt");
    model.geom("part3").feature("cyl2").set("h", "h_batt+2*(h_term+h_sc)");
    model.geom("part3").feature("cyl2").set("pos", new String[]{"0", "(ny_batt-1)*d_batt", "0"});
    model.geom("part3").feature("cyl2").setIndex("pos", "-h_term-h_sc", 2);
    model.geom("part3").feature("cyl2").setIndex("layer", "h_term+h_sc", 0);
    model.geom("part3").feature("cyl2").set("layerside", false);
    model.geom("part3").feature("cyl2").set("layerbottom", true);
    model.geom("part3").feature("cyl2").set("layertop", true);
    model.geom("part3").run("cyl2");
    model.geom("part3").create("blk2", "Block");
    model.geom("part3").feature("blk2").set("size", new String[]{"d_batt/2", "d_batt/2", "1"});
    model.geom("part3").feature("blk2").setIndex("size", "h_batt+2*(h_term+h_sc)", 2);
    model.geom("part3").feature("blk2").set("pos", new String[]{"-d_batt/2", "(ny_batt-1)*d_batt", "0"});
    model.geom("part3").feature("blk2").setIndex("pos", "-h_term-h_sc", 2);
    model.geom("part3").feature("blk2").setIndex("layer", "h_term+h_sc", 0);
    model.geom("part3").feature("blk2").set("layertop", true);
    model.geom("part3").run("blk2");
    model.geom("part3").create("dif2", "Difference");
    model.geom("part3").feature("dif2").selection("input").set("blk2");
    model.geom("part3").feature("dif2").selection("input2").set("cyl2");
    model.geom("part3").feature("dif2").set("intbnd", false);
    model.geom("part3").run("dif2");
    model.geom("part3").create("cyl3", "Cylinder");
    model.geom("part3").feature("cyl3").set("r", "r_batt");
    model.geom("part3").feature("cyl3").set("h", "h_batt+2*(h_term+h_sc)");
    model.geom("part3").feature("cyl3").set("pos", new String[]{"(nx_batt-1)*d_batt", "0", "0"});
    model.geom("part3").feature("cyl3").setIndex("pos", "-h_term-h_sc", 2);
    model.geom("part3").feature("cyl3").setIndex("layer", "h_term+h_sc", 0);
    model.geom("part3").feature("cyl3").set("layerside", false);
    model.geom("part3").feature("cyl3").set("layerbottom", true);
    model.geom("part3").feature("cyl3").set("layertop", true);
    model.geom("part3").run("cyl3");
    model.geom("part3").create("blk3", "Block");
    model.geom("part3").feature("blk3").set("size", new String[]{"d_batt/2", "d_batt/2", "1"});
    model.geom("part3").feature("blk3").setIndex("size", "h_batt+2*(h_term+h_sc)", 2);
    model.geom("part3").feature("blk3").set("pos", new String[]{"(nx_batt-1)*d_batt", "0", "0"});
    model.geom("part3").feature("blk3").setIndex("pos", "-d_batt/2", 1);
    model.geom("part3").feature("blk3").setIndex("pos", "-h_term-h_sc", 2);
    model.geom("part3").feature("blk3").setIndex("layer", "h_term+h_sc", 0);
    model.geom("part3").feature("blk3").set("layertop", true);
    model.geom("part3").run("blk3");
    model.geom("part3").create("dif3", "Difference");
    model.geom("part3").feature("dif3").selection("input").set("blk3");
    model.geom("part3").feature("dif3").selection("input2").set("cyl3");
    model.geom("part3").feature("dif3").set("intbnd", false);
    model.geom("part3").feature().duplicate("cyl4", "cyl3");
    model.geom("part3").feature().duplicate("blk4", "blk3");
    model.geom("part3").feature().duplicate("dif4", "dif3");
    model.geom("part3").feature("cyl4").setIndex("pos", "(ny_batt-1)*d_batt", 1);
    model.geom("part3").feature("blk4").setIndex("pos", "(ny_batt-1)*d_batt", 1);
    model.geom("part3").runPre("dif4");
    model.geom("part3").feature("dif4").selection("input").set("blk4");
    model.geom("part3").feature("dif4").selection("input2").set("cyl4");
    model.geom("part3").create("blk5", "Block");
    model.geom("part3").feature("blk5").set("size", new String[]{"(nx_batt)*d_batt", "1", "1"});
    model.geom("part3").feature("blk5").setIndex("size", "(ny_batt)*d_batt", 1);
    model.geom("part3").feature("blk5").setIndex("size", "h_batt+2*(h_term+h_sc)", 2);
    model.geom("part3").feature("blk5").set("pos", new String[]{"-r_batt", "-r_batt", "0"});
    model.geom("part3").feature("blk5").setIndex("pos", "-h_term-h_sc", 2);
    model.geom("part3").feature("blk5").setIndex("layer", "h_term+h_sc", 0);
    model.geom("part3").feature("blk5").set("layertop", true);
    model.geom("part3").run("blk5");
    model.geom("part3").feature().move("dif4", 11);
    model.geom("part3").run("blk5");
    model.geom("part3").create("dif5", "Difference");
    model.geom("part3").feature("dif5").selection("input").set("blk5");
    model.geom("part3").feature("dif5").selection("input2").set("dif1", "dif2", "dif3", "dif4");
    model.geom("part3").run("dif5");
    model.geom().create("part4", "Part", 3);
    model.geom("part4").geomRep("comsol");
    model.geom("part4").label("\u96f6\u4ef6 4\uff1a\u70ed\u6536\u7f29\u504f\u5305\u88c5");
    model.geom("part4").create("wp1", "WorkPlane");
    model.geom("part4").feature("wp1").set("unite", true);
    model.geom("part4").feature("wp1").set("quickz", "-h_term-h_sc");
    model.geom("part4").feature("wp1").geom().create("if1", "If");
    model.geom("part4").feature("wp1").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.geom("part4").feature("wp1").geom().feature("if1")
         .label("If 1\uff08y \u65b9\u5411\u4e3a\u5947\u6570\u4e2a\uff09");
    model.geom("part4").feature("wp1").geom().feature("if1")
         .set("condition", "if(floor(ny_batt/2)<ny_batt/2, 0, 1) == 0");
    model.geom("part4").feature("wp1").geom().run("if1");
    model.geom("part4").feature("wp1").geom().create("if2", "If");
    model.geom("part4").feature("wp1").geom().feature().createAfter("endif2", "EndIf", "if2");
    model.geom("part4").feature("wp1").geom().feature("if2").label("If 2 (ny_batt == 1)");
    model.geom("part4").feature("wp1").geom().feature("if2").set("condition", "ny_batt == 1");
    model.geom("part4").feature("wp1").geom().run("if2");
    model.geom("part4").feature("wp1").geom().create("c1", "Circle");
    model.geom("part4").feature("wp1").geom().feature("c1").set("r", "r_batt");
    model.geom("part4").feature("wp1").geom().selection().create("csel1", "CumulativeSelection");
    model.geom("part4").feature("wp1").geom().selection("csel1").label("\u70ed\u6536\u7f29");
    model.geom("part4").feature("wp1").geom().feature("c1").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().run("c1");
    model.geom("part4").feature("wp1").geom().create("if3", "If");
    model.geom("part4").feature("wp1").geom().feature().createAfter("endif3", "EndIf", "if3");
    model.geom("part4").feature("wp1").geom().feature("if3").label("If 3 (nx_batt > 1)");
    model.geom("part4").feature("wp1").geom().feature("if3").set("condition", "nx_batt >1");
    model.geom("part4").feature("wp1").geom().run("if3");
    model.geom("part4").feature("wp1").geom().create("c2", "Circle");
    model.geom("part4").feature("wp1").geom().feature("c2").set("r", "r_batt");
    model.geom("part4").feature("wp1").geom().feature("c2").set("pos", new String[]{"d_batt*(nx_batt-1)", "0"});
    model.geom("part4").feature("wp1").geom().feature("c2").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().run("c2");
    model.geom("part4").feature("wp1").geom().create("r1", "Rectangle");
    model.geom("part4").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"if(nx_batt >1, d_batt*(nx_batt-1),1)", "1"});
    model.geom("part4").feature("wp1").geom().feature("r1").setIndex("size", "d_batt", 1);
    model.geom("part4").feature("wp1").geom().feature("r1").set("pos", new String[]{"0", "-r_batt"});
    model.geom("part4").feature("wp1").geom().feature("r1").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().run("r1");

    model.param().set("ny_batt", "1");
    model.param().descr("ny_batt", "y \u65b9\u5411\u7684\u7535\u6c60\u6570");

    model.geom("part4").feature("wp1").geom().run("r1");
    model.geom("part4").feature("wp1").geom().create("uni1", "Union");
    model.geom("part4").feature("wp1").geom().feature("uni1").selection("input").named("csel1");
    model.geom("part4").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.geom("part4").feature("wp1").geom().feature().createAfter("else1", "Else", "uni1");
    model.geom("part4").feature("wp1").geom().run("uni1");
    model.geom("part4").feature("wp1").geom().create("pol1", "Polygon");
    model.geom("part4").feature("wp1").geom().feature("pol1").set("source", "table");
    model.geom("part4").feature("wp1").geom().feature().move("pol1", 8);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "d_batt*(nx_batt-1)", 0, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "d_batt*(nx_batt-1)+r_batt", 1, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "r_batt*tan(60[deg])", 1, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt+r_batt*cos(30[deg])", 2, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "r_batt*tan(60[deg])-r_batt*sin(30[deg])", 2, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt*cos(30[deg])", 3, 0);
    model.geom("part4").feature("wp1").geom().feature("pol1").setIndex("table", "-r_batt*sin(30[deg])", 3, 1);
    model.geom("part4").feature("wp1").geom().feature("pol1").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().feature().duplicate("pol2", "pol1");
    model.geom("part4").feature("wp1").geom().feature("pol2").setIndex("table", "d_batt*(nx_batt-1)+r_batt", 0, 0);
    model.geom("part4").feature("wp1").geom().feature("pol2")
         .setIndex("table", "r_batt*tan(60[deg])*(ny_batt-2)", 0, 1);
    model.geom("part4").feature("wp1").geom().feature("pol2").setIndex("table", "d_batt*(nx_batt-1)", 1, 0);
    model.geom("part4").feature("wp1").geom().feature("pol2")
         .setIndex("table", "r_batt*tan(60[deg])*(ny_batt-1)", 1, 1);
    model.geom("part4").feature("wp1").geom().feature("pol2")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt*cos(30[deg])", 2, 0);
    model.geom("part4").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(ny_batt-1)*r_batt*tan(60[deg])+r_batt*sin(30[deg])", 2, 1);
    model.geom("part4").feature("wp1").geom().feature("pol2")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt*cos(30[deg])+r_batt", 3, 0);
    model.geom("part4").feature("wp1").geom().feature("pol2")
         .setIndex("table", "r_batt*tan(60[deg])*(ny_batt-2)+r_batt*sin(30[deg])", 3, 1);
    model.geom("part4").feature("wp1").geom().run("pol2");
    model.geom("part4").feature("wp1").geom().create("c3", "Circle");
    model.geom("part4").feature("wp1").geom().feature("c3").set("r", "r_batt");
    model.geom("part4").feature("wp1").geom().feature("c3").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().feature().duplicate("c4", "c3");
    model.geom("part4").feature("wp1").geom().feature("c4").set("pos", new String[]{"d_batt*(nx_batt-1)", "0"});
    model.geom("part4").feature("wp1").geom().feature().duplicate("c5", "c4");
    model.geom("part4").feature("wp1").geom().feature("c5")
         .set("pos", new String[]{"0", "r_batt*tan(60[deg])*(ny_batt-1)"});
    model.geom("part4").feature("wp1").geom().feature().duplicate("c6", "c5");
    model.geom("part4").feature("wp1").geom().feature("c6").setIndex("pos", "d_batt*(nx_batt-1)", 0);
    model.geom("part4").feature("wp1").geom().feature().duplicate("c7", "c6");
    model.geom("part4").feature("wp1").geom().feature("c7").setIndex("pos", "d_batt*(nx_batt-1)+r_batt", 0);
    model.geom("part4").feature("wp1").geom().feature("c7").setIndex("pos", "r_batt*tan(60[deg])*(ny_batt-2)", 1);
    model.geom("part4").feature("wp1").geom().feature().duplicate("c8", "c7");
    model.geom("part4").feature("wp1").geom().feature("c8").setIndex("pos", "r_batt*tan(60[deg])", 1);
    model.geom("part4").feature("wp1").geom().run("c8");
    model.geom("part4").feature("wp1").geom().create("r2", "Rectangle");
    model.geom("part4").feature("wp1").geom().feature("r2").set("size", new String[]{"d_batt*nx_batt", "1"});
    model.geom("part4").feature("wp1").geom().feature("r2")
         .setIndex("size", "if(ny_batt == 1, 1, r_batt*tan(60[deg])*(ny_batt-1))", 1);
    model.geom("part4").feature("wp1").geom().feature("r2").set("pos", new String[]{"-r_batt", "0"});
    model.geom("part4").feature("wp1").geom().feature("r2").set("contributeto", "csel1");

    model.param().set("ny_batt", "4");

    model.geom("part4").feature("wp1").geom().feature().move("endif3", 6);
    model.geom("part4").feature("wp1").geom().run("r2");
    model.geom("part4").feature("wp1").geom().create("if4", "If");
    model.geom("part4").feature("wp1").geom().feature().createAfter("endif4", "EndIf", "if4");
    model.geom("part4").feature("wp1").geom().feature("if4").label("If 4 (nx_batt > 1)");
    model.geom("part4").feature("wp1").geom().feature("if4").set("condition", "nx_batt > 1");
    model.geom("part4").feature("wp1").geom().run("if4");
    model.geom("part4").feature("wp1").geom().create("r3", "Rectangle");
    model.geom("part4").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"if(nx_batt >1, d_batt*(nx_batt-1),1)", "1"});
    model.geom("part4").feature("wp1").geom().feature("r3")
         .setIndex("size", "r_batt*tan(60[deg])*(ny_batt-1)+d_batt", 1);
    model.geom("part4").feature("wp1").geom().feature("r3").set("pos", new String[]{"0", "-r_batt"});
    model.geom("part4").feature("wp1").geom().feature("r3").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().run("r3");
    model.geom("part4").feature("wp1").geom().create("if5", "If");
    model.geom("part4").feature("wp1").geom().feature().createAfter("endif5", "EndIf", "if5");
    model.geom("part4").feature("wp1").geom().feature().move("endif5", 22);
    model.geom("part4").feature("wp1").geom().feature().move("if5", 21);
    model.geom("part4").feature("wp1").geom().feature("if5").label("If 5 (ny_batt > 3)");
    model.geom("part4").feature("wp1").geom().feature("if5").set("condition", "ny_batt > 3");
    model.geom("part4").feature("wp1").geom().run("if5");
    model.geom("part4").feature("wp1").geom().create("r4", "Rectangle");
    model.geom("part4").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"r_batt", "if(ny_batt > 3, r_batt*tan(60[deg])*(ny_batt-3), 1)"});
    model.geom("part4").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"d_batt*(nx_batt-1)+r_batt", "0"});
    model.geom("part4").feature("wp1").geom().feature("r4").setIndex("pos", "r_batt*tan(60[deg])", 1);
    model.geom("part4").feature("wp1").geom().feature("r4").set("contributeto", "csel1");

    model.param().set("nx_batt", "7");
    model.param().descr("nx_batt", "x \u65b9\u5411\u7684\u7535\u6c60\u6570");
    model.param().set("ny_batt", "5");

    model.geom("part4").feature("wp1").geom().run("r4");
    model.geom("part4").feature("wp1").geom().create("uni2", "Union");
    model.geom("part4").feature("wp1").geom().feature("uni2").selection("input").named("csel1");
    model.geom("part4").feature("wp1").geom().feature("uni2").set("intbnd", false);
    model.geom("part4").feature("wp1").geom().feature().move("uni2", 24);
    model.geom("part4").feature("wp1").geom().feature().createAfter("elseif1", "ElseIf", "endif2");
    model.geom("part4").feature("wp1").geom().feature("elseif1")
         .label("Else If 1\uff08y \u65b9\u5411\u4e3a\u5076\u6570\u4e2a\uff09");
    model.geom("part4").feature("wp1").geom().feature("elseif1")
         .set("condition", "if(floor(ny_batt/2)<ny_batt/2, 0, 1) == 1");

    return model;
  }

  public static Model run3(Model model) {
    model.geom("part4").feature("wp1").geom().run("uni2");
    model.geom("part4").feature("wp1").geom().create("pol3", "Polygon");
    model.geom("part4").feature("wp1").geom().feature("pol3").set("source", "table");
    model.geom("part4").feature("wp1").geom().feature().move("pol3", 27);

    model.param().set("nx_batt", "6");
    model.param().set("ny_batt", "4");

    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt*cos(30[deg])", 0, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt*sin(30[deg])", 0, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt-r_batt*cos(30[deg])", 1, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*sin(30[deg])+r_batt*tan(60[deg])", 1, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt", 2, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt*tan(60[deg])+r_batt", 2, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt*(nx_batt*2-1)", 3, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt*tan(60[deg])+r_batt", 3, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*(nx_batt*2)-(r_batt-r_batt*cos(30[deg]))", 4, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*tan(60[deg])-(r_batt-r_batt*sin(30[deg]))", 4, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*(nx_batt*2-2)+r_batt*cos(30[deg])", 5, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt*sin(30[deg])", 5, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt*(nx_batt*2-2)", 6, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt", 6, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", 0, 7, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt", 7, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt", 8, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", 0, 8, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt*cos(30[deg])", 9, 0);
    model.geom("part4").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt*sin(30[deg])", 9, 1);
    model.geom("part4").feature("wp1").geom().feature("pol3").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().run("pol3");
    model.geom("part4").feature("wp1").geom().create("c9", "Circle");
    model.geom("part4").feature("wp1").geom().feature("c9").set("r", "r_batt");
    model.geom("part4").feature("wp1").geom().feature("c9").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().feature().duplicate("c10", "c9");
    model.geom("part4").feature("wp1").geom().feature("c10").set("pos", new String[]{"d_batt*(nx_batt-1)", "0"});
    model.geom("part4").feature("wp1").geom().feature().duplicate("c11", "c10");
    model.geom("part4").feature("wp1").geom().feature("c11")
         .set("pos", new String[]{"r_batt", "r_batt*tan(60[deg])"});
    model.geom("part4").feature("wp1").geom().feature().duplicate("c12", "c11");
    model.geom("part4").feature("wp1").geom().feature("c12").setIndex("pos", "d_batt*(nx_batt-1)+r_batt", 0);
    model.geom("part4").feature("wp1").geom().run("c12");
    model.geom("part4").feature("wp1").geom().create("uni3", "Union");
    model.geom("part4").feature("wp1").geom().feature("uni3").selection("input").named("csel1");
    model.geom("part4").feature("wp1").geom().feature("uni3").set("intbnd", false);
    model.geom("part4").feature("wp1").geom().feature("uni3").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().run("uni3");
    model.geom("part4").feature("wp1").geom().create("if6", "If");
    model.geom("part4").feature("wp1").geom().feature().createAfter("endif6", "EndIf", "if6");
    model.geom("part4").feature("wp1").geom().feature("if6").label("If 6 (ny_batt > 3)");
    model.geom("part4").feature("wp1").geom().feature("if6").set("condition", "ny_batt>3");
    model.geom("part4").feature("wp1").geom().run("if6");
    model.geom("part4").feature("wp1").geom().create("arr1", "Array");
    model.geom("part4").feature("wp1").geom().feature("arr1").selection("input").named("csel1");
    model.geom("part4").feature("wp1").geom().feature("arr1").set("type", "linear");
    model.geom("part4").feature("wp1").geom().feature("arr1").set("linearsize", "if(ny_batt>3, ny_batt/2, 1)");
    model.geom("part4").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "r_batt*tan(60[deg])+d_batt-(r_batt-r_batt*cos(30[deg]))*2"});
    model.geom("part4").feature("wp1").geom().run("arr1");
    model.geom("part4").feature("wp1").geom().create("r5", "Rectangle");
    model.geom("part4").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"d_batt", "if(ny_batt>3, r_batt*tan(60[deg])*(ny_batt-2), 1)"});
    model.geom("part4").feature("wp1").geom().feature("r5").set("pos", new String[]{"-r_batt", "0"});
    model.geom("part4").feature("wp1").geom().feature("r5").set("contributeto", "csel1");
    model.geom("part4").feature("wp1").geom().feature().duplicate("r6", "r5");
    model.geom("part4").feature("wp1").geom().feature("r6").set("pos", new String[]{"d_batt*(nx_batt-1)", "0"});
    model.geom("part4").feature("wp1").geom().feature("r6").setIndex("pos", "r_batt*tan(60[deg])", 1);
    model.geom("part4").feature("wp1").geom().run("r6");
    model.geom("part4").feature("wp1").geom().create("uni4", "Union");
    model.geom("part4").feature("wp1").geom().feature("uni4").selection("input").set("arr1", "r5", "r6");
    model.geom("part4").feature("wp1").geom().feature("uni4").set("intbnd", false);
    model.geom("part4").feature("wp1").geom().feature("uni4").set("contributeto", "csel1");
    model.geom("part4").run("wp1");
    model.geom("part4").feature().create("ext1", "Extrude");
    model.geom("part4").feature("ext1").set("workplane", "wp1");
    model.geom("part4").feature("ext1").selection("input").set("wp1");
    model.geom("part4").feature("ext1").setIndex("distance", "h_term+h_sc", 0);
    model.geom("part4").feature("ext1").setIndex("distance", "h_term+h_sc+h_batt", 1);
    model.geom("part4").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.geom("part4").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.geom("part4").feature("ext1").set("twist", new String[]{"0", "0"});
    model.geom("part4").feature("ext1").setIndex("distance", "h_term*2+h_sc*2+h_batt", 2);
    model.geom("part4").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.geom("part4").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.geom("part4").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.geom("part4").run("ext1");
    model.component("comp2").geom("geom1").create("if1", "If");
    model.component("comp2").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp2").geom("geom1").feature("if1").label("If 1 (stacking_type == 0)");
    model.component("comp2").geom("geom1").feature("if1").set("condition", "stacking_type == 0");
    model.component("comp2").geom("geom1").run("if1");
    model.component("comp2").geom("geom1").create("pi1", "PartInstance");
    model.component("comp2").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel1", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel2", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel3", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel1.dom", true);
    model.component("comp2").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp2").geom("geom1").selection("csel1").label("\u7535\u6c60\u57df");
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel2.dom", true);
    model.component("comp2").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp2").geom("geom1").selection("csel2").label("\u7ec8\u7aef\u57df");
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel2.dom", "csel2");
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel3.dom", true);
    model.component("comp2").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp2").geom("geom1").selection("csel3").label("\u6bcd\u7ebf\u57df");
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel3.dom", "csel3");
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel1.bnd", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel2.bnd", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel3.bnd", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepedg", "pi1_csel1.edg", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepedg", "pi1_csel2.edg", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeepedg", "pi1_csel3.edg", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeeppnt", "pi1_csel1.pnt", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeeppnt", "pi1_csel2.pnt", true);
    model.component("comp2").geom("geom1").feature("pi1").setEntry("selkeeppnt", "pi1_csel3.pnt", true);
    model.component("comp2").geom("geom1").run("pi1");
    model.component("comp2").geom("geom1").create("pi2", "PartInstance");
    model.component("comp2").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom1").feature("pi2").set("part", "part3");
    model.component("comp2").geom("geom1").run("pi2");
    model.component("comp2").geom("geom1").create("if2", "If");
    model.component("comp2").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp2").geom("geom1").feature().move("endif2", 5);
    model.component("comp2").geom("geom1").feature().move("if2", 4);
    model.component("comp2").geom("geom1").feature("if2").label("If 2 (stacking_type == 1)");
    model.component("comp2").geom("geom1").feature("if2").set("condition", "stacking_type == 1");
    model.component("comp2").geom("geom1").run("if2");
    model.component("comp2").geom("geom1").create("pi3", "PartInstance");
    model.component("comp2").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom1").feature("pi3").set("part", "part2");
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepobj", "pi3_csel1", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepobj", "pi3_csel2", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepobj", "pi3_csel3", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepdom", "pi3_csel1.dom", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_csel1.dom", "csel1");
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepdom", "pi3_csel2.dom", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_csel2.dom", "csel2");
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepdom", "pi3_csel3.dom", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_csel3.dom", "csel3");
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel1.bnd", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel2.bnd", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel3.bnd", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepedg", "pi3_csel1.edg", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepedg", "pi3_csel2.edg", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeepedg", "pi3_csel3.edg", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeeppnt", "pi3_csel1.pnt", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeeppnt", "pi3_csel2.pnt", true);
    model.component("comp2").geom("geom1").feature("pi3").setEntry("selkeeppnt", "pi3_csel3.pnt", true);
    model.component("comp2").geom("geom1").run("pi3");
    model.component("comp2").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom1").feature("wp1").set("quickz", "-h_term-h_sc");
    model.component("comp2").geom("geom1").run("wp1");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("if1", "If");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if1")
         .label("If 1\uff08y \u65b9\u5411\u4e3a\u5947\u6570\u4e2a\uff09");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if1")
         .set("condition", "if(floor(ny_batt/2)<ny_batt/2, 0, 1) == 0");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("if1");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("if2", "If");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if2").label("If 2 (ny_batt == 1)");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if2").set("condition", "ny_batt == 1");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("if2");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r_batt");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("if3", "If");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().createAfter("endif3", "EndIf", "if3");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if3").label("If 3 (nx_batt > 1)");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if3").set("condition", "nx_batt > 1");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("if3");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c2").set("r", "r_batt");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new String[]{"d_batt*(nx_batt-1)", "0"});
    model.component("comp2").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"d_batt*(nx_batt-1)", "1"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "d_batt", 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "-r_batt"});
    model.component("comp2").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("c1", "c2", "r1");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().createAfter("else1", "Else", "endif3");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().move("uni1", 7);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().move("pol1", 9);

    model.param().set("ny_batt", "3");

    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "d_batt*(nx_batt-1)", 0, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt", 1, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "r_batt*tan(60[deg])", 1, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt+r_batt*cos(30[deg])", 2, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "r_batt*tan(60[deg])-r_batt*sin(30[deg])", 2, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt*cos(30[deg])", 3, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "-r_batt*sin(30[deg])", 3, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("pol2", "pol1");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt", 0, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "r_batt*tan(60[deg])*(ny_batt-2)", 0, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "d_batt*(nx_batt-1)", 1, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "r_batt*tan(60[deg])*(ny_batt-1)", 1, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt*cos(30[deg])", 2, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(ny_batt-1)*r_batt*tan(60[deg])+r_batt*sin(30[deg])", 2, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "d_batt*(nx_batt-1)+r_batt*cos(30[deg])+r_batt", 3, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "r_batt*tan(60[deg])*(ny_batt-2)+r_batt*sin(30[deg])", 3, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("pol2");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("c3", "Circle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c3").set("r", "r_batt");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("c4", "c3");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c4")
         .set("pos", new String[]{"d_batt*(nx_batt-1)", "0"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("c5", "c4");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c5")
         .set("pos", new String[]{"0", "r_batt*tan(60[deg])*(ny_batt-1)"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("c6", "c5");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c6")
         .setIndex("pos", "d_batt*(nx_batt-1)", 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("c7", "c6");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c7")
         .setIndex("pos", "d_batt*(nx_batt-1)+r_batt", 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c7")
         .setIndex("pos", "r_batt*tan(60[deg])*(ny_batt-2)", 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("c8", "c7");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c8")
         .setIndex("pos", "r_batt*tan(60[deg])", 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("c8");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"d_batt*nx_batt", "1"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r2")
         .setIndex("size", "r_batt*tan(60[deg])*(ny_batt-1)", 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"-r_batt", "0"});
    model.component("comp2").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("if4", "If");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().createAfter("endif4", "EndIf", "if4");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if4").label("If 4 (nx_batt > 1)");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if4").set("condition", "nx_batt > 1");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("if4");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"d_batt*(nx_batt-1)", "1"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r3")
         .setIndex("size", "r_batt*tan(60[deg])*(ny_batt-1)+d_batt", 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"0", "-r_batt"});
    model.component("comp2").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("if5", "If");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().createAfter("endif5", "EndIf", "if5");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().move("endif5", 22);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().move("if5", 21);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if5").label("If 5(ny_batt > 3)");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if5").set("condition", "ny_batt > 3");

    model.param().set("ny_batt", "5");

    model.component("comp2").geom("geom1").feature("wp1").geom().run("if5");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"r_batt", "r_batt*tan(60[deg])*(ny_batt-3)"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"d_batt*(nx_batt-1)+r_batt", "0"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r4")
         .setIndex("pos", "r_batt*tan(60[deg])", 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("endif5");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("uni2").selection("input")
         .set("c3", "c4", "c5", "c6", "c7", "c8", "pol1", "pol2", "r2", "r3", "r4");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("uni2").set("intbnd", false);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("endif2");

    model.param().set("ny_batt", "4");

    model.component("comp2").geom("geom1").feature("wp1").geom().feature()
         .createAfter("elseif1", "ElseIf", "endif2");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("elseif1")
         .label("Else If 1\uff08y \u65b9\u5411\u4e3a\u5076\u6570\u4e2a\uff09");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("elseif1")
         .set("condition", "if(floor(ny_batt/2)<ny_batt/2, 0, 1) == 1");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("elseif1");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("pol3", "Polygon");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3").set("source", "table");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "-r_batt*cos(30[deg])", 0, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*sin(30[deg])", 0, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt-r_batt*cos(30[deg])", 1, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*sin(30[deg])+r_batt*tan(60[deg])", 1, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3").setIndex("table", "r_batt", 2, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*tan(60[deg])+r_batt", 2, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*(nx_batt*2-1)", 3, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*tan(60[deg])+r_batt", 3, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*(nx_batt*2)-(r_batt-r_batt*cos(30[deg]))", 4, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*tan(60[deg])-(r_batt-r_batt*sin(30[deg]))", 4, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*(nx_batt*2-2)+r_batt*cos(30[deg])", 5, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "-r_batt*sin(30[deg])", 5, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*(nx_batt*2-2)", 6, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt", 6, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3").setIndex("table", 0, 7, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt", 7, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3").setIndex("table", "-r_batt", 8, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3").setIndex("table", 0, 8, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "-r_batt*cos(30[deg])", 9, 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("pol3")
         .setIndex("table", "r_batt*sin(30[deg])", 9, 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("pol3");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("c9", "Circle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c9").set("r", "r_batt");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("c10", "c9");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c10")
         .set("pos", new String[]{"d_batt*(nx_batt-1)", "0"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("c11", "c10");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c11")
         .set("pos", new String[]{"r_batt", "r_batt*tan(60[deg])"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("c12", "c11");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("c12")
         .setIndex("pos", "d_batt*(nx_batt-1)+r_batt", 0);
    model.component("comp2").geom("geom1").feature("wp1").geom().runPre("c12");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("c12");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("uni3", "Union");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("uni3").selection("input")
         .set("c10", "c11", "c12", "c9", "pol3");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("uni3").set("intbnd", false);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("uni3");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("if6", "If");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().createAfter("endif6", "EndIf", "if6");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if6").label("If 6 (ny_batt > 3)");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("if6").set("condition", "ny_batt > 3");
    model.component("comp2").geom("geom1").feature("wp1").geom().run("if6");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("uni3");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("arr1").set("type", "linear");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("arr1").set("linearsize", "ny_batt/2");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "r_batt*tan(60[deg])+d_batt-(r_batt-r_batt*cos(30[deg]))*2"});
    model.component("comp2").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp2").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"d_batt", "r_batt*tan(60[deg])*(ny_batt-2)"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r5")
         .set("pos", new String[]{"-r_batt", "0"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature().duplicate("r6", "r5");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r6")
         .set("pos", new String[]{"d_batt*(nx_batt-1)", "0"});
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("r6")
         .setIndex("pos", "r_batt*tan(60[deg])", 1);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("r6");

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp2").geom("geom1").feature("wp1").geom().create("uni4", "Union");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("uni4").selection("input")
         .set("arr1", "r5", "r6");
    model.component("comp2").geom("geom1").feature("wp1").geom().feature("uni4").set("intbnd", false);
    model.component("comp2").geom("geom1").feature("wp1").geom().run("endif1");
    model.component("comp2").geom("geom1").run("wp1");
    model.component("comp2").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp2").geom("geom1").feature("ext1").setIndex("distance", "h_term+h_sc", 0);
    model.component("comp2").geom("geom1").feature("ext1").setIndex("distance", "h_term+h_sc+h_batt", 1);
    model.component("comp2").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp2").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp2").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp2").geom("geom1").feature("ext1").setIndex("distance", "h_term*2+h_sc*2+h_batt", 2);
    model.component("comp2").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp2").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp2").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp2").geom("geom1").run("ext1");
    model.component("comp2").geom("geom1").run("endif2");
    model.component("comp2").geom("geom1").create("pi4", "PartInstance");
    model.component("comp2").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom1").feature("pi4").set("part", "part4");
    model.component("comp2").geom("geom1").feature().move("pi4", 8);
    model.component("comp2").geom("geom1").run("fin");
    model.component("comp2").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp2").geom("geom1").feature("boxsel1").label("\u6240\u6709\u57df");
    model.component("comp2").geom("geom1").run("boxsel1");
    model.component("comp2").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp2").geom("geom1").feature("difsel1").label("\u7a7a\u6c14\u57df");
    model.component("comp2").geom("geom1").feature("difsel1").set("add", new String[]{"boxsel1"});
    model.component("comp2").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"csel1", "csel2", "csel3"});
    model.component("comp2").geom("geom1").run("difsel1");
    model.component("comp2").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp2").geom("geom1").feature("boxsel2").label("\u626b\u63a0\u7f51\u683c\u57df");
    model.component("comp2").geom("geom1").feature("boxsel2").set("zmin", 0);
    model.component("comp2").geom("geom1").feature("boxsel2").set("zmax", "h_batt");
    model.component("comp2").geom("geom1").feature("boxsel2").set("condition", "allvertices");
    model.component("comp2").geom("geom1").run("boxsel2");

    model.component("comp2").cpl().create("maxop1", "Maximum");
    model.component("comp2").cpl("maxop1").selection().named("geom1_csel1_dom");
    model.component("comp2").cpl().create("aveop1", "Average");
    model.component("comp2").cpl("aveop1").set("axisym", true);
    model.component("comp2").cpl("aveop1").selection().named("geom1_csel1_dom");

    model.component("comp2").selection().create("box1", "Box");
    model.component("comp2").selection("box1").label("\u7535\u6c60\u4e0b\u8fb9\u754c");
    model.component("comp2").selection("box1").set("entitydim", 2);
    model.component("comp2").selection("box1").set("zmin", "-h_term/2");
    model.component("comp2").selection("box1").set("zmax", "h_term/2");
    model.component("comp2").selection("box1").set("condition", "inside");
    model.component("comp2").selection().duplicate("box2", "box1");
    model.component("comp2").selection("box2").label("\u4e0b\u8fb9\u754c");
    model.component("comp2").selection("box2").set("zmin", Double.NEGATIVE_INFINITY);
    model.component("comp2").selection("box2").set("zmax", "-h_term-h_sc*0.99");
    model.component("comp2").selection().duplicate("box3", "box2");
    model.component("comp2").selection("box3").label("\u4e0a\u8fb9\u754c");
    model.component("comp2").selection("box3").set("zmin", "h_batt+h_term+h_sc*0.99");
    model.component("comp2").selection("box3").set("zmax", Double.POSITIVE_INFINITY);
    model.component("comp2").selection().create("box4", "Box");
    model.component("comp2").selection("box4").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp2").selection("box4").set("entitydim", 2);
    model.component("comp2").selection().create("dif1", "Difference");
    model.component("comp2").selection("dif1").label("\u4fa7\u8fb9\u754c");
    model.component("comp2").selection("dif1").set("entitydim", 2);
    model.component("comp2").selection("dif1").set("add", new String[]{"box4"});
    model.component("comp2").selection("dif1").set("subtract", new String[]{"box2", "box3"});

    model.component("comp2").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp2").physics().create("lb2", "LumpedBattery", "geom1");

    model.component("comp2").material().create("mat1", "Common");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").label("Air");
    model.component("comp2").material("mat1").set("family", "air");
    model.component("comp2").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat1").materialType("nonSolid");
    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat2").label("Aluminum");
    model.component("comp2").material("mat2").set("family", "aluminum");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp2").material().create("mat3", "Common");
    model.component("comp2").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp2").material("mat3").label("Copper");
    model.component("comp2").material("mat3").set("family", "copper");
    model.component("comp2").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp2").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp2").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat3").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp2").material("mat3").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp2").material("mat3").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp2").material("mat3").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp2").material("mat3").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp2").material("mat3").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp2").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp2").material("mat1").selection().named("geom1_difsel1");
    model.component("comp2").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp2").material("mat3").selection().named("geom1_csel3_dom");
    model.component("comp2").material().create("mat4", "Common");
    model.component("comp2").material("mat4").label("\u6d3b\u6027\u7535\u6c60\u6750\u6599");
    model.component("comp2").material("mat4").selection().named("geom1_csel1_dom");
    model.component("comp2").material("mat4").set("family", "custom");
    model.component("comp2").material("mat4")
         .set("customspecular", new double[]{0.5176470875740051, 0.7568627595901489, 0.9176470637321472});
    model.component("comp2").material("mat4").set("diffuse", "custom");
    model.component("comp2").material("mat4")
         .set("customdiffuse", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.component("comp2").material("mat4").set("ambient", "custom");
    model.component("comp2").material("mat4")
         .set("customambient", new double[]{0, 0.34117648005485535, 0.5921568870544434});
    model.component("comp2").material("mat4").set("noise", true);
    model.component("comp2").material("mat4").set("lighting", "phong");
    model.component("comp2").material("mat4").set("shininess", 64);

    model.component("comp2").physics("ht").prop("PhysicalModelProperty").set("Tref", "T0");
    model.component("comp2").physics("ht").feature("init1").set("Tinit", "T_init");
    model.component("comp2").physics("ht").create("bl1", "BatteryLayers", 3);
    model.component("comp2").physics("ht").feature("bl1").selection().named("geom1_csel1_dom");
    model.component("comp2").physics("ht").feature("bl1").set("LayerConfiguration", "SpirallyWound");
    model.component("comp2").physics("ht").feature("bl1").set("ThroughLayerConductivity", "kT_batt_r");
    model.component("comp2").physics("ht").feature("bl1").set("InLayerConductivity", "kT_batt_ang");
    model.component("comp2").physics("ht").feature("bl1").set("Density", "rho_batt");
    model.component("comp2").physics("ht").feature("bl1").set("HeatCapacity", "Cp_batt");
    model.component("comp2").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp2").physics("ht").feature("hf1").label("\u70ed\u901a\u91cf 1- \u4fa7\u9762");
    model.component("comp2").physics("ht").feature("hf1").selection().named("dif1");
    model.component("comp2").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht").feature("hf1").set("h", "ht");
    model.component("comp2").physics("ht").feature("hf1").set("Text", "T_init");
    model.component("comp2").physics("ht").feature().duplicate("hf2", "hf1");
    model.component("comp2").physics("ht").feature("hf2").label("\u70ed\u901a\u91cf 2- \u9876\u90e8");
    model.component("comp2").physics("ht").feature("hf2").selection().named("box3");
    model.component("comp2").physics("ht").feature("hf2").set("h", "ht_top");
    model.component("comp2").physics("ht").feature().duplicate("hf3", "hf2");
    model.component("comp2").physics("ht").feature("hf3").label("\u70ed\u901a\u91cf 3- \u5e95\u90e8");
    model.component("comp2").physics("ht").feature("hf3").selection().named("box2");
    model.component("comp2").physics("ht").feature("hf3").set("h", "ht_bottom");
    model.component("comp2").physics("lb2").selection().named("geom1_csel1_dom");
    model.component("comp2").physics("lb2").prop("BatterySettings").set("I_app", "I_1C*C_rate*C_sign*n_batt");
    model.component("comp2").physics("lb2").prop("BatterySettings").set("Q_cell0", "Q_cell*n_batt");
    model.component("comp2").physics("lb2").prop("BatterySettings").set("SOC_cell0", "initial_SOC");
    model.component("comp2").physics("lb2").feature("cep1").set("OpenCircuitVoltageInput", "fromdef");
    model.component("comp2").physics("lb2").feature("cep1").set("Eocvdef", "int2");
    model.component("comp2").physics("lb2").feature("cep1").set("Tref", "T0");
    model.component("comp2").physics("lb2").feature("vl1").set("eta_ir1C", "opt_eta_IR_1C");
    model.component("comp2").physics("lb2").feature("vl1").set("J0", "opt_J0");
    model.component("comp2").physics("lb2").feature("vl1").set("IncludeConcentrationOverpotential", true);
    model.component("comp2").physics("lb2").feature("vl1").set("tau", "opt_tau");

    model.component("comp2").multiphysics().create("ech1", "ElectrochemicalHeating", 3);

    model.component("comp2").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp2").mesh("mesh1").automatic(false);
    model.component("comp2").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh1").feature().move("ftri1", 1);
    model.component("comp2").mesh("mesh1").feature("ftri1").selection().named("box1");
    model.component("comp2").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("swe1").selection().named("geom1_boxsel2");
    model.component("comp2").mesh("mesh1").run("swe1");
    model.component("comp2").mesh("mesh1").run("ftet1");
    model.component("comp2").mesh("mesh1").run("ftet1");
    model.component("comp2").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/lb", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", false);
    model.study("std1").feature("time").setSolveFor("/physics/lb2", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ech1", false);
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time")
         .set("tlist", "range(0, (abs(final_SOC-initial_SOC)/C_rate)/(output_steps-1),abs(final_SOC-initial_SOC)/C_rate)");
    model.study("std1").feature("time").set("probesel", "none");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"ht", "lb2"});
    model.study("std1").feature("time").set("disabledcoupling", new String[]{"ech1"});
    model.study("std1").setGenPlots(false);
    model.study("std1").create("lsqo", "LSQOptimization");
    model.study("std1").feature("lsqo").set("source", "resultTable");
    model.study("std1").feature("lsqo").setEntry("modelExpression", "col2", "comp1.lb.E_cell");
    model.study("std1").feature("lsqo").setEntry("unit", "col2", "V");
    model.study("std1").feature("lsqo").setEntry("columnType", "col3", "none");
    model.study("std1").feature("lsqo").setIndex("pname", "beta", 0);
    model.study("std1").feature("lsqo").setIndex("initval", "atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad]", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "beta", 0);
    model.study("std1").feature("lsqo").setIndex("initval", "atan((r_batt*tan(60[deg])-d_sc)/r_batt)[rad]", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "C_rate", 1);
    model.study("std1").feature("lsqo").setIndex("initval", 4, 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "C_rate", 1);
    model.study("std1").feature("lsqo").setIndex("initval", 4, 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "C_sign", 2);
    model.study("std1").feature("lsqo").setIndex("initval", "sign(final_SOC-initial_SOC)", 2);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std1").feature("lsqo").setIndex("pname", "C_sign", 2);
    model.study("std1").feature("lsqo").setIndex("initval", "sign(final_SOC-initial_SOC)", 2);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std1").feature("lsqo").setIndex("pname", "eta_IR_1C", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 0.01, 0);
    model.study("std1").feature("lsqo").setIndex("pname", "invJ0", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "tau", 2);
    model.study("std1").feature("lsqo").setIndex("scale", 1000, 2);
    model.study("std1").feature("lsqo").set("opttolinner", "opt_tol");
    model.study("std1").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std1").feature("lsqo").set("useobjtable", false);
    model.study("std1").feature("lsqo").set("plot", true);
    model.study("std1").createAutoSequences("all");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u7535\u6c60\u7535\u4f4d\u548c\u8d1f\u8f7d (lb)");
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg1").set("showsecylabel", "on");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("legendpos", "lowerleft");
    model.result("pg1").feature().create("glob1", "Global");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("expr", new String[]{"comp1.lb.E_cell"});
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("data", "parent");

    return model;
  }

  public static Model run5(Model model) {
    model.result("pg1").feature().create("glob2", "Global");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("expr", new String[]{"comp1.lb.Eocv_cell"});
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("data", "parent");
    model.result("pg1").feature().create("glob3", "Global");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("plotonsecyaxis", true);
    model.result("pg1").feature("glob3").set("expr", new String[]{"comp1.lb.I_cell"});
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("data", "parent");

    model.sol("sol1").runAll();

    model.result().remove("pg1");

    model.study("std1").feature("lsqo").set("plotgroup", "Default");
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/lb", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/physics/lb2", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/ech1", true);
    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time")
         .set("tlist", "range(0, (abs(final_SOC-initial_SOC)/C_rate)/(output_steps-1),abs(final_SOC-initial_SOC)/C_rate)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").setSolveFor("/physics/lb", false);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"lb"});
    model.study("std2").feature("time").set("plot", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");

    model.sol("sol2").runAll();

    model.result().remove("pg1");

    model.study("std2").feature("time").set("plotgroup", "Default");

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("eta_IR_1C");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").setIndex("expr", "eta_IR_1C", 0);
    model.result().numerical("gev1")
         .setIndex("descr", "1C \u6b27\u59c6\u8fc7\u7535\u4f4d\uff0c\u62df\u5408\u53c2\u6570", 0);
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").label("invJ0");
    model.result().numerical("gev2").setIndex("expr", "invJ0", 0);
    model.result().numerical("gev2")
         .setIndex("descr", "\u9006\u65e0\u91cf\u7eb2\u7535\u8377\u4ea4\u6362\u7535\u6d41\uff0c\u62df\u5408\u53c2\u6570", 0);
    model.result().numerical().duplicate("gev3", "gev2");
    model.result().numerical("gev3").label("tau");
    model.result().numerical("gev3").setIndex("expr", "tau", 0);
    model.result().numerical("gev3")
         .setIndex("descr", "\u6269\u6563\u65f6\u95f4\u5e38\u6570\uff0c\u62df\u5408\u53c2\u6570", 0);
    model.result().numerical().duplicate("gev4", "gev3");
    model.result().numerical("gev4").label("J0");
    model.result().numerical("gev4").setIndex("expr", "J0", 0);
    model.result().numerical("gev4").setIndex("descr", "\u65e0\u91cf\u7eb2\u7535\u8377\u4ea4\u6362\u7535\u6d41", 0);
    model.result().numerical().duplicate("gev5", "gev4");
    model.result().numerical("gev5").label("\u6700\u9ad8\u7535\u6c60\u6e29\u5ea6");
    model.result().numerical("gev5").set("data", "dset2");
    model.result().numerical("gev5").setIndex("looplevelinput", "all", 0);
    model.result().numerical("gev5").setIndex("expr", "maxop1(T)", 0);
    model.result().numerical("gev5").setIndex("unit", "degC", 0);
    model.result().numerical("gev5").setIndex("descr", "\u6700\u5927\u503c 1", 0);
    model.result().numerical("gev5").set("dataseries", "maximum");
    model.result().numerical().duplicate("gev6", "gev5");
    model.result().numerical("gev6").label("\u5e73\u5747\u7535\u6c60\u6e29\u5ea6");
    model.result().numerical("gev6").setIndex("expr", "aveop1(T)", 0);
    model.result().numerical("gev6").set("dataseries", "average");
    model.result().numerical().create("meas1", "MeasureVolume");
    model.result().numerical("meas1").set("data", "dset2");
    model.result().numerical("meas1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("meas1").selection().all();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u4f53\u79ef\u6d4b\u91cf 1");
    model.result().numerical("meas1").set("table", "tbl3");
    model.result().numerical("meas1").setResult();

    model.component("comp2").view("view2").set("showmaterial", true);
    model.component("comp2").view("view2").hideEntities().create("hide1");
    model.component("comp2").view("view2").hideEntities("hide1").named("geom1_difsel1");
    model.component("comp2").view("view2").hideEntities("hide1").label("\u9690\u85cf\u7a7a\u6c14\u57df");
    model.component("comp2").view("view2").hideEntities().create("hide2");
    model.component("comp2").view("view2").hideEntities("hide2").geom("geom1", 1);
    model.component("comp2").view("view2").hideEntities("hide2").all();
    model.component("comp2").view("view2").hideEntities("hide2").label("\u9690\u85cf\u6240\u6709\u8fb9");
    model.component("comp2").view("view2").hideEntities().create("hide3");
    model.component("comp2").view("view2").hideEntities("hide3").named("geom1_csel3_dom");
    model.component("comp2").view("view2").hideEntities("hide3").active(false);

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5b9e\u9a8c\u6570\u636e");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("xaxisdata", 1);
    model.result("pg1").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg1").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg1").feature("tblp1").set("linecolor", "custom");
    model.result("pg1").feature("tblp1")
         .set("customlinecolor", new double[]{0, 0.501960813999176, 0.7529411911964417});
    model.result("pg1").feature("tblp1").set("linewidth", 2);
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u7535\u538b (V)", 0);
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("plotcolumns", new int[]{3});
    model.result("pg1").feature("tblp2").set("plotonsecyaxis", true);
    model.result("pg1").feature("tblp2")
         .set("customlinecolor", new double[]{0.6392157077789307, 0.3294117748737335, 0.5607843399047852});
    model.result("pg1").feature("tblp2").set("legendmethod", "automatic");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5f00\u8def\u7535\u538b");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("table", "tbl2");
    model.result("pg2").feature("tblp1").set("linecolor", "custom");
    model.result("pg2").feature("tblp1")
         .set("customlinecolor", new double[]{0.21568627655506134, 0.48627451062202454, 0.5333333611488342});
    model.result("pg2").feature("tblp1").set("linewidth", 2);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u6c60\u8377\u7535\u72b6\u6001");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u7535\u6c60\u8377\u7535\u72b6\u6001\u548c\u7535\u6c60\u7535\u6d41");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "comp1.lb.SOC_cell", 0);
    model.result("pg3").feature("glob1").set("linecolor", "custom");
    model.result("pg3").feature("glob1")
         .set("customlinecolor", new double[]{0.21568627655506134, 0.5058823823928833, 0.5686274766921997});
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").setIndex("expr", "comp1.lb.I_cell", 0);
    model.result("pg3").feature("glob2")
         .set("customlinecolor", new double[]{0.6392157077789307, 0.3294117748737335, 0.5607843399047852});
    model.result("pg3").run();
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u6c60\u7535\u538b");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "comp1.lb.E_cell", 0);
    model.result("pg4").feature("glob1").set("linecolor", "custom");
    model.result("pg4").feature("glob1")
         .set("customlinecolor", new double[]{0.21568627655506134, 0.5058823823928833, 0.5686274766921997});
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("expr", "comp1.lb.Eocv_cell", 0);
    model.result("pg4").feature("glob2")
         .set("customlinecolor", new double[]{0.6392157077789307, 0.3294117748737335, 0.5607843399047852});
    model.result("pg4").feature().duplicate("glob3", "glob2");
    model.result("pg4").run();
    model.result("pg4").feature("glob3").setIndex("expr", "E_cell_exp(t)[V]", 0);
    model.result("pg4").feature("glob3").setIndex("descr", "\u5b9e\u9a8c\u7535\u6c60\u7535\u538b", 0);
    model.result("pg4").feature("glob3").set("linestyle", "dotted");
    model.result("pg4").feature("glob3")
         .set("customlinecolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u538b\u635f\u5931\u548c\u8d1f\u8f7d");
    model.result("pg5").set("ylabel", "\u8fc7\u7535\u538b (V)");
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "comp1.lb.eta_ir", 0);
    model.result("pg5").run();
    model.result("pg5").feature("glob2").setIndex("expr", "comp1.lb.eta_act", 0);
    model.result("pg5").run();
    model.result("pg5").feature("glob3").setIndex("expr", "comp1.lb.eta_conc", 0);
    model.result("pg5").feature("glob3").setIndex("descr", "\u6d53\u5ea6\u8fc7\u7535\u4f4d", 0);
    model.result("pg5").feature().duplicate("glob4", "glob3");
    model.result("pg5").run();
    model.result("pg5").feature("glob4").set("plotonsecyaxis", true);
    model.result("pg5").feature("glob4").setIndex("expr", "comp1.lb.I_cell", 0);
    model.result("pg5").feature("glob4")
         .set("customlinecolor", new double[]{0.5176470875740051, 0.7568627595901489, 0.9176470637321472});
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6700\u9ad8/\u5e73\u5747\u7535\u6c60\u6e29\u5ea6");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u7535\u6c60\u6e29\u5ea6 (degC)");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u65f6\u95f4 (h)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u6e29\u5ea6 (degC)");
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "maxop1(T)", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "degC", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u6700\u9ad8\u6e29\u5ea6", 0);
    model.result("pg6").feature("glob1").set("linecolor", "custom");
    model.result("pg6").feature("glob1")
         .set("customlinecolor", new double[]{0.6392157077789307, 0.3294117748737335, 0.5607843399047852});
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("linemarker", "circle");
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").setIndex("expr", "aveop1(T)", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "\u5e73\u5747\u6e29\u5ea6", 0);
    model.result("pg6").feature("glob2")
         .set("customlinecolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg6").run();

    model.study("std2").feature("time").set("plotgroup", "pg6");
    model.study("std2").feature("time").set("probesel", "none");

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6 - \u5207\u9762");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("slc1", "Slice");
    model.result("pg7").feature("slc1").set("unit", "degC");
    model.result("pg7").feature("slc1").set("quickplane", "xy");
    model.result("pg7").feature("slc1").set("colortable", "HeatCamera");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u6e29\u5ea6 - \u4f53");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("colortable", "HeatCamera");
    model.result("pg8").feature("vol1").set("unit", "degC");
    model.result("pg8").run();
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
    model.result().export("anim1").set("plotgroup", "pg8");
    model.result().export("anim1").set("solnumtype", "inner");

    model.title(null);

    model.description("");

    model.label("li_battery_pack_designer_embedded.mph");

    model.result().export("anim1").showFrame();
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").feature().clear();
    model.result().report("rpt1").create("tp1", "TitlePage");
    model.result().report("rpt1").create("toc1", "TableOfContents");
    model.result().report("rpt1").create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").set("source", "custom");
    model.result().report("rpt1").feature("sec1").set("heading", "\u5168\u5c40\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec1").create("root1", "Model");
    model.result().report("rpt1").feature("sec1").create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").set("source", "custom");
    model.result().report("rpt1").feature("sec1").feature("sec1").set("heading", "\u53c2\u6570");
    model.result().report("rpt1").feature("sec1").feature("sec1").create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec1")
         .set("heading", "\u53c2\u6570\uff1a\u51e0\u4f55\u5e8f\u5217");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec1").create("param1", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec1").feature("param1")
         .set("noderef", "default");
    model.result().report("rpt1").feature("sec1").feature("sec1").create("sec2", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec2")
         .set("heading", "\u53c2\u6570\uff1a\u53c2\u6570\u4f30\u8ba1");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec2").create("param1", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec2").feature("param1")
         .set("noderef", "par2");
    model.result().report("rpt1").feature("sec1").feature("sec1").create("sec3", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec3")
         .set("heading", "\u53c2\u6570\uff1a\u70ed\u7ba1\u7406");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec3").create("param1", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec3").feature("param1")
         .set("noderef", "par3");
    model.result().report("rpt1").feature("sec1").create("sec2", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec2").set("source", "custom");
    model.result().report("rpt1").feature("sec1").feature("sec2").set("heading", "\u51fd\u6570");
    model.result().report("rpt1").feature("sec1").feature("sec2").create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec1")
         .set("heading", "\u8d1f\u8f7d\u5468\u671f\u6570\u636e");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec1").create("func1", "Functions");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec1").feature("func1")
         .set("noderef", "int1");
    model.result().report("rpt1").feature("sec1").feature("sec2").create("sec2", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec2")
         .set("heading", "\u5f00\u8def\u7535\u538b");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec2").create("func1", "Functions");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec2").feature("func1")
         .set("noderef", "int2");
    model.result().report("rpt1").feature("sec1").create("sec3", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").set("source", "custom");
    model.result().report("rpt1").feature("sec1").feature("sec3").set("heading", "\u51e0\u4f55\u96f6\u4ef6");
    model.result().report("rpt1").feature("sec1").feature("sec3").create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec1")
         .set("heading", "\u96f6\u4ef6 1\uff1a\u76f4\u5305\u88c5");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec1").create("gpart1", "GeometryPart");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec1").feature("gpart1")
         .set("noderef", "part1");
    model.result().report("rpt1").feature("sec1").feature("sec3").create("sec2", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec2").set("source", "custom");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec2").set("heading", "\u53c2\u6570");
    model.result().report("rpt1").feature("sec1").feature("sec3").create("sec3", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec3")
         .set("heading", "\u96f6\u4ef6 2\uff1a\u504f\u5305\u88c5");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec3").create("gpart1", "GeometryPart");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec3").feature("gpart1")
         .set("noderef", "part2");
    model.result().report("rpt1").feature("sec1").feature("sec3").create("sec4", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec4").set("source", "custom");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec4").set("heading", "\u53c2\u6570");
    model.result().report("rpt1").feature("sec1").feature("sec3").create("sec5", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec5").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec5")
         .set("heading", "\u96f6\u4ef6 3\uff1a\u70ed\u6536\u7f29\u76f4\u5305\u88c5");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec5").create("gpart1", "GeometryPart");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec5").feature("gpart1")
         .set("noderef", "part3");
    model.result().report("rpt1").feature("sec1").feature("sec3").create("sec6", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec6").set("source", "custom");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec6").set("heading", "\u53c2\u6570");
    model.result().report("rpt1").feature("sec1").feature("sec3").create("sec7", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec7").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec7")
         .set("heading", "\u96f6\u4ef6 4\uff1a\u70ed\u6536\u7f29\u504f\u5305\u88c5");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec7").create("gpart1", "GeometryPart");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec7").feature("gpart1")
         .set("noderef", "part4");
    model.result().report("rpt1").feature("sec1").feature("sec3").create("sec8", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec8").set("source", "custom");
    model.result().report("rpt1").feature("sec1").feature("sec3").feature("sec8").set("heading", "\u53c2\u6570");
    model.result().report("rpt1").create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").set("source", "custom");
    model.result().report("rpt1").feature("sec2").set("heading", "\u7ec4\u4ef6 1");
    model.result().report("rpt1").feature("sec2").create("comp1", "ModelNode");
    model.result().report("rpt1").feature("sec2").feature("comp1").set("noderef", "comp1");
    model.result().report("rpt1").feature("sec2").create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec1").set("heading", "\u96c6\u603b\u7535\u6c60");
    model.result().report("rpt1").feature("sec2").feature("sec1").create("phys1", "Physics");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("phys1").set("noderef", "lb");
    model.result().report("rpt1").create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").set("source", "custom");
    model.result().report("rpt1").feature("sec3").set("heading", "\u7ec4\u4ef6 2");
    model.result().report("rpt1").feature("sec3").create("comp1", "ModelNode");
    model.result().report("rpt1").feature("sec3").feature("comp1").set("noderef", "comp2");
    model.result().report("rpt1").feature("sec3").create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").set("source", "custom");
    model.result().report("rpt1").feature("sec3").feature("sec1").set("heading", "\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec3").feature("sec1").create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").set("source", "custom");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").set("heading", "\u9009\u62e9");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec1")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec1")
         .set("heading", "\u7535\u6c60\u4e0b\u8fb9\u754c");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec1")
         .create("sel1", "Selection");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec1").feature("sel1")
         .set("noderef", "box1");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec2")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec2")
         .set("heading", "\u4e0b\u8fb9\u754c");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec2")
         .create("sel1", "Selection");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec2").feature("sel1")
         .set("noderef", "box2");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec3")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec3")
         .set("heading", "\u4e0a\u8fb9\u754c");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec3")
         .create("sel1", "Selection");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec3").feature("sel1")
         .set("noderef", "box3");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec4")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec4")
         .set("heading", "\u6240\u6709\u8fb9\u754c");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec4")
         .create("sel1", "Selection");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec4").feature("sel1")
         .set("noderef", "box4");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").create("sec5", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec5")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec5")
         .set("heading", "\u4fa7\u8fb9\u754c");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec5")
         .create("sel1", "Selection");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("sec5").feature("sel1")
         .set("noderef", "dif1");

    return model;
  }

  public static Model run6(Model model) {
    model.result().report("rpt1").feature("sec3").feature("sec1").create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").set("source", "custom");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2")
         .set("heading", "\u975e\u5c40\u90e8\u8026\u5408");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("sec1")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("sec1")
         .set("heading", "\u6700\u5927\u503c 1");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("sec1")
         .create("cpl1", "ComponentCoupling");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("sec1").feature("cpl1")
         .set("noderef", "maxop1");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("sec2")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("sec2")
         .set("heading", "\u5e73\u5747\u503c 1");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("sec2")
         .create("cpl1", "ComponentCoupling");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("sec2").feature("cpl1")
         .set("noderef", "aveop1");
    model.result().report("rpt1").feature("sec3").feature("sec1").create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").set("source", "custom");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3")
         .set("heading", "\u5750\u6807\u7cfb");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("sec1")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("sec1")
         .set("heading", "\u8fb9\u754c\u5750\u6807\u7cfb 1");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("sec1")
         .create("csys1", "CoordinateSystem");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("sec1").feature("csys1")
         .set("noderef", "sys1");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("sec2")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("sec2")
         .set("heading", "\u591a\u5706\u67f1\u5750\u6807\u7cfb");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("sec2")
         .create("csys1", "CoordinateSystem");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("sec2").feature("csys1")
         .set("noderef", "BatteryLayers_ht_bl1");
    model.result().report("rpt1").feature("sec3").create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec2").set("heading", "\u51e0\u4f55 1");
    model.result().report("rpt1").feature("sec3").feature("sec2").create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("geom1").set("noderef", "geom1");
    model.result().report("rpt1").feature("sec3").create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").set("source", "custom");
    model.result().report("rpt1").feature("sec3").feature("sec3").set("heading", "\u6750\u6599");
    model.result().report("rpt1").feature("sec3").feature("sec3").create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec1").set("heading", "Air");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec1").create("mat1", "Material");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec1").feature("mat1")
         .set("noderef", "mat1");
    model.result().report("rpt1").feature("sec3").feature("sec3").create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").set("heading", "Aluminum");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").create("mat1", "Material");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature("mat1")
         .set("noderef", "mat2");
    model.result().report("rpt1").feature("sec3").feature("sec3").create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").set("heading", "Copper");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").create("mat1", "Material");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature("mat1")
         .set("noderef", "mat3");
    model.result().report("rpt1").feature("sec3").feature("sec3").create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec4").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec4")
         .set("heading", "\u6d3b\u6027\u7535\u6c60\u6750\u6599");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec4").create("mat1", "Material");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec4").feature("mat1")
         .set("noderef", "mat4");
    model.result().report("rpt1").feature("sec3").create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec4").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec4").set("heading", "\u56fa\u4f53\u4f20\u70ed");
    model.result().report("rpt1").feature("sec3").feature("sec4").create("phys1", "Physics");
    model.result().report("rpt1").feature("sec3").feature("sec4").feature("phys1").set("noderef", "ht");
    model.result().report("rpt1").feature("sec3").create("sec5", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec5").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec5").set("heading", "\u96c6\u603b\u7535\u6c60 2");
    model.result().report("rpt1").feature("sec3").feature("sec5").create("phys1", "Physics");
    model.result().report("rpt1").feature("sec3").feature("sec5").feature("phys1").set("noderef", "lb2");
    model.result().report("rpt1").feature("sec3").create("sec6", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec6").set("source", "custom");
    model.result().report("rpt1").feature("sec3").feature("sec6").set("heading", "\u591a\u7269\u7406\u573a");
    model.result().report("rpt1").feature("sec3").feature("sec6").create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec6").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec6").feature("sec1")
         .set("heading", "\u7535\u5316\u5b66\u70ed 1");
    model.result().report("rpt1").feature("sec3").feature("sec6").feature("sec1").create("mph1", "Multiphysics");
    model.result().report("rpt1").feature("sec3").feature("sec6").feature("sec1").feature("mph1")
         .set("noderef", "ech1");
    model.result().report("rpt1").feature("sec3").create("sec7", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec7").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec7").set("heading", "\u7f51\u683c 1");
    model.result().report("rpt1").feature("sec3").feature("sec7").create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("sec7").feature("mesh1").set("noderef", "mesh1");
    model.result().report("rpt1").create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").set("source", "custom");
    model.result().report("rpt1").feature("sec4").set("heading", "\u7814\u7a76 1");
    model.result().report("rpt1").feature("sec4").create("std1", "Study");
    model.result().report("rpt1").feature("sec4").feature("std1").set("noderef", "std1");
    model.result().report("rpt1").create("sec5", "Section");
    model.result().report("rpt1").feature("sec5").set("source", "custom");
    model.result().report("rpt1").feature("sec5").set("heading", "\u7814\u7a76 2");
    model.result().report("rpt1").feature("sec5").create("std1", "Study");
    model.result().report("rpt1").feature("sec5").feature("std1").set("noderef", "std2");
    model.result().report("rpt1").create("sec6", "Section");
    model.result().report("rpt1").feature("sec6").set("source", "custom");
    model.result().report("rpt1").feature("sec6").set("heading", "\u7ed3\u679c");
    model.result().report("rpt1").feature("sec6").create("sec1", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec1").set("source", "custom");
    model.result().report("rpt1").feature("sec6").feature("sec1").set("heading", "\u6570\u636e\u96c6");
    model.result().report("rpt1").feature("sec6").feature("sec1").create("sec1", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("sec1")
         .set("heading", "\u7814\u7a76 1/\u89e3 1");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("sec1").create("dset1", "DataSet");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("sec1").feature("dset1")
         .set("noderef", "dset1");
    model.result().report("rpt1").feature("sec6").feature("sec1").create("sec2", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("sec2")
         .set("heading", "\u7814\u7a76 2/\u89e3 2");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("sec2").create("dset1", "DataSet");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("sec2").feature("dset1")
         .set("noderef", "dset2");
    model.result().report("rpt1").feature("sec6").create("sec2", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec2").set("source", "custom");
    model.result().report("rpt1").feature("sec6").feature("sec2").set("heading", "\u6d3e\u751f\u503c");
    model.result().report("rpt1").feature("sec6").feature("sec2").create("sec1", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec1").set("heading", "eta_IR_1C");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec1").create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec1").feature("num1")
         .set("noderef", "gev1");
    model.result().report("rpt1").feature("sec6").feature("sec2").create("sec2", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec2").set("heading", "invJ0");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec2").create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec2").feature("num1")
         .set("noderef", "gev2");
    model.result().report("rpt1").feature("sec6").feature("sec2").create("sec3", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec3").set("heading", "tau");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec3").create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec3").feature("num1")
         .set("noderef", "gev3");
    model.result().report("rpt1").feature("sec6").feature("sec2").create("sec4", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec4").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec4").set("heading", "J0");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec4").create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec4").feature("num1")
         .set("noderef", "gev4");
    model.result().report("rpt1").feature("sec6").feature("sec2").create("sec5", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec5").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec5")
         .set("heading", "\u6700\u9ad8\u7535\u6c60\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec5").create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec5").feature("num1")
         .set("noderef", "gev5");
    model.result().report("rpt1").feature("sec6").feature("sec2").create("sec6", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec6").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec6")
         .set("heading", "\u5e73\u5747\u7535\u6c60\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec6").create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec6").feature("num1")
         .set("noderef", "gev6");
    model.result().report("rpt1").feature("sec6").feature("sec2").create("sec7", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec7").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec7")
         .set("heading", "\u4f53\u79ef\u6d4b\u91cf 1");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec7").create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec6").feature("sec2").feature("sec7").feature("num1")
         .set("noderef", "meas1");
    model.result().report("rpt1").feature("sec6").create("sec3", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec3").set("source", "custom");
    model.result().report("rpt1").feature("sec6").feature("sec3").set("heading", "\u8868\u683c");
    model.result().report("rpt1").feature("sec6").feature("sec3").create("sec1", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec1")
         .set("heading", "\u8d1f\u8f7d\u5468\u671f\u6570\u636e");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec1").create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec1").feature("mtbl1")
         .set("noderef", "tbl1");
    model.result().report("rpt1").feature("sec6").feature("sec3").create("sec2", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec2")
         .set("heading", "\u5f00\u8def\u7535\u538b");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec2").create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec2").feature("mtbl1")
         .set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec6").feature("sec3").create("sec3", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec3").set("heading", "\u8868\u683c 3");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec3").create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec6").feature("sec3").feature("sec3").feature("mtbl1")
         .set("noderef", "tbl3");
    model.result().report("rpt1").feature("sec6").create("sec4", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").set("source", "custom");
    model.result().report("rpt1").feature("sec6").feature("sec4").set("heading", "\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec6").feature("sec4").create("sec1", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec1")
         .set("heading", "\u5b9e\u9a8c\u6570\u636e");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec1").create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec1").feature("pg1")
         .set("noderef", "pg1");
    model.result().report("rpt1").feature("sec6").feature("sec4").create("sec2", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec2")
         .set("heading", "\u5f00\u8def\u7535\u538b");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec2").create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec2").feature("pg1")
         .set("noderef", "pg2");
    model.result().report("rpt1").feature("sec6").feature("sec4").create("sec3", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec3")
         .set("heading", "\u7535\u6c60\u8377\u7535\u72b6\u6001");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec3").create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec3").feature("pg1")
         .set("noderef", "pg3");
    model.result().report("rpt1").feature("sec6").feature("sec4").create("sec4", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec4").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec4")
         .set("heading", "\u7535\u6c60\u7535\u538b");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec4").create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec4").feature("pg1")
         .set("noderef", "pg4");
    model.result().report("rpt1").feature("sec6").feature("sec4").create("sec5", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec5").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec5")
         .set("heading", "\u7535\u538b\u635f\u5931\u548c\u8d1f\u8f7d");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec5").create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec5").feature("pg1")
         .set("noderef", "pg5");
    model.result().report("rpt1").feature("sec6").feature("sec4").create("sec6", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec6").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec6")
         .set("heading", "\u6700\u9ad8/\u5e73\u5747\u7535\u6c60\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec6").create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec6").feature("pg1")
         .set("noderef", "pg6");
    model.result().report("rpt1").feature("sec6").feature("sec4").create("sec7", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec7").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec7")
         .set("heading", "\u6e29\u5ea6 - \u5207\u9762");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec7").create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec7").feature("pg1")
         .set("noderef", "pg7");
    model.result().report("rpt1").feature("sec6").feature("sec4").create("sec8", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec8").set("source", "firstchild");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec8")
         .set("heading", "\u6e29\u5ea6 - \u4f53");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec8").create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec4").feature("sec8").feature("pg1")
         .set("noderef", "pg8");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///li_battery_estimation_thermal.docx");
    model.result().report("rpt1").feature("tp1").set("title", "\u9502\u7535\u6c60\u7ec4\u8bbe\u8ba1\u5668");

    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", false);
    model.study("std1").feature("time").setSolveFor("/physics/lb2", true);
    model.study("std1").feature("time").setSolveFor("/physics/lb2", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ech1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ech1", false);
    model.study("std1").feature("time").set("useadvanceddisable", false);

    model.title("\u9502\u7535\u6c60\u7ec4\u8bbe\u8ba1\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4f7f\u7528\u5361\u7247\u5806\u7684\u52a8\u6001\u5e2e\u52a9\u7cfb\u7edf\n\u2022 \u5355\u4e2a App \u4e2d\u7684\u591a\u4e2a\u7ec4\u4ef6\uff08\u4e00\u7ef4\u548c\u4e09\u7ef4\uff09\n\u2022 \u529f\u80fd\u533a\u4e2d\u7684\u5207\u6362\u6309\u94ae\u7528\u4e8e\u663e\u793a\u4e0d\u540c\u7684\u8f93\u5165\uff0c\u9690\u85cf/\u663e\u793a\u51e0\u4f55\u9009\u62e9\u4ee5\u53ca\u663e\u793a\u52a8\u6001\u5e2e\u52a9\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u5bfc\u5165\u5b9e\u9a8c\u6570\u636e\n\u2022 \u7528\u4e8e\u521b\u5efa\u4e0d\u540c\u7f51\u683c\u5927\u5c0f\u7684\u9009\u9879\n\u2022 \u91cd\u7f6e\u90e8\u5206\u6216\u6240\u6709\u8f93\u5165\u53c2\u6570\n\u2022 \u5728 App \u4f1a\u8bdd\u671f\u95f4\u751f\u6210\u7ed3\u679c\u8868\n\u2022 \u5982\u679c\u60a8\u6709 LiveLink\u2122 for Excel\u00ae \u8bb8\u53ef\u8bc1\uff0c\u53ef\u4ee5\u5c06\u7ed3\u679c\u5bfc\u51fa\u81f3\u6587\u672c\u6587\u4ef6\u6216 Microsoft\u00ae Excel\u3002\n\u2022 \u7528\u4e8e\u63a7\u5236\u7ed8\u56fe\u65f6\u6b65\u7684\u6ed1\u5757\u548c\u6309\u94ae\n\u2022 \u901a\u8fc7\u52a8\u753b\u5c06\u7ed3\u679c\u53ef\u89c6\u5316\n\u2022 \u81ea\u5b9a\u4e49\u7a97\u53e3\u56fe\u6807\u3002\n\n\u8fd9\u662f\u4e00\u4e2a\u4f7f\u7528\u8d1f\u8f7d\u5468\u671f\u548c SOC vs. OCV \u76f8\u5173\u6027\u5b9e\u9a8c\u6570\u636e\u7814\u7a76\u7535\u6c60\u7ec4\u52a8\u6001\u7535\u538b\u548c\u70ed\u7279\u6027\u7684\u5de5\u5177\u3002\n\n\u8be5 App \u53ef\u4ee5\u5bf9\u5404\u79cd\u53c2\u6570\u6267\u884c\u53c2\u6570\u4f30\u8ba1\uff0c\u4f8b\u5982\u6b27\u59c6\u8fc7\u7535\u4f4d\u3001\u6269\u6563\u65f6\u95f4\u5e38\u6570\u548c\u65e0\u91cf\u7eb2\u4ea4\u6362\u7535\u6d41\u3002\u7136\u540e\uff0c\u53ef\u7528\u4e8e\u6839\u636e\u4e0e\u7535\u6c60\u7684\u7535\u538b\u635f\u5931\u76f8\u5173\u7684\u70ed\u8d28\u91cf\u548c\u4ea7\u751f\u7684\u70ed\u91cf\u6765\u8ba1\u7b97\u7535\u6c60\u7ec4\u7684\u6e29\u5ea6\u5206\u5e03\u3002\n\n\u7528\u6237\u53ef\u4ee5\u66f4\u6539\u5404\u79cd\u7535\u6c60\u7ec4\u8bbe\u8ba1\u53c2\u6570\uff08\u5305\u88c5\u7c7b\u578b\u3001\u7535\u6c60\u6570\u3001\u914d\u7f6e\u3001\u51e0\u4f55\u5f62\u72b6\uff09\u3001\u7535\u6c60\u6750\u6599\u5c5e\u6027\u548c\u5de5\u4f5c\u6761\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("li_battery_pack_designer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    run6(model);
  }

}
