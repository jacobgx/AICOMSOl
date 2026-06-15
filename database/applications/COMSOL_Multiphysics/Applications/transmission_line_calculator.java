/*
 * transmission_line_calculator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class transmission_line_calculator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Applications");

    model.fontSize(6);

    model.param().set("frq_coax", "1[GHz]");
    model.param().descr("frq_coax", "Frequency");
    model.param().set("Ri_coax", "0.405[mm]");
    model.param().descr("Ri_coax", "Inner radius");
    model.param().set("dR_coax", "1.045[mm]");
    model.param().descr("dR_coax", "Dielectric thickness");
    model.param().set("d_s_coax", "0.1[mm]");
    model.param().descr("d_s_coax", "Screen thickness");
    model.param().set("epsr_coax", "2.25");
    model.param().descr("epsr_coax", "Relative permittivity of dielectric");
    model.param().set("mur_coax", "1");
    model.param().descr("mur_coax", "Relative permeability of dielectric");
    model.param().set("sigma_d_coax", "1e-14[S/m]");
    model.param().descr("sigma_d_coax", "Conductivity of dielectric");
    model.param().set("sigma_c_coax", "5.98e7[S/m]");
    model.param().descr("sigma_c_coax", "Conductivity of conductors");
    model.param().set("Ro_coax", "Ri_coax+dR_coax");
    model.param().descr("Ro_coax", "Outer radius");
    model.param().set("w_coax", "2*pi*frq_coax");
    model.param().descr("w_coax", "Angular frequency");
    model.param().set("delta_coax", "sqrt(2/(w_coax*mur_coax*mu0_const*sigma_c_coax))");
    model.param().descr("delta_coax", "Skin depth");
    model.param().set("d_c_coax", "dR_coax");
    model.param().descr("d_c_coax", "Conductor distance");
    model.param().set("lambda_coax", "c_const/frq_coax/sqrt(mur_coax*epsr_coax)");
    model.param().descr("lambda_coax", "Wavelength in dielectric");
    model.param().set("QS_coax", "d_c_coax<(0.1*lambda_coax)");
    model.param().descr("QS_coax", "Validity of quasistatic analysis");
    model.param().set("frq_twin", "300[MHz]");
    model.param().descr("frq_twin", "Frequency");
    model.param().set("R1_twin", "1[mm]");
    model.param().descr("R1_twin", "Wire radius");
    model.param().set("d_twin", "12[mm]");
    model.param().descr("d_twin", "Ribbon width");
    model.param().set("t_ins_twin", "1[mm]");
    model.param().descr("t_ins_twin", "Wire insulation thickness");
    model.param().set("t_ribbon_twin", "t_ins_twin");
    model.param().descr("t_ribbon_twin", "Ribbon thickness");
    model.param().set("epsr_twin", "2.25");
    model.param().descr("epsr_twin", "Relative permittivity of dielectric");
    model.param().set("sigma_d_twin", "1e-14[S/m]");
    model.param().descr("sigma_d_twin", "Conductivity of dielectric");
    model.param().set("sigma_c_twin", "5.98e7[S/m]");
    model.param().descr("sigma_c_twin", "Conductivity of conductors");
    model.param().set("w_twin", "2*pi*frq_twin");
    model.param().descr("w_twin", "Angular frequency");
    model.param().set("delta_twin", "sqrt(2/(w_twin*1*mu0_const*sigma_c_twin))");
    model.param().descr("delta_twin", "Skin depth");
    model.param().set("d_c_twin", "2*t_ins_twin+d_twin");
    model.param().descr("d_c_twin", "Conductor distance");
    model.param().set("lambda_twin", "c_const/frq_twin");
    model.param().descr("lambda_twin", "Wavelength");
    model.param().set("QS_twin", "d_c_twin<(0.1*lambda_twin)");
    model.param().descr("QS_twin", "Validity of quasistatic analysis");
    model.param().set("frq_ms", "10[GHz]");
    model.param().descr("frq_ms", "Frequency");
    model.param().set("W_ms", "0.17[mm]");
    model.param().descr("W_ms", "Strip width");
    model.param().set("t_ms", "0.01[mm]");
    model.param().descr("t_ms", "Strip thickness");
    model.param().set("h_ms", "0.06[mm]");
    model.param().descr("h_ms", "Dielectric height");
    model.param().set("t_gp_ms", "0.01[mm]");
    model.param().descr("t_gp_ms", "Ground plane thickness");
    model.param().set("epsr_ms", "2.25");
    model.param().descr("epsr_ms", "Relative permittivity of dielectric");
    model.param().set("sigma_d_ms", "1e-14[S/m]");
    model.param().descr("sigma_d_ms", "Conductivity of dielectric");
    model.param().set("sigma_c_ms", "5.98e7[S/m]");
    model.param().descr("sigma_c_ms", "Conductivity of conductors");
    model.param().set("w_ms", "2*pi*frq_ms");
    model.param().descr("w_ms", "Angular frequency");
    model.param().set("delta_ms", "sqrt(2/(w_ms*1*mu0_const*sigma_c_ms))");
    model.param().descr("delta_ms", "Skin depth");
    model.param().set("ms_width", "max(5*(t_ms+h_ms+t_gp_ms),10*W_ms)");
    model.param().descr("ms_width", "Domain width");
    model.param().set("ms_height", "max(10*(t_ms+h_ms+t_gp_ms),5*W_ms)");
    model.param().descr("ms_height", "Domain height");
    model.param().set("d_c_ms", "h_ms");
    model.param().descr("d_c_ms", "Conductor distance");
    model.param().set("lambda_ms", "c_const/frq_ms/sqrt(epsr_ms)");
    model.param().descr("lambda_ms", "Wavelength in dielectric");
    model.param().set("QS_ms", "d_c_ms<(0.1*lambda_ms)");
    model.param().descr("QS_ms", "Validity of quasistatic analysis");
    model.param().set("bad_aspect_ratio_ms", "max(h_ms/W_ms,h_ms/t_gp_ms/5)>50");
    model.param().descr("bad_aspect_ratio_ms", "Aspect ratio evaluation");
    model.param().set("frq_cpw", "10[GHz]");
    model.param().descr("frq_cpw", "Frequency");
    model.param().set("W_cpw", "0.17[mm]");
    model.param().descr("W_cpw", "Central track width");
    model.param().set("g_cpw", "0.1[mm]");
    model.param().descr("g_cpw", "Gap to ground width");
    model.param().set("t_cpw", "0.01[mm]");
    model.param().descr("t_cpw", "Track thickness");
    model.param().set("h_cpw", "0.06[mm]");
    model.param().descr("h_cpw", "Dielectric height");
    model.param().set("t_bp_cpw", "0.01[mm]");
    model.param().descr("t_bp_cpw", "Thickness of metallic back plane");
    model.param().set("epsr_cpw", "2.25");
    model.param().descr("epsr_cpw", "Relative permittivity of dielectric");
    model.param().set("sigma_d_cpw", "1e-14[S/m]");
    model.param().descr("sigma_d_cpw", "Conductivity of dielectric");
    model.param().set("sigma_c_cpw", "5.98e7[S/m]");
    model.param().descr("sigma_c_cpw", "Conductivity of conductors");
    model.param().set("w_cpw", "2*pi*frq_cpw");
    model.param().descr("w_cpw", "Angular frequency");
    model.param().set("delta_cpw", "sqrt(2/(w_cpw*1*mu0_const*sigma_c_cpw))");
    model.param().descr("delta_cpw", "Skin depth");
    model.param().set("cpw_width", "max(5*h_cpw,5*(W_cpw+2*g_cpw))");
    model.param().descr("cpw_width", "Domain width");
    model.param().set("cpw_height", "cpw_width/2");
    model.param().descr("cpw_height", "Domain height");
    model.param().set("sigma_bp_cpw", "if(bp_cpw,sigma_c_cpw,0)");
    model.param().descr("sigma_bp_cpw", "Domain conductivity");
    model.param().set("epsilonr_bp_cpw", "1");
    model.param().descr("epsilonr_bp_cpw", "Domain relative permittivity");
    model.param().set("h_bp_cpw", "if(bp_cpw,t_bp_cpw,cpw_height)");
    model.param().descr("h_bp_cpw", "Domain height");
    model.param().set("bp_cpw", "1");
    model.param().descr("bp_cpw", "Conductive back plane switch (1/0)");
    model.param().set("d_c_cpw", "max(g_cpw,h_cpw)");
    model.param().descr("d_c_cpw", "Conductor distance");
    model.param().set("lambda_cpw", "c_const/frq_cpw/sqrt(epsr_cpw)");
    model.param().descr("lambda_cpw", "Wavelength in dielectric");
    model.param().set("QS_cpw", "d_c_cpw<(0.1*lambda_cpw)");
    model.param().descr("QS_cpw", "Validity of quasistatic analysis");
    model.param()
         .set("bad_aspect_ratio_cpw", "(max(max(max(h_cpw/h_bp_cpw/5,h_cpw/t_cpw/5),h_cpw/g_cpw),h_cpw/W_cpw)>50)||((g_cpw/W_cpw)>2)");
    model.param().descr("bad_aspect_ratio_cpw", "Aspect ratio evaluation");
    model.param().set("FR_coax", "delta_coax<d_s_coax");
    model.param().descr("FR_coax", "Validity of reasonable frequency range");
    model.param().set("FR_twin", "delta_twin<R1_twin");
    model.param().descr("FR_twin", "Validity of reasonable frequency range");
    model.param().set("FR_ms", "delta_ms<min(t_gp_ms,t_ms)");
    model.param().descr("FR_ms", "Validity of reasonable frequency range");
    model.param().set("FR_cpw", "delta_ms<min(h_bp_cpw,t_cpw)");
    model.param().descr("FR_cpw", "Validity of reasonable frequency range");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").label("Coaxial Line");

    model.component("comp1").sorder("linear");

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("R_coax", "real(1[V/m]/intop2(mf.Jz))");
    model.component("comp1").variable("var1").descr("R_coax", "Distributed resistance");
    model.component("comp1").variable("var1").set("L_coax", "imag(1[V/m]/intop2(mf.Jz))/w_coax");
    model.component("comp1").variable("var1").descr("L_coax", "Distributed inductance");
    model.component("comp1").variable("var1").set("C_coax", "imag(intop1(reacf(V)*1[A/m])/1[V])/w_coax");
    model.component("comp1").variable("var1").descr("C_coax", "Capacitance");
    model.component("comp1").variable("var1").set("G_coax", "real(intop1(reacf(V)*1[A/m])/1[V])");
    model.component("comp1").variable("var1").descr("G_coax", "Shunt conductance");
    model.component("comp1").variable("var1")
         .set("Zc_coax", "sqrt((R_coax+j*w_coax*L_coax)/(G_coax+j*w_coax*C_coax))");
    model.component("comp1").variable("var1").descr("Zc_coax", "Characteristic impedance");
    model.component("comp1").variable("var1")
         .set("gamma_coax", "sqrt((R_coax+j*w_coax*L_coax)*(G_coax+j*w_coax*C_coax))");
    model.component("comp1").variable("var1").descr("gamma_coax", "Propagation constant");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Ro_coax+d_s_coax");
    model.component("comp1").geom("geom1").feature("c1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("c1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("c1")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "Ro_coax");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "Ro_coax");
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("r", "Ri_coax");
    model.component("comp1").geom("geom1").feature("c4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("c4").set("color", "custom");
    model.component("comp1").geom("geom1").feature("c4")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(5, 6, 9, 10);
    model.component("comp1").cpl("intop1").set("method", "summation");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().set(3);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics("ec").selection().set(2);
    model.component("comp1").physics("ec").feature("cucn1").set("sigma_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn1")
         .set("sigma", new String[]{"sigma_d_coax+j*w_coax*epsr_coax*epsilon0_const", "0", "0", "0", "sigma_d_coax+j*w_coax*epsr_coax*epsilon0_const", "0", "0", "0", "sigma_d_coax+j*w_coax*epsr_coax*epsilon0_const"});
    model.component("comp1").physics("ec").feature("cucn1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn1").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp1").physics("ec").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(3, 4, 8, 11);
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").selection().set(5, 6, 9, 10);
    model.component("comp1").physics("ec").feature("pot1").set("V0", 1);
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp1").physics("mf").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp1").physics("mf").prop("EquationForm").setIndex("freq", "frq_coax", 0);
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").selection().all();
    model.component("comp1").physics("mf").feature("als1").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als1").set("mur_mat", "userdef");
    model.component("comp1").physics("mf").feature("als1")
         .set("mur", new String[]{"mur_coax", "0", "0", "0", "mur_coax", "0", "0", "0", "mur_coax"});
    model.component("comp1").physics("mf").feature("als1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("mf").feature("als1").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als2").selection().set(1, 3);
    model.component("comp1").physics("mf").feature("als2").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als2")
         .set("sigma", new String[]{"sigma_c_coax", "0", "0", "0", "sigma_c_coax", "0", "0", "0", "sigma_c_coax"});
    model.component("comp1").physics("mf").feature("als2").set("mur_mat", "userdef");
    model.component("comp1").physics("mf").feature("als2").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("mf").feature("als2").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp1").physics("mf").create("ecd1", "ExternalCurrentDensity", 2);
    model.component("comp1").physics("mf").feature("ecd1").selection().set(3);
    model.component("comp1").physics("mf").feature("ecd1").set("Je", new String[]{"0", "0", "sigma_c_coax*1[V/m]"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature().move("bl1", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(3, 4, 5, 6, 8, 9, 10, 11);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "delta_coax/2");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").lengthUnit("mm");

    model.component("comp2").label("Twin Lead");

    model.component("comp2").sorder("linear");

    model.component("comp2").variable().create("var2");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").variable("var2").set("R_twin", "real(1[V/m]/intop4(mf2.Jz))");
    model.component("comp2").variable("var2").descr("R_twin", "Distributed resistance");
    model.component("comp2").variable("var2").set("L_twin", "imag(1[V/m]/intop4(mf2.Jz))/w_twin");
    model.component("comp2").variable("var2").descr("L_twin", "Distributed inductance");
    model.component("comp2").variable("var2").set("C_twin", "imag(intop3(reacf(V2)*1[A/m])/1[V])/w_twin");
    model.component("comp2").variable("var2").descr("C_twin", "Capacitance");
    model.component("comp2").variable("var2").set("G_twin", "real(intop3(reacf(V2)*1[A/m])/1[V])");
    model.component("comp2").variable("var2").descr("G_twin", "Shunt conductance");
    model.component("comp2").variable("var2")
         .set("Zc_twin", "sqrt((R_twin+j*w_twin*L_twin)/(G_twin+j*w_twin*C_twin))");
    model.component("comp2").variable("var2").descr("Zc_twin", "Characteristic impedance");
    model.component("comp2").variable("var2")
         .set("gamma_twin", "sqrt((R_twin+j*w_twin*L_twin)*(G_twin+j*w_twin*C_twin))");
    model.component("comp2").variable("var2").descr("gamma_twin", "Propagation constant");

    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1")
         .set("size", new String[]{"max(10*(d_twin+4*(t_ins_twin+R1_twin)), 3*(d_twin+4*(t_ins_twin+R1_twin)))", "1"});
    model.component("comp2").geom("geom2").feature("r1")
         .setIndex("size", "max(6*(d_twin+4*(t_ins_twin+R1_twin)), 5*(d_twin+4*(t_ins_twin+R1_twin)))", 1);
    model.component("comp2").geom("geom2").feature("r1").set("base", "center");
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("c1").set("r", "R1_twin+t_ins_twin");
    model.component("comp2").geom("geom2").feature("c1")
         .set("pos", new String[]{"-(d_twin/2+t_ins_twin+R1_twin)", "0"});
    model.component("comp2").geom("geom2").run("c1");
    model.component("comp2").geom("geom2").create("c2", "Circle");
    model.component("comp2").geom("geom2").feature("c2").set("r", "R1_twin+t_ins_twin");
    model.component("comp2").geom("geom2").feature("c2")
         .set("pos", new String[]{"(d_twin/2+t_ins_twin+R1_twin)", "0"});
    model.component("comp2").geom("geom2").run("c2");
    model.component("comp2").geom("geom2").create("r2", "Rectangle");
    model.component("comp2").geom("geom2").feature("r2").set("size", new String[]{"d_twin+1.9*t_ins_twin", "1"});
    model.component("comp2").geom("geom2").feature("r2").setIndex("size", "t_ribbon_twin", 1);
    model.component("comp2").geom("geom2").feature("r2").set("base", "center");
    model.component("comp2").geom("geom2").run("r2");
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input").set("c1", "c2", "r2");
    model.component("comp2").geom("geom2").feature("uni1").set("intbnd", false);
    model.component("comp2").geom("geom2").run("uni1");
    model.component("comp2").geom("geom2").create("c3", "Circle");
    model.component("comp2").geom("geom2").feature("c3").set("r", "R1_twin");
    model.component("comp2").geom("geom2").feature("c3")
         .set("pos", new String[]{"-(d_twin/2+t_ins_twin+R1_twin)", "0"});
    model.component("comp2").geom("geom2").feature("c3").set("selresult", true);
    model.component("comp2").geom("geom2").feature("c3").set("color", "custom");
    model.component("comp2").geom("geom2").feature("c3")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp2").geom("geom2").run("c3");
    model.component("comp2").geom("geom2").create("c4", "Circle");
    model.component("comp2").geom("geom2").feature("c4").set("r", "R1_twin");
    model.component("comp2").geom("geom2").feature("c4")
         .set("pos", new String[]{"(d_twin/2+t_ins_twin+R1_twin)", "0"});
    model.component("comp2").geom("geom2").feature("c4").set("selresult", true);
    model.component("comp2").geom("geom2").feature("c4").set("color", "custom");
    model.component("comp2").geom("geom2").feature("c4")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("sel1", "ExplicitSelection");
    model.component("comp2").geom("geom2").feature("sel1").selection("selection").set("fin", 1);

    model.component("comp2").view("view2").hideObjects().create("hide1");
    model.component("comp2").view("view2").hideObjects("hide1").init(2);
    model.component("comp2").view("view2").hideObjects("hide1").named("sel1");

    model.component("comp2").cpl().create("intop3", "Integration");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").cpl("intop3").set("axisym", true);
    model.component("comp2").cpl("intop3").selection().geom("geom2", 1);
    model.component("comp2").cpl("intop3").selection().set(17, 18, 20, 21);
    model.component("comp2").cpl("intop3").set("method", "summation");
    model.component("comp2").cpl().create("intop4", "Integration");
    model.component("comp2").cpl("intop4").set("axisym", true);
    model.component("comp2").cpl("intop4").selection().set(4);

    model.component("comp2").physics().create("ec2", "ConductiveMedia", "geom2");
    model.component("comp2").physics("ec2").selection().set(1, 2);
    model.component("comp2").physics("ec2").feature("cucn1").set("sigma_mat", "userdef");
    model.component("comp2").physics("ec2").feature("cucn1")
         .set("sigma", new String[]{"j*w_twin*epsilon0_const", "0", "0", "0", "j*w_twin*epsilon0_const", "0", "0", "0", "j*w_twin*epsilon0_const"});
    model.component("comp2").physics("ec2").feature("cucn1").set("epsilonr_mat", "userdef");
    model.component("comp2").physics("ec2").feature("cucn1").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp2").physics("ec2").create("cucn2", "CurrentConservation", 2);
    model.component("comp2").physics("ec2").feature("cucn2").selection().set(2);
    model.component("comp2").physics("ec2").feature("cucn2").set("sigma_mat", "userdef");
    model.component("comp2").physics("ec2").feature("cucn2")
         .set("sigma", new String[]{"sigma_d_twin+j*w_twin*epsr_twin*epsilon0_const", "0", "0", "0", "sigma_d_twin+j*w_twin*epsr_twin*epsilon0_const", "0", "0", "0", "sigma_d_twin+j*w_twin*epsr_twin*epsilon0_const"});
    model.component("comp2").physics("ec2").feature("cucn2").set("epsilonr_mat", "userdef");
    model.component("comp2").physics("ec2").feature("cucn2").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp2").physics("ec2").create("pot1", "ElectricPotential", 1);
    model.component("comp2").physics("ec2").feature("pot1").selection().set(9, 10, 12, 13);
    model.component("comp2").physics("ec2").feature("pot1").set("V0", "-0.5[V]");
    model.component("comp2").physics("ec2").create("pot2", "ElectricPotential", 1);
    model.component("comp2").physics("ec2").feature("pot2").selection().set(17, 18, 20, 21);
    model.component("comp2").physics("ec2").feature("pot2").set("V0", "0.5[V]");
    model.component("comp2").physics().create("mf2", "InductionCurrents", "geom2");
    model.component("comp2").physics("mf2").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp2").physics("mf2").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp2").physics("mf2").prop("EquationForm").setIndex("freq", "frq_twin", 0);
    model.component("comp2").physics("mf2").create("als1", "AmperesLawSolid", 2);
    model.component("comp2").physics("mf2").feature("als1").selection().all();
    model.component("comp2").physics("mf2").feature("als1").set("sigma_mat", "userdef");
    model.component("comp2").physics("mf2").feature("als1").set("mur_mat", "userdef");
    model.component("comp2").physics("mf2").feature("als1").set("epsilonr_mat", "userdef");
    model.component("comp2").physics("mf2").feature("als1").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp2").physics("mf2").create("als2", "AmperesLawSolid", 2);
    model.component("comp2").physics("mf2").feature("als2").selection().set(2);
    model.component("comp2").physics("mf2").feature("als2").set("sigma_mat", "userdef");
    model.component("comp2").physics("mf2").feature("als2")
         .set("sigma", new String[]{"sigma_d_twin", "0", "0", "0", "sigma_d_twin", "0", "0", "0", "sigma_d_twin"});
    model.component("comp2").physics("mf2").feature("als2").set("mur_mat", "userdef");
    model.component("comp2").physics("mf2").feature("als2").set("epsilonr_mat", "userdef");
    model.component("comp2").physics("mf2").feature("als2")
         .set("epsilonr", new String[]{"epsr_twin", "0", "0", "0", "epsr_twin", "0", "0", "0", "epsr_twin"});
    model.component("comp2").physics("mf2").create("als3", "AmperesLawSolid", 2);
    model.component("comp2").physics("mf2").feature("als3").selection().set(3, 4);
    model.component("comp2").physics("mf2").feature("als3").set("sigma_mat", "userdef");
    model.component("comp2").physics("mf2").feature("als3")
         .set("sigma", new String[]{"sigma_c_twin", "0", "0", "0", "sigma_c_twin", "0", "0", "0", "sigma_c_twin"});
    model.component("comp2").physics("mf2").feature("als3").set("mur_mat", "userdef");
    model.component("comp2").physics("mf2").feature("als3").set("epsilonr_mat", "userdef");
    model.component("comp2").physics("mf2").create("ecd1", "ExternalCurrentDensity", 2);
    model.component("comp2").physics("mf2").feature("ecd1").selection().set(3);
    model.component("comp2").physics("mf2").feature("ecd1")
         .set("Je", new String[]{"0", "0", "-sigma_c_twin*0.5[V/m]"});
    model.component("comp2").physics("mf2").create("ecd2", "ExternalCurrentDensity", 2);
    model.component("comp2").physics("mf2").feature("ecd2").selection().set(4);
    model.component("comp2").physics("mf2").feature("ecd2")
         .set("Je", new String[]{"0", "0", "sigma_c_twin*0.5[V/m]"});

    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("size").set("hauto", 3);
    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "10*(d_twin+4*(t_ins_twin+R1_twin))/50");
    model.component("comp2").mesh("mesh2").feature("size").set("hmin", 2.09E-5);
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom(2);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set(3, 4);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection()
         .set(9, 10, 12, 13, 17, 18, 20, 21);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blhmin", "delta_twin/2");
    model.component("comp2").mesh("mesh2").feature().move("ftri1", 2);

    model.component().create("comp3", true);

    model.component("comp3").geom().create("geom3", 2);

    model.component("comp3").mesh().create("mesh3");

    model.component("comp3").label("Microstrip");

    model.component("comp3").sorder("linear");

    model.component("comp3").geom("geom3").lengthUnit("mm");
    model.component("comp3").geom("geom3").create("r1", "Rectangle");
    model.component("comp3").geom("geom3").feature("r1").set("size", new String[]{"ms_width", "ms_height"});
    model.component("comp3").geom("geom3").run("r1");
    model.component("comp3").geom("geom3").create("r2", "Rectangle");
    model.component("comp3").geom("geom3").feature("r2").set("size", new String[]{"ms_width", "h_ms"});
    model.component("comp3").geom("geom3").run("r2");
    model.component("comp3").geom("geom3").create("r3", "Rectangle");
    model.component("comp3").geom("geom3").feature("r3").set("size", new String[]{"W_ms", "t_ms"});
    model.component("comp3").geom("geom3").feature("r3").set("pos", new String[]{"ms_width/2-W_ms/2", "0"});
    model.component("comp3").geom("geom3").feature("r3").setIndex("pos", "h_ms", 1);
    model.component("comp3").geom("geom3").feature("r3").set("selresult", true);
    model.component("comp3").geom("geom3").feature("r3").set("color", "custom");
    model.component("comp3").geom("geom3").feature("r3")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp3").geom("geom3").run("r3");
    model.component("comp3").geom("geom3").create("r4", "Rectangle");
    model.component("comp3").geom("geom3").feature("r4").set("size", new String[]{"ms_width", "t_gp_ms"});
    model.component("comp3").geom("geom3").feature("r4").set("pos", new String[]{"0", "-t_gp_ms"});
    model.component("comp3").geom("geom3").feature("r4").set("selresult", true);
    model.component("comp3").geom("geom3").feature("r4").set("color", "custom");
    model.component("comp3").geom("geom3").feature("r4")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp3").geom("geom3").run("r4");
    model.component("comp3").geom("geom3").create("r5", "Rectangle");
    model.component("comp3").geom("geom3").feature("r5").set("size", new String[]{"W_ms*2", "h_ms+t_ms+t_gp_ms"});
    model.component("comp3").geom("geom3").feature("r5").set("pos", new String[]{"ms_width/2-W_ms", "0"});
    model.component("comp3").geom("geom3").feature("r5").setIndex("pos", "-t_gp_ms", 1);
    model.component("comp3").geom("geom3").run("fin");
    model.component("comp3").geom("geom3").create("sel1", "ExplicitSelection");
    model.component("comp3").geom("geom3").feature("sel1").selection("selection").set("fin", 1, 2, 3, 6, 8, 9, 10);
    model.component("comp3").geom("geom3").run("sel1");

    model.component("comp3").view("view3").hideObjects().create("hide1");
    model.component("comp3").view("view3").hideObjects("hide1").init(2);
    model.component("comp3").view("view3").hideObjects("hide1").named("sel1");

    model.component("comp3").cpl().create("intop5", "Integration");
    model.component("comp3").cpl("intop5").set("axisym", true);
    model.component("comp3").cpl("intop5").selection().geom("geom3", 1);
    model.component("comp3").cpl("intop5").selection().set(15, 16, 17, 18);
    model.component("comp3").cpl("intop5").set("method", "summation");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp3").cpl().create("intop6", "Integration");
    model.component("comp3").cpl("intop6").set("axisym", true);
    model.component("comp3").cpl("intop6").selection().set(7);

    model.component("comp3").physics().create("ec3", "ConductiveMedia", "geom3");
    model.component("comp3").physics("ec3").selection().set(2, 3, 5, 6, 8, 10);
    model.component("comp3").physics("ec3").feature("cucn1").set("sigma_mat", "userdef");
    model.component("comp3").physics("ec3").feature("cucn1")
         .set("sigma", new String[]{"j*w_ms*epsilon0_const", "0", "0", "0", "j*w_ms*epsilon0_const", "0", "0", "0", "j*w_ms*epsilon0_const"});
    model.component("comp3").physics("ec3").feature("cucn1").set("epsilonr_mat", "userdef");
    model.component("comp3").physics("ec3").feature("cucn1").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp3").physics("ec3").create("pot1", "ElectricPotential", 1);
    model.component("comp3").physics("ec3").feature("pot1").selection().set(15, 16, 17, 18);
    model.component("comp3").physics("ec3").feature("pot1").set("V0", "1[V]");
    model.component("comp3").physics("ec3").create("gnd1", "Ground", 1);
    model.component("comp3").physics("ec3").feature("gnd1").selection().set(4, 11, 24);
    model.component("comp3").physics("ec3").create("cucn2", "CurrentConservation", 2);
    model.component("comp3").physics("ec3").feature("cucn2").selection().set(2, 5, 10);
    model.component("comp3").physics("ec3").feature("cucn2").set("sigma_mat", "userdef");
    model.component("comp3").physics("ec3").feature("cucn2")
         .set("sigma", new String[]{"sigma_d_ms+j*w_ms*epsr_ms*epsilon0_const", "0", "0", "0", "sigma_d_ms+j*w_ms*epsr_ms*epsilon0_const", "0", "0", "0", "sigma_d_ms+j*w_ms*epsr_ms*epsilon0_const"});
    model.component("comp3").physics("ec3").feature("cucn2").set("epsilonr_mat", "userdef");
    model.component("comp3").physics("ec3").feature("cucn2").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp3").physics().create("mf3", "InductionCurrents", "geom3");
    model.component("comp3").physics("mf3").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp3").physics("mf3").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp3").physics("mf3").prop("EquationForm").setIndex("freq", "frq_ms", 0);
    model.component("comp3").physics("mf3").create("als1", "AmperesLawSolid", 2);
    model.component("comp3").physics("mf3").feature("als1").selection().all();
    model.component("comp3").physics("mf3").feature("als1").set("sigma_mat", "userdef");
    model.component("comp3").physics("mf3").feature("als1").set("mur_mat", "userdef");
    model.component("comp3").physics("mf3").feature("als1").set("epsilonr_mat", "userdef");
    model.component("comp3").physics("mf3").feature("als1").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp3").physics("mf3").create("als2", "AmperesLawSolid", 2);
    model.component("comp3").physics("mf3").feature("als2").selection().set(2, 5, 10);
    model.component("comp3").physics("mf3").feature("als2").set("sigma_mat", "userdef");
    model.component("comp3").physics("mf3").feature("als2")
         .set("sigma", new String[]{"sigma_d_ms", "0", "0", "0", "sigma_d_ms", "0", "0", "0", "sigma_d_ms"});
    model.component("comp3").physics("mf3").feature("als2").set("mur_mat", "userdef");
    model.component("comp3").physics("mf3").feature("als2").set("epsilonr_mat", "userdef");
    model.component("comp3").physics("mf3").feature("als2")
         .set("epsilonr", new String[]{"epsr_ms", "0", "0", "0", "epsr_ms", "0", "0", "0", "epsr_ms"});
    model.component("comp3").physics("mf3").create("als3", "AmperesLawSolid", 2);
    model.component("comp3").physics("mf3").feature("als3").selection().set(1, 4, 7, 9);
    model.component("comp3").physics("mf3").feature("als3").set("sigma_mat", "userdef");
    model.component("comp3").physics("mf3").feature("als3")
         .set("sigma", new String[]{"sigma_c_ms", "0", "0", "0", "sigma_c_ms", "0", "0", "0", "sigma_c_ms"});
    model.component("comp3").physics("mf3").feature("als3").set("mur_mat", "userdef");
    model.component("comp3").physics("mf3").feature("als3").set("epsilonr_mat", "userdef");
    model.component("comp3").physics("mf3").create("ecd1", "ExternalCurrentDensity", 2);
    model.component("comp3").physics("mf3").feature("ecd1").selection().set(7);
    model.component("comp3").physics("mf3").feature("ecd1").set("Je", new String[]{"0", "0", "sigma_c_ms*1[V/m]"});
    model.component("comp3").physics("mf3").create("pmc1", "PerfectMagneticConductor", 1);
    model.component("comp3").physics("mf3").feature("pmc1").selection().set(2, 9, 22);

    model.component("comp3").variable().create("var3");
    model.component("comp3").variable("var3").set("R_ms", "real(1[V/m]/intop6(mf3.Jz))");
    model.component("comp3").variable("var3").descr("R_ms", "Distributed resistance");
    model.component("comp3").variable("var3").set("L_ms", "imag(1[V/m]/intop6(mf3.Jz))/w_ms");
    model.component("comp3").variable("var3").descr("L_ms", "Distributed inductance");
    model.component("comp3").variable("var3").set("C_ms", "imag(intop5(reacf(V3)*1[A/m])/1[V])/w_ms");
    model.component("comp3").variable("var3").descr("C_ms", "Capacitance");
    model.component("comp3").variable("var3").set("G_ms", "real(intop5(reacf(V3)*1[A/m])/1[V])");
    model.component("comp3").variable("var3").descr("G_ms", "Shunt conductance");
    model.component("comp3").variable("var3").set("Zc_ms", "sqrt((R_ms+j*w_ms*L_ms)/(G_ms+j*w_ms*C_ms))");
    model.component("comp3").variable("var3").descr("Zc_ms", "Characteristic impedance");
    model.component("comp3").variable("var3").set("gamma_ms", "sqrt((R_ms+j*w_ms*L_ms)*(G_ms+j*w_ms*C_ms))");
    model.component("comp3").variable("var3").descr("gamma_ms", "Propagation constant");

    model.component("comp3").mesh("mesh3").automatic(false);
    model.component("comp3").mesh("mesh3").feature("size").set("hmax", "min(ms_width/25,h_ms/2)");
    model.component("comp3").mesh("mesh3").feature("size").set("hmin", 5.1E-7);
    model.component("comp3").mesh("mesh3").create("size1", "Size");
    model.component("comp3").mesh("mesh3").feature("size1").selection().geom("geom3", 2);
    model.component("comp3").mesh("mesh3").feature("size1").selection().set(1, 4, 7, 9);
    model.component("comp3").mesh("mesh3").feature("size1").set("hauto", 9);
    model.component("comp3").mesh("mesh3").feature().move("size1", 1);
    model.component("comp3").mesh("mesh3").create("bl1", "BndLayer");
    model.component("comp3").mesh("mesh3").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp3").mesh("mesh3").feature("bl1").selection().geom(2);
    model.component("comp3").mesh("mesh3").feature("bl1").selection().set();
    model.component("comp3").mesh("mesh3").feature("bl1").selection().allGeom();
    model.component("comp3").mesh("mesh3").feature("bl1").selection().geom("geom3", 2);
    model.component("comp3").mesh("mesh3").feature("bl1").selection().set(1, 4, 9);
    model.component("comp3").mesh("mesh3").feature("bl1").feature("blp").selection().set(4, 11, 24);
    model.component("comp3").mesh("mesh3").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp3").mesh("mesh3").feature("bl1").feature("blp").set("blhmin", "min(delta_ms/2,t_gp_ms/10)");
    model.component("comp3").mesh("mesh3").create("bl2", "BndLayer");
    model.component("comp3").mesh("mesh3").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp3").mesh("mesh3").feature("bl2").selection().geom("geom3", 2);
    model.component("comp3").mesh("mesh3").feature("bl2").selection().set(7);
    model.component("comp3").mesh("mesh3").feature("bl2").feature("blp").selection().set(15, 16, 17, 18);
    model.component("comp3").mesh("mesh3").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp3").mesh("mesh3").feature("bl2").feature("blp").set("blhmin", "min(delta_ms/2,t_ms/10)");

    model.component().create("comp4", true);

    model.component("comp4").geom().create("geom4", 2);

    model.component("comp4").mesh().create("mesh4");

    model.component("comp4").label("Coplanar Waveguide");

    model.component("comp4").sorder("linear");

    model.component("comp4").variable().create("var4");

    model.component("comp4").geom("geom4").run();

    model.component("comp4").variable("var4").set("R_cpw", "real(1[V/m]/intop8(mf4.Jz))");
    model.component("comp4").variable("var4").descr("R_cpw", "Distributed resistance");
    model.component("comp4").variable("var4").set("L_cpw", "imag(1[V/m]/intop8(mf4.Jz))/w_cpw");
    model.component("comp4").variable("var4").descr("L_cpw", "Distributed inductance");
    model.component("comp4").variable("var4").set("C_cpw", "imag(intop7(reacf(V4)*1[A/m])/1[V])/w_cpw");
    model.component("comp4").variable("var4").descr("C_cpw", "Capacitance");
    model.component("comp4").variable("var4").set("G_cpw", "real(intop7(reacf(V4)*1[A/m])/1[V])");
    model.component("comp4").variable("var4").descr("G_cpw", "Shunt conductance");
    model.component("comp4").variable("var4").set("Zc_cpw", "sqrt((R_cpw+j*w_cpw*L_cpw)/(G_cpw+j*w_cpw*C_cpw))");
    model.component("comp4").variable("var4").descr("Zc_cpw", "Characteristic impedance");
    model.component("comp4").variable("var4").set("gamma_cpw", "sqrt((R_cpw+j*w_cpw*L_cpw)*(G_cpw+j*w_cpw*C_cpw))");
    model.component("comp4").variable("var4").descr("gamma_cpw", "Propagation constant");

    model.component("comp4").geom("geom4").lengthUnit("mm");
    model.component("comp4").geom("geom4").create("r1", "Rectangle");
    model.component("comp4").geom("geom4").feature("r1").set("size", new String[]{"cpw_width", "cpw_height"});
    model.component("comp4").geom("geom4").feature("r1").set("pos", new String[]{"-cpw_width/2", "0"});
    model.component("comp4").geom("geom4").feature("r1").label("Air");
    model.component("comp4").geom("geom4").run("r1");
    model.component("comp4").geom("geom4").create("r2", "Rectangle");
    model.component("comp4").geom("geom4").feature("r2").set("size", new String[]{"W_cpw", "t_cpw"});
    model.component("comp4").geom("geom4").feature("r2").set("pos", new String[]{"-W_cpw/2", "0"});
    model.component("comp4").geom("geom4").feature("r2").label("Track");
    model.component("comp4").geom("geom4").feature("r2").set("selresult", true);
    model.component("comp4").geom("geom4").feature("r2").set("color", "custom");
    model.component("comp4").geom("geom4").feature("r2")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp4").geom("geom4").run("r2");
    model.component("comp4").geom("geom4").create("r3", "Rectangle");
    model.component("comp4").geom("geom4").feature("r3")
         .set("size", new String[]{"(cpw_width-W_cpw-2*g_cpw)/2", "1"});
    model.component("comp4").geom("geom4").feature("r3").setIndex("size", "t_cpw", 1);
    model.component("comp4").geom("geom4").feature("r3").set("pos", new String[]{"-cpw_width/2", "0"});
    model.component("comp4").geom("geom4").feature("r3").label("Left GP");
    model.component("comp4").geom("geom4").feature("r3").set("selresult", true);
    model.component("comp4").geom("geom4").feature("r3").set("color", "custom");
    model.component("comp4").geom("geom4").feature("r3")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp4").geom("geom4").run("r3");
    model.component("comp4").geom("geom4").create("r4", "Rectangle");
    model.component("comp4").geom("geom4").feature("r4")
         .set("size", new String[]{"(cpw_width-W_cpw-2*g_cpw)/2", "1"});
    model.component("comp4").geom("geom4").feature("r4").setIndex("size", "t_cpw", 1);
    model.component("comp4").geom("geom4").feature("r4").set("pos", new String[]{"W_cpw/2+g_cpw", "0"});
    model.component("comp4").geom("geom4").feature("r4").label("Right GP");
    model.component("comp4").geom("geom4").feature("r4").set("selresult", true);
    model.component("comp4").geom("geom4").feature("r4").set("color", "custom");
    model.component("comp4").geom("geom4").feature("r4")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp4").geom("geom4").run("r4");
    model.component("comp4").geom("geom4").create("r5", "Rectangle");
    model.component("comp4").geom("geom4").feature("r5").set("size", new String[]{"cpw_width", "h_cpw"});
    model.component("comp4").geom("geom4").feature("r5").set("pos", new String[]{"-cpw_width/2", "0"});
    model.component("comp4").geom("geom4").feature("r5").setIndex("pos", "-h_cpw", 1);
    model.component("comp4").geom("geom4").feature("r5").label("Dielectric");
    model.component("comp4").geom("geom4").run("r5");
    model.component("comp4").geom("geom4").create("r6", "Rectangle");
    model.component("comp4").geom("geom4").feature("r6").set("size", new String[]{"cpw_width", "h_bp_cpw"});
    model.component("comp4").geom("geom4").feature("r6").set("pos", new String[]{"-cpw_width/2", "0"});
    model.component("comp4").geom("geom4").feature("r6").setIndex("pos", "-h_cpw-h_bp_cpw", 1);
    model.component("comp4").geom("geom4").feature("r6").label("Backplane/Bottom Domain");
    model.component("comp4").geom("geom4").feature("r6").set("selresult", true);
    model.component("comp4").geom("geom4").feature("r6").set("color", "custom");
    model.component("comp4").geom("geom4").feature("r6")
         .set("customcolor", new double[]{0.21960784494876862, 0.501960813999176, 0.5647059082984924});
    model.component("comp4").geom("geom4").run("r6");
    model.component("comp4").geom("geom4").create("r7", "Rectangle");
    model.component("comp4").geom("geom4").feature("r7")
         .set("size", new String[]{"g_cpw*2+W_cpw+((cpw_width-W_cpw-2*g_cpw)/2)*0.2", "1"});
    model.component("comp4").geom("geom4").feature("r7").setIndex("size", "h_cpw+h_bp_cpw+t_cpw", 1);
    model.component("comp4").geom("geom4").feature("r7")
         .set("pos", new String[]{"-cpw_width/2+(cpw_width-W_cpw-2*g_cpw)/2-((cpw_width-W_cpw-2*g_cpw)/2)*0.1", "0"});
    model.component("comp4").geom("geom4").feature("r7").setIndex("pos", "-h_cpw-h_bp_cpw", 1);
    model.component("comp4").geom("geom4").feature("r7").set("type", "curve");
    model.component("comp4").geom("geom4").run("fin");
    model.component("comp4").geom("geom4").create("sel1", "ExplicitSelection");
    model.component("comp4").geom("geom4").feature("sel1").selection("selection")
         .set("fin", 1, 2, 3, 4, 8, 10, 12, 13, 14);

    model.component("comp4").view("view4").hideObjects().create("hide1");
    model.component("comp4").view("view4").hideObjects("hide1").init(2);
    model.component("comp4").view("view4").hideObjects("hide1").named("sel1");

    model.component("comp4").cpl().create("intop7", "Integration");

    model.component("comp4").geom("geom4").run();

    model.component("comp4").cpl("intop7").set("axisym", true);
    model.component("comp4").cpl("intop7").selection().geom("geom4", 1);
    model.component("comp4").cpl("intop7").selection().set(20, 21, 22, 23);
    model.component("comp4").cpl("intop7").set("method", "summation");
    model.component("comp4").cpl().create("intop8", "Integration");
    model.component("comp4").cpl("intop8").set("axisym", true);
    model.component("comp4").cpl("intop8").selection().set(9);

    model.component("comp4").physics().create("ec4", "ConductiveMedia", "geom4");
    model.component("comp4").physics("ec4").selection().set(2, 4, 6, 8, 10, 13);
    model.component("comp4").physics("ec4").feature("cucn1").set("sigma_mat", "userdef");
    model.component("comp4").physics("ec4").feature("cucn1")
         .set("sigma", new String[]{"j*w_cpw*epsilon0_const", "0", "0", "0", "j*w_cpw*epsilon0_const", "0", "0", "0", "j*w_cpw*epsilon0_const"});
    model.component("comp4").physics("ec4").feature("cucn1").set("epsilonr_mat", "userdef");
    model.component("comp4").physics("ec4").feature("cucn1").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp4").physics("ec4").create("cucn2", "CurrentConservation", 2);
    model.component("comp4").physics("ec4").feature("cucn2").selection().set(2, 6, 13);
    model.component("comp4").physics("ec4").feature("cucn2").set("sigma_mat", "userdef");
    model.component("comp4").physics("ec4").feature("cucn2")
         .set("sigma", new String[]{"sigma_d_cpw+j*w_cpw*epsr_cpw*epsilon0_const", "0", "0", "0", "sigma_d_cpw+j*w_cpw*epsr_cpw*epsilon0_const", "0", "0", "0", "sigma_d_cpw+j*w_cpw*epsr_cpw*epsilon0_const"});
    model.component("comp4").physics("ec4").feature("cucn2").set("epsilonr_mat", "userdef");
    model.component("comp4").physics("ec4").feature("cucn2").set("epsilonr", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp4").physics("ec4").create("pot1", "ElectricPotential", 1);
    model.component("comp4").physics("ec4").feature("pot1").selection().set(20, 21, 22, 23);
    model.component("comp4").physics("ec4").feature("pot1").set("V0", 1);
    model.component("comp4").physics("ec4").create("gnd1", "Ground", 1);
    model.component("comp4").physics("ec4").feature("gnd1").selection().set(6, 8, 15, 16, 17, 26, 27, 28, 34, 35);
    model.component("comp4").physics("ec4").create("gnd2", "Ground", 1);
    model.component("comp4").physics("ec4").feature("gnd2").selection().set(4, 13, 32);
    model.component("comp4").physics().create("mf4", "InductionCurrents", "geom4");
    model.component("comp4").physics("mf4").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp4").physics("mf4").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp4").physics("mf4").prop("EquationForm").setIndex("freq", "frq_cpw", 0);
    model.component("comp4").physics("mf4").create("als1", "AmperesLawSolid", 2);
    model.component("comp4").physics("mf4").feature("als1").selection().all();
    model.component("comp4").physics("mf4").feature("als1").set("sigma_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als1").set("mur_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als1").set("epsilonr_mat", "userdef");
    model.component("comp4").physics("mf4").create("als2", "AmperesLawSolid", 2);
    model.component("comp4").physics("mf4").feature("als2").selection().set(3, 7, 9, 11, 14);
    model.component("comp4").physics("mf4").feature("als2").set("sigma_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als2")
         .set("sigma", new String[]{"sigma_c_cpw", "0", "0", "0", "sigma_c_cpw", "0", "0", "0", "sigma_c_cpw"});
    model.component("comp4").physics("mf4").feature("als2").set("mur_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als2").set("epsilonr_mat", "userdef");
    model.component("comp4").physics("mf4").create("als3", "AmperesLawSolid", 2);
    model.component("comp4").physics("mf4").feature("als3").selection().set(1, 5, 12);
    model.component("comp4").physics("mf4").feature("als3").set("sigma_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als3")
         .set("sigma", new String[]{"sigma_bp_cpw", "0", "0", "0", "sigma_bp_cpw", "0", "0", "0", "sigma_bp_cpw"});
    model.component("comp4").physics("mf4").feature("als3").set("mur_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als3").set("epsilonr_mat", "userdef");
    model.component("comp4").physics("mf4").create("als4", "AmperesLawSolid", 2);
    model.component("comp4").physics("mf4").feature("als4").selection().set(2, 6, 13);
    model.component("comp4").physics("mf4").feature("als4").set("sigma_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als4")
         .set("sigma", new String[]{"sigma_d_cpw", "0", "0", "0", "sigma_d_cpw", "0", "0", "0", "sigma_d_cpw"});
    model.component("comp4").physics("mf4").feature("als4").set("mur_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als4").set("epsilonr_mat", "userdef");
    model.component("comp4").physics("mf4").feature("als4")
         .set("epsilonr", new String[]{"epsr_cpw", "0", "0", "0", "epsr_cpw", "0", "0", "0", "epsr_cpw"});
    model.component("comp4").physics("mf4").create("ecd1", "ExternalCurrentDensity", 2);
    model.component("comp4").physics("mf4").feature("ecd1").selection().set(9);
    model.component("comp4").physics("mf4").feature("ecd1").set("Je", new String[]{"0", "0", "sigma_c_cpw*1[V/m]"});
    model.component("comp4").physics("mf4").create("pmc1", "PerfectMagneticConductor", 1);
    model.component("comp4").physics("mf4").feature("pmc1").selection().set(2, 11, 30);

    model.component("comp4").mesh("mesh4").automatic(false);
    model.component("comp4").mesh("mesh4").feature("size").set("custom", true);
    model.component("comp4").mesh("mesh4").feature("size").set("hmax", "min(cpw_width/25,h_cpw/2)");
    model.component("comp4").mesh("mesh4").feature("size").set("hmin", 5.55E-7);
    model.component("comp4").mesh("mesh4").create("size1", "Size");
    model.component("comp4").mesh("mesh4").feature("size1").selection().geom("geom4", 2);
    model.component("comp4").mesh("mesh4").feature("size1").selection().set(1, 3, 5, 7, 9, 11, 12, 14);
    model.component("comp4").mesh("mesh4").feature("size1").set("hauto", 9);
    model.component("comp4").mesh("mesh4").feature("size1").set("custom", true);
    model.component("comp4").mesh("mesh4").feature("size1").set("hmaxactive", true);
    model.component("comp4").mesh("mesh4").feature("size1").set("hmax", "min(cpw_width/25,h_cpw/2)");
    model.component("comp4").mesh("mesh4").feature().move("size1", 1);
    model.component("comp4").mesh("mesh4").create("bl1", "BndLayer");
    model.component("comp4").mesh("mesh4").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp4").mesh("mesh4").feature("bl1").selection().geom(2);
    model.component("comp4").mesh("mesh4").feature("bl1").selection().set();
    model.component("comp4").mesh("mesh4").feature("bl1").selection().allGeom();
    model.component("comp4").mesh("mesh4").feature("bl1").selection().geom("geom4", 2);
    model.component("comp4").mesh("mesh4").feature("bl1").selection().set(9);
    model.component("comp4").mesh("mesh4").feature("bl1").feature("blp").selection().set(20, 21, 22, 23);
    model.component("comp4").mesh("mesh4").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp4").mesh("mesh4").feature("bl1").feature("blp").set("blhmin", "min(delta_cpw/2,t_cpw/10)");
    model.component("comp4").mesh("mesh4").create("bl2", "BndLayer");
    model.component("comp4").mesh("mesh4").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp4").mesh("mesh4").feature("bl2").selection().geom("geom4", 2);
    model.component("comp4").mesh("mesh4").feature("bl2").selection().set(3, 7, 11, 14);
    model.component("comp4").mesh("mesh4").feature("bl2").feature("blp").selection()
         .set(6, 8, 15, 16, 17, 26, 27, 28, 34, 35);
    model.component("comp4").mesh("mesh4").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp4").mesh("mesh4").feature("bl2").feature("blp").set("blhmin", "min(delta_cpw/2,t_cpw/10)");
    model.component("comp4").mesh("mesh4").create("bl3", "BndLayer");
    model.component("comp4").mesh("mesh4").feature("bl3").create("blp", "BndLayerProp");
    model.component("comp4").mesh("mesh4").feature("bl3").selection().geom("geom4", 2);
    model.component("comp4").mesh("mesh4").feature("bl3").selection().set(1, 5, 12);
    model.component("comp4").mesh("mesh4").feature("bl3").feature("blp").selection().set(4, 13, 32);
    model.component("comp4").mesh("mesh4").feature("bl3").feature("blp").set("inittype", "blhmin");
    model.component("comp4").mesh("mesh4").feature("bl3").feature("blp")
         .set("blhmin", "min(delta_cpw/2,t_bp_cpw/10)");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mf2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec3", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mf3", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec4", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mf4", true);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ec2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mf2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ec3", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mf3", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ec4", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mf4", true);
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std3").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ec2", true);
    model.study("std3").feature("stat").setSolveFor("/physics/mf2", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ec3", true);
    model.study("std3").feature("stat").setSolveFor("/physics/mf3", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ec4", true);
    model.study("std3").feature("stat").setSolveFor("/physics/mf4", true);
    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std4").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ec2", true);
    model.study("std4").feature("stat").setSolveFor("/physics/mf2", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ec3", true);
    model.study("std4").feature("stat").setSolveFor("/physics/mf3", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ec4", true);
    model.study("std4").feature("stat").setSolveFor("/physics/mf4", true);
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").feature("stat").setSolveFor("/physics/ec2", false);
    model.study("std1").feature("stat").setSolveFor("/physics/mf2", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ec3", false);
    model.study("std1").feature("stat").setSolveFor("/physics/mf3", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ec4", false);
    model.study("std1").feature("stat").setSolveFor("/physics/mf4", false);
    model.study("std1").feature("stat").setEntry("mesh", "geom2", "nomesh");
    model.study("std1").feature("stat").setEntry("mesh", "geom3", "nomesh");
    model.study("std1").feature("stat").setEntry("mesh", "geom4", "nomesh");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std2").feature("stat").setSolveFor("/physics/mf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ec3", false);
    model.study("std2").feature("stat").setSolveFor("/physics/mf3", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ec4", false);
    model.study("std2").feature("stat").setSolveFor("/physics/mf4", false);
    model.study("std2").feature("stat").setEntry("mesh", "geom1", "nomesh");
    model.study("std2").feature("stat").setEntry("mesh", "geom3", "nomesh");
    model.study("std2").feature("stat").setEntry("mesh", "geom4", "nomesh");
    model.study("std3").setGenPlots(false);
    model.study("std3").setGenConv(false);
    model.study("std3").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std3").feature("stat").setSolveFor("/physics/mf", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ec2", false);
    model.study("std3").feature("stat").setSolveFor("/physics/mf2", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ec4", false);
    model.study("std3").feature("stat").setSolveFor("/physics/mf4", false);
    model.study("std3").feature("stat").setEntry("mesh", "geom1", "nomesh");
    model.study("std3").feature("stat").setEntry("mesh", "geom2", "nomesh");
    model.study("std3").feature("stat").setEntry("mesh", "geom4", "nomesh");
    model.study("std4").setGenPlots(false);
    model.study("std4").setGenConv(false);
    model.study("std4").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std4").feature("stat").setSolveFor("/physics/mf", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ec2", false);
    model.study("std4").feature("stat").setSolveFor("/physics/mf2", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ec3", false);
    model.study("std4").feature("stat").setSolveFor("/physics/mf3", false);
    model.study("std4").feature("stat").setEntry("mesh", "geom1", "nomesh");
    model.study("std4").feature("stat").setEntry("mesh", "geom2", "nomesh");
    model.study("std4").feature("stat").setEntry("mesh", "geom3", "nomesh");
    model.study("std1").showAutoSequences("all");
    model.study("std2").showAutoSequences("all");
    model.study("std3").showAutoSequences("all");
    model.study("std4").showAutoSequences("all");

    model.result().dataset().remove("dset5");
    model.result().dataset().remove("dset6");
    model.result().dataset().remove("dset7");
    model.result().dataset().remove("dset8");
    model.result().dataset().remove("dset9");
    model.result().dataset().remove("dset10");
    model.result().dataset().remove("dset11");
    model.result().dataset().remove("dset12");
    model.result().dataset().remove("dset13");
    model.result().dataset().remove("dset14");
    model.result().dataset().remove("dset15");
    model.result().dataset().remove("dset16");
    model.result().dataset("dset2").set("solution", "sol2");
    model.result().dataset("dset3").set("solution", "sol3");
    model.result().dataset("dset4").set("solution", "sol4");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").setIndex("genpoints", "-3*(d_twin+4*(t_ins_twin+R1_twin))", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "3*(d_twin+4*(t_ins_twin+R1_twin))", 1, 1);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Coaxial Resistance");
    model.result().numerical("gev1").setIndex("expr", "R_coax", 0);
    model.result().numerical("gev1").setIndex("unit", "\u03a9/m", 0);
    model.result().numerical("gev1").setIndex("descr", "Distributed resistance", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Coaxial Resistance");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    return model;
  }

  public static Model run3(Model model) {
    model.result().table("tbl1").label("Coaxial");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("Coaxial Inductance");
    model.result().numerical("gev2").setIndex("expr", "L_coax", 0);
    model.result().numerical("gev2").setIndex("unit", "H/m", 0);
    model.result().numerical("gev2").setIndex("descr", "Distributed inductance", 0);
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("Coaxial Conductance");
    model.result().numerical("gev3").setIndex("expr", "G_coax", 0);
    model.result().numerical("gev3").setIndex("unit", "S/m", 0);
    model.result().numerical("gev3").setIndex("descr", "Shunt conductance", 0);
    model.result().numerical("gev3").set("table", "tbl1");
    model.result().numerical("gev3").setResult();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("Coaxial Capacitance");
    model.result().numerical("gev4").setIndex("expr", "C_coax", 0);
    model.result().numerical("gev4").setIndex("unit", "F/m", 0);
    model.result().numerical("gev4").setIndex("descr", "Capacitance", 0);
    model.result().numerical("gev4").set("table", "tbl1");
    model.result().numerical("gev4").setResult();
    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").label("Coaxial Characteristic Impedance");
    model.result().numerical("gev5").setIndex("expr", "Zc_coax", 0);
    model.result().numerical("gev5").setIndex("unit", "\u03a9", 0);
    model.result().numerical("gev5").setIndex("descr", "Characteristic impedance", 0);
    model.result().numerical("gev5").set("table", "tbl1");
    model.result().numerical("gev5").setResult();
    model.result().numerical().create("gev6", "EvalGlobal");
    model.result().numerical("gev6").label("Coaxial Propagation Constant");
    model.result().numerical("gev6").setIndex("expr", "gamma_coax", 0);
    model.result().numerical("gev6").setIndex("unit", "1/m", 0);
    model.result().numerical("gev6").setIndex("descr", "Propagation constant", 0);
    model.result().numerical("gev6").set("table", "tbl1");
    model.result().numerical("gev6").setResult();
    model.result().numerical().create("gev7", "EvalGlobal");
    model.result().numerical("gev7").label("Twin Lead Resistance");
    model.result().numerical("gev7").set("data", "dset2");
    model.result().numerical("gev7").setIndex("expr", "R_twin", 0);
    model.result().numerical("gev7").setIndex("unit", "\u03a9/m", 0);
    model.result().numerical("gev7").setIndex("descr", "Distributed resistance", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Twin Lead Resistance");
    model.result().numerical("gev7").set("table", "tbl2");
    model.result().numerical("gev7").setResult();
    model.result().table("tbl2").label("Twin Lead");
    model.result().numerical().create("gev8", "EvalGlobal");
    model.result().numerical("gev8").label("Twin Lead Inductance");
    model.result().numerical("gev8").set("data", "dset2");
    model.result().numerical("gev8").setIndex("expr", "L_twin", 0);
    model.result().numerical("gev8").setIndex("unit", "H/m", 0);
    model.result().numerical("gev8").setIndex("descr", "Distributed inductance", 0);
    model.result().numerical("gev8").set("table", "tbl2");
    model.result().numerical("gev8").setResult();
    model.result().numerical().create("gev9", "EvalGlobal");
    model.result().numerical("gev9").label("Twin Lead Conductance");
    model.result().numerical("gev9").set("data", "dset2");
    model.result().numerical("gev9").setIndex("expr", "G_twin", 0);
    model.result().numerical("gev9").setIndex("unit", "S/m", 0);
    model.result().numerical("gev9").setIndex("descr", "Shunt conductance", 0);
    model.result().numerical("gev9").set("table", "tbl2");
    model.result().numerical("gev9").setResult();
    model.result().numerical().create("gev10", "EvalGlobal");
    model.result().numerical("gev10").label("Twin Lead Capacitance");
    model.result().numerical("gev10").set("data", "dset2");
    model.result().numerical("gev10").setIndex("expr", "C_twin", 0);
    model.result().numerical("gev10").setIndex("unit", "F/m", 0);
    model.result().numerical("gev10").setIndex("descr", "Capacitance", 0);
    model.result().numerical("gev10").set("table", "tbl2");
    model.result().numerical("gev10").setResult();
    model.result().numerical().create("gev11", "EvalGlobal");
    model.result().numerical("gev11").label("Twin Lead Characteristic Impedance");
    model.result().numerical("gev11").set("data", "dset2");
    model.result().numerical("gev11").setIndex("expr", "Zc_twin", 0);
    model.result().numerical("gev11").setIndex("unit", "\u03a9", 0);
    model.result().numerical("gev11").setIndex("descr", "Characteristic impedance", 0);
    model.result().numerical("gev11").set("table", "tbl2");
    model.result().numerical("gev11").setResult();
    model.result().numerical().create("gev12", "EvalGlobal");
    model.result().numerical("gev12").label("Twin Lead Propagation Constant");
    model.result().numerical("gev12").set("data", "dset2");
    model.result().numerical("gev12").setIndex("expr", "gamma_twin", 0);
    model.result().numerical("gev12").setIndex("unit", "1/m", 0);
    model.result().numerical("gev12").setIndex("descr", "Propagation constant", 0);
    model.result().numerical("gev12").set("table", "tbl2");
    model.result().numerical("gev12").setResult();
    model.result().numerical().create("gev13", "EvalGlobal");
    model.result().numerical("gev13").label("Microstrip Resistance");
    model.result().numerical("gev13").set("data", "dset3");
    model.result().numerical("gev13").setIndex("expr", "R_ms", 0);
    model.result().numerical("gev13").setIndex("unit", "\u03a9/m", 0);
    model.result().numerical("gev13").setIndex("descr", "Distributed resistance", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Microstrip Resistance");
    model.result().numerical("gev13").set("table", "tbl3");
    model.result().numerical("gev13").setResult();
    model.result().table("tbl3").label("Microstrip");
    model.result().numerical().create("gev14", "EvalGlobal");
    model.result().numerical("gev14").label("Microstrip Inductance");
    model.result().numerical("gev14").set("data", "dset3");
    model.result().numerical("gev14").setIndex("expr", "L_ms", 0);
    model.result().numerical("gev14").setIndex("unit", "H/m", 0);
    model.result().numerical("gev14").setIndex("descr", "Distributed inductance", 0);
    model.result().numerical("gev14").set("table", "tbl3");
    model.result().numerical("gev14").setResult();
    model.result().numerical().create("gev15", "EvalGlobal");
    model.result().numerical("gev15").label("Microstrip Conductance");
    model.result().numerical("gev15").set("data", "dset3");
    model.result().numerical("gev15").setIndex("expr", "G_ms", 0);
    model.result().numerical("gev15").setIndex("unit", "S/m", 0);
    model.result().numerical("gev15").setIndex("descr", "Shunt conductance", 0);
    model.result().numerical("gev15").set("table", "tbl3");
    model.result().numerical("gev15").setResult();
    model.result().numerical().create("gev16", "EvalGlobal");
    model.result().numerical("gev16").label("Microstrip Capacitance");
    model.result().numerical("gev16").set("data", "dset3");
    model.result().numerical("gev16").setIndex("expr", "C_ms", 0);
    model.result().numerical("gev16").setIndex("unit", "F/m", 0);
    model.result().numerical("gev16").setIndex("descr", "Capacitance", 0);
    model.result().numerical("gev16").set("table", "tbl3");
    model.result().numerical("gev16").setResult();
    model.result().numerical().create("gev17", "EvalGlobal");
    model.result().numerical("gev17").label("Microstrip Characteristic Impedance");
    model.result().numerical("gev17").set("data", "dset3");
    model.result().numerical("gev17").setIndex("expr", "Zc_ms", 0);
    model.result().numerical("gev17").setIndex("unit", "\u03a9", 0);
    model.result().numerical("gev17").setIndex("descr", "Characteristic impedance", 0);
    model.result().numerical("gev17").set("table", "tbl3");
    model.result().numerical("gev17").setResult();
    model.result().numerical().create("gev18", "EvalGlobal");
    model.result().numerical("gev18").label("Microstrip Propagation Constant");
    model.result().numerical("gev18").set("data", "dset3");
    model.result().numerical("gev18").setIndex("expr", "gamma_ms", 0);
    model.result().numerical("gev18").setIndex("unit", "1/m", 0);
    model.result().numerical("gev18").setIndex("descr", "Propagation constant", 0);
    model.result().numerical("gev18").set("table", "tbl3");
    model.result().numerical("gev18").setResult();
    model.result().numerical().create("gev19", "EvalGlobal");
    model.result().numerical("gev19").label("Coplanar Waveguide Resistance");
    model.result().numerical("gev19").set("data", "dset4");
    model.result().numerical("gev19").setIndex("expr", "R_cpw", 0);
    model.result().numerical("gev19").setIndex("unit", "\u03a9/m", 0);
    model.result().numerical("gev19").setIndex("descr", "Distributed resistance", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("Coplanar Waveguide Resistance");
    model.result().numerical("gev19").set("table", "tbl4");
    model.result().numerical("gev19").setResult();
    model.result().table("tbl4").label("Coplanar Waveguide");
    model.result().numerical().create("gev20", "EvalGlobal");
    model.result().numerical("gev20").label("Coplanar Waveguide Inductance");
    model.result().numerical("gev20").set("data", "dset4");
    model.result().numerical("gev20").setIndex("expr", "L_cpw", 0);
    model.result().numerical("gev20").setIndex("unit", "H/m", 0);
    model.result().numerical("gev20").setIndex("descr", "Distributed inductance", 0);
    model.result().numerical("gev20").set("table", "tbl4");
    model.result().numerical("gev20").setResult();
    model.result().numerical().create("gev21", "EvalGlobal");
    model.result().numerical("gev21").label("Coplanar Waveguide Conductance");
    model.result().numerical("gev21").set("data", "dset4");
    model.result().numerical("gev21").setIndex("expr", "G_cpw", 0);
    model.result().numerical("gev21").setIndex("unit", "S/m", 0);
    model.result().numerical("gev21").setIndex("descr", "Shunt conductance", 0);
    model.result().numerical("gev21").set("table", "tbl4");
    model.result().numerical("gev21").setResult();
    model.result().numerical().create("gev22", "EvalGlobal");
    model.result().numerical("gev22").label("Coplanar Waveguide Capacitance");
    model.result().numerical("gev22").set("data", "dset4");
    model.result().numerical("gev22").setIndex("expr", "C_cpw", 0);
    model.result().numerical("gev22").setIndex("unit", "F/m", 0);
    model.result().numerical("gev22").setIndex("descr", "Capacitance", 0);
    model.result().numerical("gev22").set("table", "tbl4");
    model.result().numerical("gev22").setResult();
    model.result().numerical().create("gev23", "EvalGlobal");
    model.result().numerical("gev23").label("Coplanar Waveguide Characteristic Impedance");
    model.result().numerical("gev23").set("data", "dset4");
    model.result().numerical("gev23").setIndex("expr", "Zc_cpw", 0);
    model.result().numerical("gev23").setIndex("unit", "\u03a9", 0);
    model.result().numerical("gev23").setIndex("descr", "Characteristic impedance", 0);
    model.result().numerical("gev23").set("table", "tbl4");
    model.result().numerical("gev23").setResult();
    model.result().numerical().create("gev24", "EvalGlobal");
    model.result().numerical("gev24").label("Coplanar Waveguide Propagation Constant");
    model.result().numerical("gev24").set("data", "dset4");
    model.result().numerical("gev24").setIndex("expr", "gamma_cpw", 0);
    model.result().numerical("gev24").setIndex("unit", "1/m", 0);
    model.result().numerical("gev24").setIndex("descr", "Propagation constant", 0);
    model.result().numerical("gev24").set("table", "tbl4");
    model.result().numerical("gev24").setResult();
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("Coaxial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "imag(Az)");
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("color", "white");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").create("sel1", "Selection");
    model.result("pg1").feature("con1").feature("sel1").selection().set(2);
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg1").feature("str1").selection().set(5, 6, 9, 10);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Electric potential. Lines: Electric Field, Magnetic Flux Density");
    model.result("pg1").set("legendpos", "bottom");
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("Twin Lead");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("showhiddenobjects", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "V2");
    model.result("pg2").run();
    model.result("pg2").feature("con1").set("expr", "imag(A2z)");
    model.result("pg2").run();
    model.result("pg2").feature("con1").feature("sel1").selection().set(1);
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("expr", new String[]{"ec2.Ex", "ec2.Ey"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("startdata", "cln1");
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("Microstrip");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("showhiddenobjects", true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "V3");
    model.result("pg3").run();
    model.result("pg3").feature("con1").set("expr", "imag(A3z)");
    model.result("pg3").run();
    model.result("pg3").feature("con1").feature("sel1").selection().set(2, 3, 5, 6, 8, 10);
    model.result("pg3").run();
    model.result("pg3").feature("str1").set("expr", new String[]{"ec3.Ex", "ec3.Ey"});
    model.result("pg3").feature("str1").selection().set(15, 16, 17, 18);
    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4").label("Coplanar Waveguide");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").set("showhiddenobjects", true);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "V4");
    model.result("pg4").run();
    model.result("pg4").feature("con1").set("expr", "imag(A4z)");
    model.result("pg4").run();
    model.result("pg4").feature("con1").feature("sel1").selection().set(1, 2, 4, 5, 6, 8, 10, 12, 13);
    model.result("pg4").run();
    model.result("pg4").feature("str1").set("expr", new String[]{"ec4.Ex", "ec4.Ey"});
    model.result("pg4").feature("str1").selection().set(20, 21, 22, 23);

    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result("pg4").run();

    model.component("comp4").view("view4").axis().set("xmin", "-.28");
    model.component("comp4").view("view4").axis().set("xmax", 0.28);
    model.component("comp4").view("view4").axis().set("ymin", "-.08");
    model.component("comp4").view("view4").axis().set("ymax", 0.4);

    model.title(null);

    model.description("");

    model.label("transmission_line_calculator_embedded.mph");

    model.setExpectedComputationTime("4 \u79d2");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///transmission_line_calculator_coax");
    model.result().report("rpt1").set("alwaysask", true);
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("title", "\u540c\u8f74\u7ebf");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u672c\u62a5\u544a\u663e\u793a\u76f8\u5bf9\u8f93\u5165\u53c2\u6570\u53ca\u7b97\u5f97\u7684\u540c\u8f74\u7ebf\u4f20\u8f93\u7ebf\u53c2\u6570 R\u3001L\u3001G \u548c C\uff0c\u53ca\u5176\u7279\u5f81\u963b\u6297\u548c\u4f20\u64ad\u5e38\u6570\u3002");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").label("\u540c\u8f74");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u5168\u5c40\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 28, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 29, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 30, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 31, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 32, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 33, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 34, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 35, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 36, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 37, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 38, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 39, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 40, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 41, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 42, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 43, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 44, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 45, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 46, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 47, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 48, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 49, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 50, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 51, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 52, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 53, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 54, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 55, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 56, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 57, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 58, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 59, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 60, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 61, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 62, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 63, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").label("\u8868\u683c");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl1").label("R, L, G, C, Zc, gamma");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl1").set("commentssource", "none");
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").label("\u7ed8\u56fe");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report().duplicate("rpt2", "rpt1");
    model.result().report("rpt2").label("\u53cc\u5bfc\u7ebf");
    model.result().report("rpt2").set("filename", "user:///transmission_line_calculator_twin");
    model.result().report("rpt2").feature("tp1").label("\u53cc\u5bfc\u7ebf");
    model.result().report("rpt2").feature("tp1")
         .set("summary", "\u672c\u62a5\u544a\u663e\u793a\u76f8\u5bf9\u8f93\u5165\u53c2\u6570\u53ca\u7b97\u5f97\u7684\u53cc\u5bfc\u7ebf\u4f20\u8f93\u7ebf\u53c2\u6570 R\u3001L\u3001G \u548c C\uff0c\u53ca\u5176\u7279\u5f81\u963b\u6297\u548c\u4f20\u64ad\u5e38\u6570\u3002");
    model.result().report("rpt2").feature("sec1").feature("std1").set("noderef", "std2");
    model.result().report("rpt2").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 0, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 1, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 2, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 3, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 4, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 5, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 6, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 7, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", false, 8, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", true, 14, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", true, 15, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", true, 16, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", true, 17, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", true, 18, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", true, 19, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", true, 20, 1);
    model.result().report("rpt2").feature("sec2").feature("param1").setIndex("children", true, 21, 1);
    model.result().report("rpt2").feature("sec3").feature("sec1").feature("mtbl1").set("noderef", "tbl2");
    model.result().report("rpt2").feature("sec3").feature("sec2").feature("pg1").set("noderef", "pg2");
    model.result().report("rpt2").feature("sec3").feature("sec2").feature("mesh1").set("noderef", "mesh2");
    model.result().report("rpt2").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt2").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt2").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report().duplicate("rpt3", "rpt1");
    model.result().report("rpt3").label("\u5fae\u5e26\u7ebf");
    model.result().report("rpt3").set("filename", "user:///transmission_line_calculator_twin");
    model.result().report("rpt3").feature("tp1").label("\u5fae\u5e26\u7ebf");
    model.result().report("rpt3").feature("tp1")
         .set("summary", "\u672c\u62a5\u544a\u663e\u793a\u76f8\u5bf9\u8f93\u5165\u53c2\u6570\u53ca\u7b97\u5f97\u7684\u5fae\u5e26\u7ebf\u4f20\u8f93\u7ebf\u53c2\u6570 R\u3001L\u3001G \u548c C\uff0c\u53ca\u5176\u7279\u5f81\u963b\u6297\u548c\u4f20\u64ad\u5e38\u6570\u3002");
    model.result().report("rpt3").feature("sec1").feature("std1").set("noderef", "std3");
    model.result().report("rpt3").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 0, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 1, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 2, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 3, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 4, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 5, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 6, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 7, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", false, 8, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", true, 27, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", true, 28, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", true, 29, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", true, 30, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", true, 31, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", true, 32, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", true, 33, 1);
    model.result().report("rpt3").feature("sec2").feature("param1").setIndex("children", true, 34, 1);
    model.result().report("rpt3").feature("sec3").feature("sec1").feature("mtbl1").set("noderef", "tbl3");
    model.result().report("rpt3").feature("sec3").feature("sec2").feature("pg1").set("noderef", "pg3");
    model.result().report("rpt3").feature("sec3").feature("sec2").feature("mesh1").set("noderef", "mesh3");
    model.result().report("rpt3").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt3").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt3").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 2, 1);

    return model;
  }

  public static Model run4(Model model) {
    model.result().report("rpt3").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 3, 1);
    model.result().report("rpt3").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 4, 1);
    model.result().report().duplicate("rpt4", "rpt1");
    model.result().report("rpt4").label("\u5171\u9762\u6ce2\u5bfc");
    model.result().report("rpt4").set("filename", "user:///transmission_line_calculator_cpw");
    model.result().report("rpt4").feature("tp1").label("\u5171\u9762\u6ce2\u5bfc");
    model.result().report("rpt4").feature("tp1")
         .set("summary", "\u672c\u62a5\u544a\u663e\u793a\u76f8\u5bf9\u8f93\u5165\u53c2\u6570\u53ca\u7b97\u5f97\u7684\u5171\u9762\u6ce2\u5bfc\u4f20\u8f93\u7ebf\u53c2\u6570 R\u3001L\u3001G \u548c C\uff0c\u53ca\u5176\u7279\u5f81\u963b\u6297\u548c\u4f20\u64ad\u5e38\u6570\u3002");
    model.result().report("rpt4").feature("sec1").feature("std1").set("noderef", "std4");
    model.result().report("rpt4").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 0, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 1, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 2, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 3, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 4, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 5, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 6, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 7, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", false, 8, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 43, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 44, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 45, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 46, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 47, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 48, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 49, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 50, 1);
    model.result().report("rpt4").feature("sec2").feature("param1").setIndex("children", true, 51, 1);
    model.result().report("rpt4").feature("sec3").feature("sec1").feature("mtbl1").set("noderef", "tbl4");
    model.result().report("rpt4").feature("sec3").feature("sec2").feature("pg1").set("noderef", "pg4");
    model.result().report("rpt4").feature("sec3").feature("sec2").feature("mesh1").set("noderef", "mesh4");
    model.result().report("rpt4").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt4").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt4").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report("rpt4").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 3, 1);
    model.result().report("rpt4").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 4, 1);
    model.result().report("rpt4").feature("sec3").feature("sec2").feature("mesh1").setIndex("children", false, 5, 1);

    model.title("\u4f20\u8f93\u7ebf\u53c2\u6570\u8ba1\u7b97\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u521b\u5efa\u7528\u4e8e\u667a\u80fd\u624b\u673a\u7b49\u5c0f\u5c4f\u5e55\u7684 App\n\u2022 \u7528\u6237\u754c\u9762\u5bfc\u822a\uff08\u5177\u6709\u7f51\u7ad9\u4e0a\u5e38\u7528\u7684\u9876\u90e8\u83dc\u5355\uff09\n\u2022 \u52a8\u6001\u9690\u85cf\u4f7f\u7528\u5361\u7247\u5806\u7684\u8868\u5355\uff0c\u4ee5\u6700\u5c0f\u5316 App \u6240\u9700\u7684\u7a7a\u95f4\n\u2022 \u4f7f\u7528\u4e0d\u540c\u7684\u80cc\u666f\u989c\u8272\u66f4\u6539\u5916\u89c2\u3002\n\n\u4f20\u8f93\u7ebf\u7406\u8bba\u662f\u5c04\u9891\u4e0e\u5fae\u6ce2\u5de5\u7a0b\u6559\u5b66\u7684\u57fa\u77f3\u3002\n\n\u4f20\u8f93\u7ebf\u7528\u4e8e\u5f15\u5bfc\u5c04\u9891\u7535\u78c1\u573a\u7684\u7535\u78c1\u6ce2\uff0c\u5176\u5f62\u5f0f\u591a\u79cd\u591a\u6837\uff0c\u5176\u4e2d\u8bb8\u591a\u90fd\u6613\u4e8e\u5236\u9020\uff0c\u5e76\u7528\u4e8e\u5370\u5237\u7535\u8def\u677f (PCB) \u8bbe\u8ba1\u3002\u5b83\u4eec\u901a\u5e38\u7528\u4e8e\u5728\u4e00\u4e2a\u8bbe\u5907\u5185\u4ee5\u53ca\u4e24\u4e2a\u8bbe\u5907\u4e4b\u95f4\u4f20\u9012\u4fe1\u606f\uff0c\u53ef\u4ee5\u6700\u5927\u7a0b\u5ea6\u5730\u51cf\u5c11\u4fe1\u606f\u7684\u4e22\u5931\u548c\u5931\u771f\u3002\n\n\u7535\u78c1\u573a\u4ee5\u6a2a\u7535\u78c1 (TEM) \u6ce2\u7684\u5f62\u5f0f\u6cbf\u4f20\u8f93\u7ebf\u4f20\u64ad\u3002\u8be5 App \u53ef\u4ee5\u8ba1\u7b97\u7535\u963b (R)\u3001\u7535\u611f (L)\u3001\u7535\u5bfc\u7387 (G) \u548c\u7535\u5bb9 (C)\uff0c\u4ee5\u53ca\u4e00\u4e9b\u5e38\u89c1\u4f20\u8f93\u7ebf\u7c7b\u578b\u7684\u7279\u6027\u963b\u6297\u548c\u4f20\u64ad\u5e38\u6570\uff0c\u5305\u62ec\u540c\u8f74\u7ebf\u3001\u53cc\u5bfc\u7ebf\u3001\u5fae\u5e26\u7ebf\u548c\u5171\u9762\u6ce2\u5bfc (CPW)\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("transmission_line_calculator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
