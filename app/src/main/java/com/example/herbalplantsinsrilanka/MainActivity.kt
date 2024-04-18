package com.example.herbalplantsinsrilanka

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.herbalplantsinsrilanka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PlantAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var plantList: MutableList<PlantItem>
    private lateinit var adapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize plantList with herbal plants data
        plantList = mutableListOf(
            PlantItem("Gotu Kola", "Centella asiatica", "Gotu Kola, also known as \"Brahmi,\" is a herbaceous plant used in traditional medicine and culinary practices in Sri Lanka. It is renowned for its ability to improve cognitive function, enhance memory, and promote wound healing. Gotu Kola is rich in antioxidants and is believed to have anti-inflammatory properties. It is used to treat skin conditions like eczema and psoriasis, support venous circulation, and alleviate anxiety and stress.", "https://upload.wikimedia.org/wikipedia/commons/c/cc/Centella_asiatica_%28%E0%A6%A5%E0%A6%BE%E0%A6%A8%E0%A6%95%E0%A7%81%E0%A6%A8%E0%A6%BF%29_%283%29.jpg"),
            PlantItem("Ranawara", "Senna auriculata", "Ranawara, or \"Avaram,\" is a shrub native to Sri Lanka with significant medicinal properties. It is commonly used in Ayurvedic medicine to manage diabetes by regulating blood sugar levels. Ranawara is also beneficial for skin health, helping to treat conditions like acne and itching. Additionally, it has diuretic properties and is used to cleanse the urinary system. This herb is brewed into a tea and consumed for its health benefits.", "https://upload.wikimedia.org/wikipedia/commons/archive/a/a6/20180729173638%21Ranawara_or_Avaram-_Senna_auriculata_at_Sindhrot_near_Vadodara%2C_Gujrat_Pix_044.jpg"),
            PlantItem("Kothala Himbutu", "Salacia reticulata", "Kothala Himbutu is a climbing, perennial, woody shrub. The plant has dichotomous branching pattern. The bark is smooth, greenish grey in colour, thin, and white internally. The leaves are opposite and elliptic-oblong. The leaves have acute bases, abruptly acuminate apexes, and a margin with minute rounded teeth. The flowers are bisexual and arranged in clusters of 2-8 in the leaf axils. They are greenish-white to greenish-yellow in color. The fruit is a drupe which is globose and tubercular. The drupe assumes a pinkish-orange color on ripening. There are 1–4 seeds, each resembling an almond.", "https://upload.wikimedia.org/wikipedia/commons/3/30/Ekanayakam_-_plant_15.jpg"),
            PlantItem("Iramusu", "Hemidesmus indicus", "Iramusu, also called \"Indian Sarsaparilla,\" is known for its blood-purifying properties in Ayurvedic medicine. It is used to detoxify the body, improve skin health, and treat various skin disorders like acne and eczema. Iramusu is also used to support digestive function and relieve gastric disturbances. Additionally, it is believed to have anti-inflammatory effects and is used in formulations for arthritis and rheumatic conditions.", "https://upload.wikimedia.org/wikipedia/commons/d/d2/Hemidesmus_scandens.jpg"),
            PlantItem("Weniwel", "Coscinium fenestratum", "Weniwel, or \"Tree Turmeric,\" is a medicinal plant native to Sri Lanka. It is valued for its antimicrobial and anti-inflammatory properties. Weniwel is used to treat infections, particularly skin infections, and promote wound healing. In traditional medicine, it is also used to support liver health and detoxification. Weniwel is prepared as a decoction or applied topically for skin-related issues.", "https://upload.wikimedia.org/wikipedia/commons/7/73/Coscinium_fenestratum.jpg"),
            PlantItem("Polpala", "Aerva lanata", "Polpala is recognized for its diuretic and anti-inflammatory properties. It is used in traditional medicine to treat urinary tract disorders like kidney stones and bladder infections. Polpala is also beneficial for inflammatory conditions such as arthritis and gout. Additionally, it is used to alleviate symptoms of gastrointestinal disorders like indigestion and bloating. Polpala is typically prepared as a tea or decoction for internal use..", "https://indiabiodiversity.org/files-api/api/get/raw/img//Aerva_lanata/Aerva-lanata.jpg"),
            PlantItem("Koththamalli", "Coriandrum sativum", "Koththamalli, commonly known as coriander or cilantro, is a versatile herb used in Sri Lankan cuisine and traditional medicine. It is rich in antioxidants, vitamins, and minerals, making it beneficial for overall health. In culinary applications, koththamalli is used fresh as a garnish or in cooking to add flavor to dishes. It has digestive properties and is believed to alleviate digestive issues like bloating and indigestion. Additionally, koththamalli is used in traditional medicine for its anti-inflammatory and antibacterial properties, aiding in the treatment of skin conditions and promoting overall wellness.", "https://upload.wikimedia.org/wikipedia/commons/1/13/Coriandrum_sativum_-_K%C3%B6hler%E2%80%93s_Medizinal-Pflanzen-193.jpg"),
            PlantItem("Nelli", "Phyllanthus emblica", "Nelli, also known as Indian gooseberry or amla, is a highly valued medicinal fruit in Sri Lankan traditional practices and Ayurveda. It is renowned for its rich vitamin C content and potent antioxidant properties. Nelli is used to boost immunity, promote digestive health, and enhance skin and hair health. In Ayurvedic medicine, it is utilized for its rejuvenating effects and as a remedy for respiratory conditions like asthma. Nelli is consumed raw, as a juice, or in various formulations such as chutneys and medicinal concoctions. Its diverse health benefits make it a staple in holistic wellness practices.", "https://upload.wikimedia.org/wikipedia/commons/7/7f/Phyllanthus_officinalis.jpg"),
            PlantItem("Hathawariya", "Cardiospermum halicacabum", "Hathawariya, also known as balloon vine or \"Hakiniya\" in Sinhala, is a medicinal plant native to Sri Lanka. It is traditionally used to treat skin ailments, including eczema, itching, and rashes. The plant's leaves and roots are crushed and applied topically to affected areas for relief. Hathawariya is also used in Ayurvedic medicine to manage respiratory conditions such as asthma and bronchitis. It has anti-inflammatory properties and is believed to help reduce swelling and inflammation. Hathawariya is valued for its natural healing benefits and is an important component of Sri Lankan herbal remedies.", "https://upload.wikimedia.org/wikipedia/commons/3/3f/Cardiospermum_halicacabum_1808.jpg"),
            PlantItem("Thippili", "Piper longum", "Thippili, also known as long pepper, is a medicinal plant used extensively in Ayurvedic medicine and traditional practices in Sri Lanka. It is valued for its therapeutic properties, especially for respiratory ailments. Thippili is known to alleviate coughs, colds, and congestion by clearing respiratory passages and promoting expectoration. It is also used to enhance digestion, stimulate appetite, and improve metabolism. Thippili is often consumed as a powder or in herbal formulations to treat various health conditions, including asthma, indigestion, and inflammation. This versatile herb is cherished for its role in promoting respiratory and digestive wellness.", "https://upload.wikimedia.org/wikipedia/commons/6/60/Piper_longum_print.jpg"),
            PlantItem("Ashwagandha", "Withania somnifera", "Ashwagandha, also known as Indian ginseng or winter cherry, is a powerful adaptogenic herb widely used in Ayurvedic medicine. It is renowned for its ability to combat stress, promote relaxation, and improve overall vitality. Ashwagandha is considered a rejuvenating herb that helps boost energy levels and resilience against physical and mental stressors. It is used to support adrenal health, balance hormones, and enhance cognitive function. Ashwagandha is also beneficial for sleep disorders, anxiety, and inflammatory conditions. This versatile herb is typically consumed as a powder, in capsule form, or as a herbal supplement to promote overall well-being and longevity.", "https://upload.wikimedia.org/wikipedia/commons/a/ad/WithaniaFruit.jpg"),

        )

        // Initialize RecyclerView and set up adapter
        adapter = PlantAdapter(this, plantList, this)
        binding.recyclerViewPlants.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPlants.adapter = adapter

        // Set click listener for Add Plant button
        binding.btnAddPlant.setOnClickListener {
            val intent = Intent(this, AddPlantActivity::class.java)
            startActivityForResult(intent, ADD_PLANT_REQUEST)
        }
    }

    override fun onItemClick(plantItem: PlantItem) {
        // Handle item click (e.g., show detailed view)
        val intent = Intent(this, DetailedItemActivity::class.java).apply {
            putExtra("plant_item", plantItem)
        }
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_PLANT_REQUEST && resultCode == RESULT_OK) {
            // Retrieve new plant item from AddPlantActivity
            val newPlantItem = data?.getParcelableExtra<PlantItem>("new_plant_item")

            if (newPlantItem != null) {
                // Add new plant item to the list
                plantList.add(newPlantItem)
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val ADD_PLANT_REQUEST = 1
    }
}
